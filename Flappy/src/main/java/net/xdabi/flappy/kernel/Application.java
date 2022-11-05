package net.xdabi.flappy.kernel;

import lombok.Getter;
import net.xdabi.flappy.config.GlobalRenderConfig;
import net.xdabi.flappy.renderer.Renderer;

public class Application implements Runnable {

    private static Application instance = null;

    @Getter
    private Window window;

    @Getter
    private Input input;

    @Getter
    private Renderer renderer;

    @Getter
    private boolean isRunning;

    protected Application() {
        init();
    }

    private void init() {
        window = new Window(1280, 720, "Flappy Bird | by xdAbi");
        window.create();
        window.setVSync(false);

        input = new Input(window);
        renderer = new Renderer();
        renderer.setGlobalConfig(new GlobalRenderConfig());

        isRunning = true;
    }

    private void shutdown() {
        renderer.deleteObjects();
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

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }

        return instance;
    }
}
