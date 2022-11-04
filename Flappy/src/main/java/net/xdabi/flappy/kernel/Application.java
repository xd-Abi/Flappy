package net.xdabi.flappy.kernel;

import lombok.Getter;
import net.xdabi.flappy.config.RenderConfig;
import net.xdabi.flappy.math.Vec2f;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.model.Mesh;
import net.xdabi.flappy.model.Vertex;
import org.lwjgl.glfw.GLFW;

public class Application implements Runnable {

    @Getter
    private Window window;

    @Getter
    private Input input;

    private RenderConfig renderConfig;

    private Mesh mesh;

    public Application() {

    }

    private void init() {
        window = new Window(1280, 720, "Flappy Bird | by xdAbi");
        window.create();
        window.setVSync(false);

        input = new Input(window);
        renderConfig = new RenderConfig();

        mesh = new Mesh();
        mesh.create(new Vertex[]{
                new Vertex(new Vec3f(-0.5f, 0.5f, 0), new Vec2f(0, 0)),
                new Vertex(new Vec3f(-0.5f, -0.5f, 0), new Vec2f(0, 1)),
                new Vertex(new Vec3f(0.5f, -0.5f, 0), new Vec2f(1, 1)),
                new Vertex(new Vec3f(0.5f, 0.5f, 0), new Vec2f(1, 0)),
        }, new int[]{
                0,1,3,3,1,2
        });
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
        mesh.draw();

        renderConfig.disable();
        window.draw();
    }
}
