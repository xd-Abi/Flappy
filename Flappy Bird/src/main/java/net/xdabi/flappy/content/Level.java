package net.xdabi.flappy.content;

import lombok.Getter;
import net.xdabi.flappy.FlappyApplication;
import net.xdabi.flappy.content.background.Background;
import net.xdabi.flappy.content.bird.Bird;
import net.xdabi.flappy.content.bird.types.DefaultBird;
import net.xdabi.flappy.content.bird.types.ReversyBird;
import net.xdabi.flappy.content.bird.types.StrongBird;
import net.xdabi.flappy.content.bird.types.WeakBird;
import net.xdabi.flappy.content.effects.VignetteEffect;
import net.xdabi.flappy.content.pipe.PipeGenerator;
import net.xdabi.flappy.engine.input.Input;
import net.xdabi.flappy.engine.input.KeyCode;
import net.xdabi.flappy.engine.math.Transform;
import net.xdabi.flappy.engine.scenegraph.Renderable;

public class Level extends Renderable {

    private Bird bird;
    private final PipeGenerator pipeGenerator;

    private final Input input;

    @Getter
    private GameState gameState;

    public Level() {
        super();

        input = FlappyApplication.getInstance().getInput();
        bird = new DefaultBird();
        pipeGenerator = new PipeGenerator();
        gameState = GameState.MENU;

        addChild(new Background());
        addChild(pipeGenerator);
        addChild(bird);

        // Effects
        addChild(new VignetteEffect());
    }

    @Override
    public void update() {

        switch (gameState) {
            case MENU -> {
                if (input.isKeyPushed(KeyCode.NUM_1)) {
                    changeBird(new DefaultBird());
                } else if (input.isKeyPushed(KeyCode.NUM_2)) {
                    changeBird(new StrongBird());
                }
                else if (input.isKeyPushed(KeyCode.NUM_3)) {
                    changeBird(new WeakBird());
                }
                else  if(input.isKeyPushed(KeyCode.NUM_4)) {
                    changeBird(new ReversyBird());
                } else if (input.isKeyPushed(KeyCode.SPACE)) {
                    gameState = GameState.PLAYING;
                }
            }
            case PLAYING -> {
                super.update();

                if (checkCollision()) {
                    gameState = GameState.DEATH;
                }
            }
            case DEATH -> {
                bird.death();

            }
        }
    }

    private void changeBird(Bird bird) {
        if (this.bird == bird) {
            return;
        }

        int index = getChildren().indexOf(this.bird);
        this.bird = bird;
        getChildren().set(index, this.bird);
    }

    private boolean checkCollision() {
        Transform birdTransform = bird.getWorldTransform();
        float bx = birdTransform.getTranslation().getX();
        float by = birdTransform.getTranslation().getY();

        for (int i = 0; i < PipeGenerator.MAX_PIPES; i++) {

            Transform pipeTransform = pipeGenerator.getChildren().get(i).getWorldTransform();
            float px = pipeTransform.getTranslation().getX();
            float py = pipeTransform.getTranslation().getY();

            float bx0 = bx - (birdTransform.getScaling().getX() + 0.0f) / 2;
            float bx1 = bx + (birdTransform.getScaling().getX() + 0.55f) / 2;
            float by0 = by - birdTransform.getScaling().getY() / 2;
            float by1 = by + birdTransform.getScaling().getY() / 2;

            float px0 = px - pipeTransform.getScaling().getX() / 2;
            float px1 = px + (pipeTransform.getScaling().getX() - 0.55f) / 2;
            float py0 = py - pipeTransform.getScaling().getY() - pipeTransform.getScaling().getY() / 12f;
            float py1 = py + pipeTransform.getScaling().getY() / 1.2f;


            if (bx1 > px0 && bx0 < px1) {
                if (by1 > py0 && by0 < py1) {
                    return true;
                }
            }
        }

        return false;
    }

}
