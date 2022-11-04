package net.xdabi.flappy.kernel;

import lombok.Getter;

public class Application implements Runnable {

    @Getter
    private Window window;

    public Application() {

    }

    private void init() {
        window = new Window(1280, 720, "Flappy Bird | by xdAbi");
        window.create();
        window.setVSync(true);
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
        window.update();
    }

    private void render() {
        window.draw();
    }
}
