package net.xdabi.flappy.scenegraph.content;

import net.xdabi.flappy.image.Image;
import net.xdabi.flappy.kernel.Input;
import net.xdabi.flappy.math.Matrix4f;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.model.Mesh;
import net.xdabi.flappy.model.Model;
import net.xdabi.flappy.pipeline.ShaderProgram;
import net.xdabi.flappy.scenegraph.Renderable;
import net.xdabi.flappy.util.MeshGenerator;
import net.xdabi.flappy.util.ResourceLoader;
import org.lwjgl.glfw.GLFW;

public class Bird extends Renderable {

    private float delta = 0.0f;

    public Bird() {
        Mesh quad = new Mesh();
        MeshGenerator.createQuad(quad);

        ShaderProgram shaderProgram = new ShaderProgram();
        shaderProgram.addVertexShader(ResourceLoader.loadShader("shaders/bird-vertex.glsl"));
        shaderProgram.addFragmentShader(ResourceLoader.loadShader("shaders/bird-fragment.glsl"));
        shaderProgram.compile();
        shaderProgram.addUniform("worldMatrix");
        shaderProgram.addUniform("modelMatrix");
        shaderProgram.addUniform("sampler");

        setTexture(new Image("assets/bird.png"));
        setModel(new Model(quad, shaderProgram));
        getTransform().setScaling(new Vec3f(1f));
    }

    @Override
    public void update(Input input) {
        getTransform().getTranslation().setY(getTransform().getTranslation().getY() - delta);

        if (input.isKeyPushed(GLFW.GLFW_KEY_SPACE)) {
            delta = -0.15f;
        }
        else {
            delta += 0.01f;
        }

        getTransform().setRotation(new Vec3f(0, 0, -0.2f));
    }

    @Override
    public void updateUniforms(ShaderProgram shaderProgram, Matrix4f worldMatrix) {
        shaderProgram.setUniform("sampler", 0);
        shaderProgram.setUniform("worldMatrix", worldMatrix);
        shaderProgram.setUniform("modelMatrix", getTransform().getWorldMatrix());
    }
}
