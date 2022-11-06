package net.xdabi.flappy;

import net.xdabi.flappy.config.GlobalRenderConfig;
import net.xdabi.flappy.content.Level;
import net.xdabi.flappy.content.background.Background;
import net.xdabi.flappy.engine.Application;
import net.xdabi.flappy.engine.device.Window;
import net.xdabi.flappy.engine.renderer.Renderer;

public class FlappyApplication extends Application {

    public static FlappyApplication instance = null;

    protected FlappyApplication() {
        super(1280, 720, "Flappy Bird | by xdAbi");
    }

    @Override
    public void onInit(Window window, Renderer renderer) {
        window.setIcon("icon.png");
        window.setAspectRatio(16, 9);
        window.setMinMaxSize(1280, 720, Window.MAX_SIZE, Window.MAX_SIZE);
        window.setVSync(false);

        renderer.getRenderCommand().setClearColor(1,1,1,1);
        renderer.setGlobalConfig(new GlobalRenderConfig());
    }

    public static FlappyApplication getInstance() {
        if (instance == null) {
            instance = new FlappyApplication();
        }

        return instance;
    }
}
