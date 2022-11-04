package net.xdabi.flappy.kernel;

import lombok.Getter;
import net.xdabi.flappy.config.RenderConfig;
import net.xdabi.flappy.math.*;
import net.xdabi.flappy.model.Mesh;
import net.xdabi.flappy.pipeline.ShaderProgram;
import net.xdabi.flappy.scenegraph.RenderList;
import net.xdabi.flappy.scenegraph.content.Background;
import net.xdabi.flappy.scenegraph.content.Bird;
import net.xdabi.flappy.util.MeshGenerator;
import net.xdabi.flappy.util.ResourceLoader;

public class Application implements Runnable {

    @Getter
    private Window window;

    @Getter
    private Input input;

    private RenderConfig renderConfig;

    private RenderList renderList;

    private Matrix4f worldMatrix;

    public Application() {

    }

    private void init() {
        window = new Window(1280, 720, "Flappy Bird | by xdAbi");
        window.create();
        window.setVSync(false);

        input = new Input(window);
        renderConfig = new RenderConfig();

        renderList = new RenderList();
        renderList.add(new Bird());
        renderList.add(new Background());

        worldMatrix = new Matrix4f().orthographicProjection(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
    }

    private void shutdown() {
        renderList.delete();
        window.destroy();
    }

    @Override
    public void run() {

        init();

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0; // Limited to 60 updates per second
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (!window.isCloseRequested()) {
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
        renderList.update(input);
    }

    private void render() {
        renderConfig.enable();
        renderList.render(worldMatrix);
        renderConfig.disable();
        window.draw();
    }
}
