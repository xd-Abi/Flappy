package net.xdabi.flappy.scenegraph;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.image.Image;
import net.xdabi.flappy.kernel.Input;
import net.xdabi.flappy.math.Matrix4f;
import net.xdabi.flappy.math.Transform;
import net.xdabi.flappy.model.Model;
import net.xdabi.flappy.pipeline.ShaderProgram;

@Getter
@Setter
public abstract class Renderable {

    private Transform transform;
    private Model model;

    private Image texture;

    public Renderable() {
        transform = new Transform();
        texture = null;
    }

    public abstract void update(Input input);

    public abstract void updateUniforms(ShaderProgram shaderProgram, Matrix4f worldMatrix);

    public void render(Matrix4f worldMatrix) {
        model.getShaderProgram().bind();

        updateUniforms(model.getShaderProgram(), worldMatrix);

        if (texture != null) {
            texture.noFilter();
            texture.bind();
        }

        model.getMesh().draw();
        model.getShaderProgram().unbind();
    }

    public void delete() {
        model.getMesh().delete();
        model.getShaderProgram().delete();

        if (texture != null) {
            texture.delete();
        }
    }
}
