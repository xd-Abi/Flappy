package net.xdabi.flappy.engine.renderer;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.engine.device.WindowSizeListener;
import net.xdabi.flappy.engine.scenegraph.RenderList;
import net.xdabi.flappy.engine.scenegraph.Renderable;

public class Renderer implements WindowSizeListener {

    @Getter
    @Setter
    private RenderConfig globalConfig;

    @Getter
    private final RenderCommand renderCommand;

    @Getter
    @Setter
    private RenderList renderList;

    public Renderer() {
        renderCommand = new RenderCommand();
        renderList = new RenderList();
    }

    public void update() {
        renderList.getValues().forEach(Renderable::update);
    }

    public void render() {
        globalConfig.enable();
        renderCommand.clearScreen();
        renderList.getValues().forEach(Renderable::render);
        globalConfig.disable();
    }

    @Override
    public void onWindowSizeChange(int width, int height) {
        renderCommand.setViewPort(0, 0, width, height);
    }
}
