package net.xdabi.flappy.content.bird;

import net.xdabi.flappy.FlappyApplication;
import net.xdabi.flappy.engine.audio.Audio;
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

    private final float fallSpeed;
    private final float jumpVelocity;
    private final Input input;

    private final Audio flapSound;

    private float delta = 0.0f;

    public Bird(Image texture, float fallSpeed, float jumpVelocity) {
        super();

        this.fallSpeed = fallSpeed;
        this.jumpVelocity = jumpVelocity;
        this.flapSound = new Audio("sounds/flap.wav");
        this.input = FlappyApplication.getInstance().getInput();

        getWorldTransform().getTranslation().setZ(0.05f);
        Mesh mesh = MeshGenerator.createQuad();
        MeshBuffer meshBuffer = new MeshBuffer(mesh);
        RenderInfo renderInfo = new RenderInfo(new BirdRenderConfig(), new BirdShader(), meshBuffer);

        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
        addComponent(NodeComponentType.TEXTURE, texture);
    }

    @Override
    public void update() {
        super.update();

        getWorldTransform().getTranslation().setY(getWorldTransform().getTranslation().getY() - delta);

        if (input.isKeyPushed(KeyCode.SPACE)) {
            delta = -jumpVelocity;
            flapSound.play();
        } else {
            delta += fallSpeed;
        }

        getWorldTransform().setRotation(new Vec3f(0,0, -0.2f * -delta * .0f));

    }

    public void death() {

        if (getWorldTransform().getTranslation().getY() > -11f) {
            getWorldTransform().getTranslation().setY(getWorldTransform().getTranslation().getY() - delta);
        }

        delta += 0.01f;
    }

    @Override
    public void delete() {
        super.delete();
        flapSound.delete();
    }
}
