package net.xdabi.flappy.content.pipe;

import net.xdabi.flappy.math.Transform;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.renderer.RenderInfo;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.model.Mesh;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.MeshGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class PipeGenerator extends Renderable {

    public static final int MAX_PIPES = 2 * 5;
    public static final float SPACE_BETWEEN_PIPES = 13.5f;
    public static final float MOVE_SPEED = 0.05f;
    public static final float SPEED_PER_TICK = 0.001f;

    public static final float MIN_X_POS = 4f;
    public static final float MAX_X_POS = 8f;

    private int index = 0;
    private float currentSpeed = 1.0f;

    public PipeGenerator() {
        super();

        generatePipes();

        Mesh mesh = MeshGenerator.createRectangle(1.5f, 8.0f);
        RenderInfo renderInfo = new PipeGeneratorRenderInfo(new PipeGeneratorRenderConfig(), new PipeGeneratorShader(), mesh);

        Image image = new Image("assets/pipe.png");
        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
        addComponent(NodeComponentType.TEXTURE, image);
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < MAX_PIPES; i++) {
            Transform pipeTransform = getChildren().get(i).getWorldTransform();
            pipeTransform.getTranslation().setX(pipeTransform.getTranslation().getX() - MOVE_SPEED * currentSpeed);

            if (pipeTransform.getTranslation().getX() < -10) {
                updatePipes();
            }
        }

        currentSpeed += SPEED_PER_TICK;
    }

    private void updatePipes() {
        Pipe middlePipe = (Pipe) getChildren().get(MAX_PIPES / 2);
        float xPos = middlePipe.getInitialTranslation() + 4.0f;
        float yPos = ThreadLocalRandom.current().nextFloat(MIN_X_POS, MAX_X_POS);

        Pipe upperPipe = (Pipe) getChildren().get(index % 10);
        upperPipe.getWorldTransform().setTranslation(new Vec3f(xPos, yPos, Pipe.Z_POS));

        Pipe lowerPipe = (Pipe) getChildren().get((index + 1) % 10);
        lowerPipe.getWorldTransform().setTranslation(new Vec3f(xPos, yPos - SPACE_BETWEEN_PIPES, Pipe.Z_POS));

        getChildren().set(index % 10, upperPipe);
        getChildren().set((index + 1) % 10, lowerPipe);

        index += 2;
    }

    private void generatePipes() {
        for (int i = 0; i < MAX_PIPES; i += 2) {

            float xPos = 5.0f + index * 3.0f;
            float yPos = ThreadLocalRandom.current().nextFloat(MIN_X_POS, MAX_X_POS);

            Pipe upperPipe = new Pipe(xPos, yPos);
            Pipe lowerPipe = new Pipe(xPos, yPos - SPACE_BETWEEN_PIPES);
            lowerPipe.getWorldTransform().getScaling().setY(-lowerPipe.getWorldTransform().getScaling().getY());

            addChild(upperPipe);
            addChild(lowerPipe);

            index += 2;
        }
    }
}
