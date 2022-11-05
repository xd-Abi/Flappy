package net.xdabi.flappy.renderer;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Renderer {

    @Getter
    private Map<String, Renderable> renderList;

    @Getter
    @Setter
    private RenderConfig globalConfig;

    public Renderer() {
        renderList = new HashMap<>();
    }

    public void addObject(String key, Renderable renderable) {
        renderList.put(key, renderable);
    }

    public void update() {
        renderList.forEach((key, renderable) -> renderable.update());
    }

    public void render() {
        globalConfig.enable();
        renderList.forEach((key, renderable) -> renderable.render());
        globalConfig.disable();
    }

    public void deleteObjects() {
        renderList.forEach((key, renderable) -> renderable.render());

    }
}
