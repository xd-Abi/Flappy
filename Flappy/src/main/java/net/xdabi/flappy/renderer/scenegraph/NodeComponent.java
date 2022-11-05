package net.xdabi.flappy.renderer.scenegraph;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.renderer.Renderable;

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
