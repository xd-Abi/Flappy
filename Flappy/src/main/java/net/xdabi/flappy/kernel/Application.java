package net.xdabi.flappy.kernel;

import lombok.Getter;
import net.xdabi.flappy.config.RenderConfig;
import org.lwjgl.glfw.GLFW;

public class Application implements Runnable {

    @Getter
    private Window window;

    @Getter
    private Input input;

    private RenderConfig renderConfig;

    public Application() {

    }

    private void init() {
        window = new Window(1280, 720, "Flappy Bird | by xdAbi");
        window.create();
        window.setVSync(false);

        input = new Input(window);
        renderConfig = new RenderConfig();
    }

    private void shutdown() {
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

        renderConfig.disable();
        window.draw();
    }
}
