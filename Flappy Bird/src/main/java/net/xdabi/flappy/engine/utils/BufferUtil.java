package net.xdabi.flappy.engine.utils;

import net.xdabi.flappy.engine.math.Matrix4f;
import net.xdabi.flappy.engine.renderer.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
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

    public static FloatBuffer createFlippedBuffer(Matrix4f matrix) {
        FloatBuffer buffer = createFloatBuffer(4 * 4);

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                buffer.put(matrix.get(i, j));
            }
        }
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

    public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }
}
