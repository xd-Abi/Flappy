package net.xdabi.flappy.content.bird;

import net.xdabi.flappy.FlappyApplication;
import net.xdabi.flappy.engine.input.Input;
import net.xdabi.flappy.engine.input.KeyCode;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.renderer.Image;
import net.xdabi.flappy.engine.renderer.Mesh;
import net.xdabi.flappy.engine.renderer.MeshBuffer;
import net.xdabi.flappy.engine.renderer.RenderInfo;
import net.xdabi.flappy.engine.scenegraph.NodeComponentType;
import net.xdabi.flappy.engine.scenegraph.Renderable;
import net.xdabi.flappy.engine.utils.MeshGenerator;

public class Bird extends Renderable {

    public static final float FALL_SPEED = 0.01f;
    public static final float JUMP_VELOCITY = 0.15f;

    private final Input input;
    private float delta = 0.0f;

    public Bird() {
        super();

        input = FlappyApplication.getInstance().getInput();

        getWorldTransform().getTranslation().setZ(0.05f);
        Mesh mesh = MeshGenerator.createQuad();
        MeshBuffer meshBuffer = new MeshBuffer(mesh);
        RenderInfo renderInfo = new RenderInfo(new BirdRenderConfig(), new BirdShader(), meshBuffer);
        Image image = new Image("images/bird.png");

        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
        addComponent(NodeComponentType.TEXTURE, image);
    }

    @Override
    public void update() {
        super.update();

        getWorldTransform().getTranslation().setY(getWorldTransform().getTranslation().getY() - delta);

        if (input.isKeyPushed(KeyCode.SPACE)) {
            delta = -JUMP_VELOCITY;
        } else {
            delta += FALL_SPEED;
        }

        getWorldTransform().setRotation(new Vec3f(0,0, -0.2f * -delta * .0f));

    }

    public void death() {

        if (getWorldTransform().getTranslation().getY() > -9.5f) {
            getWorldTransform().getTranslation().setY(getWorldTransform().getTranslation().getY() - delta);
        }

        delta += FALL_SPEED;
    }
}
