package net.xdabi.flappy.engine.renderer;

import net.xdabi.flappy.engine.utils.BufferUtil;
import org.lwjgl.opengl.GL15;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class MeshBuffer {

    private final int vao;
    private final int vbo;
    private final int ibo;
    private final int size;

    public MeshBuffer(Mesh mesh) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = mesh.getIndices().length;

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(mesh.getVertices()), GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.BYTES, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.BYTES, Float.BYTES * 3);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(mesh.getIndices()), GL_STATIC_DRAW);

        glBindVertexArray(0);
    }

    public void draw() {
        glBindVertexArray(vao);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(0);
        glBindVertexArray(0);
    }

    public void delete() {
        glBindVertexArray(vao);
        glDeleteBuffers(vbo);
        glDeleteBuffers(ibo);
        glDeleteVertexArrays(vao);
        glBindVertexArray(0);
    }
}
