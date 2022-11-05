package net.xdabi.flappy.content.bird;

import net.xdabi.flappy.kernel.Application;
import net.xdabi.flappy.kernel.Input;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.MeshGenerator;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.renderer.RenderInfo;
import net.xdabi.flappy.renderer.model.Mesh;
import org.lwjgl.glfw.GLFW;

public class Bird extends Renderable {

    public static final float FALL_SPEED = 0.01f;
    public static final float JUMP_VELOCITY  = 0.15f;

    private final Input input;
    private float delta = 0.0f;

    public Bird() {
        super();

        input = Application.getInstance().getInput();

        getWorldTransform().getTranslation().setZ(0.05f);
        getWorldTransform().setScaling(new Vec3f(0.6f));
        Mesh mesh = MeshGenerator.createQuad();
        RenderInfo renderInfo = new RenderInfo(new BirdRenderConfig(), new BirdShader(), mesh);

        Image image = new Image("assets/bird.png");

        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
        addComponent(NodeComponentType.TEXTURE, image);
    }

    @Override
    public void update() {
        super.update();
        getWorldTransform().getTranslation().setY(getWorldTransform().getTranslation().getY() - delta);

        if (input.isKeyPushed(GLFW.GLFW_KEY_SPACE)) {
            delta = -JUMP_VELOCITY;
        }
        else {
            delta += FALL_SPEED;
        }
        getWorldTransform().setRotation(new Vec3f(0, 0, -0.2f * -delta * 10));
    }

}
