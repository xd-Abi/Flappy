package net.xdabi.flappy.util;

import net.xdabi.flappy.math.Vec2f;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.model.Mesh;
import net.xdabi.flappy.model.Vertex;

public class MeshGenerator {

    public static void createQuad(Mesh mesh) {
        mesh.create(new Vertex[]{
                new Vertex(new Vec3f(-1f, 1f, 0), new Vec2f(0, 0)),
                new Vertex(new Vec3f(-1f, -1f, 0), new Vec2f(0, 1)),
                new Vertex(new Vec3f(1f, -1f, 0), new Vec2f(1, 1)),
                new Vertex(new Vec3f(1f, 1f, 0), new Vec2f(1, 0)),
        }, new int[]{
                0, 1, 3, 3, 1, 2
        });
    }
}
