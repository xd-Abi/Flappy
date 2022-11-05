package net.xdabi.flappy.renderer;

import lombok.Getter;
import net.xdabi.flappy.renderer.scenegraph.Node;
import net.xdabi.flappy.renderer.scenegraph.NodeComponent;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;

import java.util.HashMap;
import java.util.Map;

public class Renderable extends Node {

    @Getter
    private Map<NodeComponentType, NodeComponent> components;

    public Renderable() {
        super();
        components = new HashMap<>();
    }

    public void addComponent(NodeComponentType type, NodeComponent component) {
        component.setParent(this);
        components.put(type, component);
    }

    public void update() {
        super.update();
        components.forEach((nodeComponentType, nodeComponent) -> nodeComponent.update());
    }

    public void render() {
        super.render();
        components.forEach((nodeComponentType, nodeComponent) -> nodeComponent.render());
    }

    public void delete() {
        components.forEach((nodeComponentType, nodeComponent) -> nodeComponent.delete());
    }

    public <T> T getComponent(NodeComponentType type) {
        return (T)components.getOrDefault(type, null);
    }
}
