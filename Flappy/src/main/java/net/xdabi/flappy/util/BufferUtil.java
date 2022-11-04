package net.xdabi.flappy.util;

import net.xdabi.flappy.model.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtil {

    public static FloatBuffer createFloatBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer createIntBuffer(int size) {
        return BufferUtils.createIntBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(float... values) {
        FloatBuffer buffer = createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();

        return buffer;
    }

    public static IntBuffer createFlippedBuffer(int... values) {
        IntBuffer buffer = createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();

        return buffer;
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.FLOATS);

        for (Vertex vertex : vertices) {

            buffer.put(vertex.getPosition().getX());
            buffer.put(vertex.getPosition().getY());
            buffer.put(vertex.getPosition().getZ());
            buffer.put(vertex.getUvCoord().getX());
            buffer.put(vertex.getUvCoord().getY());
        }

        buffer.flip();

        return buffer;
    }

}
