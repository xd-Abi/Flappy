package net.xdabi.flappy.kernel;

import lombok.Getter;
import org.lwjgl.glfw.GLFW;

public class Application implements Runnable {

    @Getter
    private Window window;

    @Getter
    private Input input;

    public Application() {

    }

    private void init() {
        window = new Window(1280, 720, "Flappy Bird | by xdAbi");
        window.create();
        window.setVSync(false);

        input = new Input(window);
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
        window.draw();
    }
}
