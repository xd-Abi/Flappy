package net.xdabi.flappy.engine;

import lombok.Getter;
import net.xdabi.flappy.engine.device.Window;
import net.xdabi.flappy.engine.input.Input;
import net.xdabi.flappy.engine.renderer.Renderer;

public class Application implements Runnable {

    @Getter
    private final Window window;

    @Getter
    private final Input input;

    @Getter
    private final Renderer renderer;

    @Getter
    private boolean isRunning = true;

    protected Application(int width, int height, String title) {
        window = new Window(width, height, title);
        window.create();
        window.show();
        input = new Input();
        renderer = new Renderer();

        window.addListener(input);
        window.addListener(renderer);

        onInit(window, renderer);
    }

    public void onInit(Window window, Renderer renderer) {}

    public void onShutdown() {}

    private void shutdown() {
        onShutdown();
        window.destroy();
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0; // Limited to 60 updates per second
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1.0) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (window.isCloseRequested()) {
                isRunning = false;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }


        shutdown();
    }

    private void update() {
        input.update();
        window.update();
        renderer.update();
    }

    private void render() {
        renderer.render();
        window.draw();
    }
}
