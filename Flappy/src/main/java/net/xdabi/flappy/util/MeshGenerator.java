package net.xdabi.flappy.util;

import net.xdabi.flappy.math.Vec2f;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.renderer.model.Mesh;
import net.xdabi.flappy.renderer.model.Vertex;

public class MeshGenerator {

    public static Mesh createQuad() {
        Mesh mesh = new Mesh();
        mesh.create(new Vertex[]{
                new Vertex(new Vec3f(-1f, 1f, 0), new Vec2f(0, 0)),
                new Vertex(new Vec3f(-1f, -1f, 0), new Vec2f(0, 1)),
                new Vertex(new Vec3f(1f, -1f, 0), new Vec2f(1, 1)),
                new Vertex(new Vec3f(1f, 1f, 0), new Vec2f(1, 0)),
        }, new int[]{
                0, 1, 3, 3, 1, 2
        });

        return mesh;
    }

    public static Mesh createRectangle(float width, float height) {
        Mesh mesh = new Mesh();
        mesh.create(new Vertex[]{
                new Vertex(new Vec3f(-width, -height, 0), new Vec2f(0, 1)),
                new Vertex(new Vec3f(-width, height, 0), new Vec2f(0, 0)),
                new Vertex(new Vec3f(0, height, 0), new Vec2f(1, 0)),
                new Vertex(new Vec3f(0, -height, 0), new Vec2f(1, 1)),
        }, new int[]{
                0, 1, 3, 3, 1, 2
        });

        return mesh;
    }
}
