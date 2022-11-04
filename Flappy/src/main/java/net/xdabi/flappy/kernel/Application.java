package net.xdabi.flappy.kernel;

import lombok.Getter;
import net.xdabi.flappy.config.RenderConfig;
import net.xdabi.flappy.math.*;
import net.xdabi.flappy.model.Mesh;
import net.xdabi.flappy.model.Vertex;
import net.xdabi.flappy.pipeline.ShaderProgram;
import net.xdabi.flappy.util.MeshGenerator;
import net.xdabi.flappy.util.ResourceLoader;
import org.lwjgl.glfw.GLFW;

public class Application implements Runnable {

    @Getter
    private Window window;

    @Getter
    private Input input;

    private RenderConfig renderConfig;

    private Mesh mesh;
    private ShaderProgram shaderProgram;

    public Application() {

    }

    private void init() {
        window = new Window(1280, 720, "Flappy Bird | by xdAbi");
        window.create();
        window.setVSync(false);

        input = new Input(window);
        renderConfig = new RenderConfig();

        mesh = new Mesh();
        MeshGenerator.createQuad(mesh);
        shaderProgram = new ShaderProgram();
        shaderProgram.addVertexShader(ResourceLoader.loadShader("shader/test-vertex.glsl"));
        shaderProgram.addFragmentShader(ResourceLoader.loadShader("shader/test-fragment.glsl"));
        shaderProgram.compile();
        shaderProgram.addUniform("color");
        shaderProgram.addUniform("worldMatrix");

    }

    private void shutdown() {
        mesh.delete();
        window.destroy();
    }

    @Override
    public void run() {

        init();

        while (!window.isCloseRequested()) {
            update();
            render();
        }

        shutdown();
    }

    private void update() {
        input.update();
        window.update();

    }

    private void render() {
        renderConfig.enable();
        shaderProgram.bind();
        shaderProgram.setUniform("color", new Vec4f(1,0.3f,0.3f, 1f));
        shaderProgram.setUniform("worldMatrix", new Transform().getWorldMatrix());

        mesh.draw();
        shaderProgram.unbind();
        renderConfig.disable();
        window.draw();
    }
}
