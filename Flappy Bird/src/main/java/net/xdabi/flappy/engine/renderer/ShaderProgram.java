package net.xdabi.flappy.engine.renderer;

import static org.lwjgl.opengl.GL20.*;

import lombok.Getter;

import net.xdabi.flappy.engine.math.Matrix4f;
import net.xdabi.flappy.engine.math.Vec2f;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.math.Vec4f;
import net.xdabi.flappy.engine.scenegraph.Renderable;
import net.xdabi.flappy.engine.utils.BufferUtil;
import org.lwjgl.opengl.GL20;

import java.util.HashMap;

public class ShaderProgram {

    @Getter
    private final int id;
    private final HashMap<String, Integer> uniforms;

    public ShaderProgram() {
        id = glCreateProgram();
        uniforms = new HashMap<>();
    }

    public void delete() {
        glUseProgram(0);

        if (id != 0) {
            glDeleteProgram(id);
        }
    }

    public void bind() {
        glUseProgram(id);
    }

    public void addUniform(String uniform) {
        int uniformLocation = glGetUniformLocation(id, uniform);

        if (uniformLocation == 0xFFFFFFFF) {
            throw new RuntimeException("Could not find uniform: " + uniform);
        }

        uniforms.put(uniform, uniformLocation);
    }

    public void addVertexShader(String text) {
        addProgram(text, GL_VERTEX_SHADER);
    }

    public void addFragmentShader(String text) {
        addProgram(text, GL_FRAGMENT_SHADER);
    }

    public void compile() {
        glLinkProgram(id);

        if (glGetProgrami(id, GL_LINK_STATUS) == 0) {
            throw new RuntimeException(glGetProgramInfoLog(id, 1024));
        }

        glValidateProgram(id);

        if (glGetProgrami(id, GL_VALIDATE_STATUS) == 0) {
            throw new RuntimeException(glGetProgramInfoLog(id, 1024));
        }
    }

    private void addProgram(String text, int type) {
        int shader = glCreateShader(type);

        if (shader == 0) {
            throw new RuntimeException("Shader creation failed");
        }

        glShaderSource(shader, text);
        glCompileShader(shader);

        if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException(glGetShaderInfoLog(shader, 1024));
        }

        glAttachShader(id, shader);
    }

    public void updateUniforms(Renderable parent) {
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, boolean value) {
        glUniform1i(uniforms.get(uniformName), value ? 1 : 0);
    }

    public void setUniform(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, Vec2f value) {
        glUniform2f(uniforms.get(uniformName), value.getX(), value.getY());
    }

    public void setUniform(String uniformName, Vec3f value) {
        glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
    }

    public void setUniform(String uniformName, Vec4f value) {
        glUniform4f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ(), value.getW());
    }

    public void setUniform(String uniformName, Matrix4f value) {
        GL20.glUniformMatrix4fv(uniforms.get(uniformName), true, BufferUtil.createFlippedBuffer(value));
    }
}