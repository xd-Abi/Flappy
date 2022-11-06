package net.xdabi.flappy.engine.utils;

import net.xdabi.flappy.engine.math.Vec2f;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.renderer.Mesh;
import net.xdabi.flappy.engine.renderer.Vertex;

public class MeshGenerator {

    public static Mesh createQuad() {
        return createRectangle(1,1);
    }

    public static Mesh createRectangle(float width, float height) {
        return new Mesh(new Vertex[]{
                new Vertex(new Vec3f(-width, -height, 0), new Vec2f(0, 1)),
                new Vertex(new Vec3f(-width, height, 0), new Vec2f(0, 0)),
                new Vertex(new Vec3f(0f, height, 0), new Vec2f(1, 0)),
                new Vertex(new Vec3f(0f, -height, 0), new Vec2f(1, 1)),
        }, new int[]{
                0, 1, 2,
                2,3,0
        });
    }
}