package net.xdabi.flappy.renderer.model;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.math.Vec2f;
import net.xdabi.flappy.math.Vec3f;

@Getter
@Setter
public class Vertex {

    public static final int FLOATS = 5;
    public static final int BYTES = FLOATS * Float.BYTES;

    private Vec3f position;
    private Vec2f uvCoord;

    public Vertex(Vec3f position, Vec2f uvCoord) {
        this.position = position;
        this.uvCoord = uvCoord;
    }
}
