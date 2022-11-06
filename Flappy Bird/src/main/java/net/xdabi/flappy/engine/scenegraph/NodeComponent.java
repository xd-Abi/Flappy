package net.xdabi.flappy.engine.scenegraph;

import lombok.Getter;
import lombok.Setter;

public class NodeComponent {

    @Getter
    @Setter
    private Renderable parent;

    public NodeComponent() {

    }

    public void update() {}

    public void render() {}

    public void delete() {}
}