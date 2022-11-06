package net.xdabi.flappy.engine.renderer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mesh {

    private Vertex[] vertices;
    private int[] indices;

    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }
}
