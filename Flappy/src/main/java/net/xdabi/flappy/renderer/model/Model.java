package net.xdabi.flappy.renderer.model;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;

@Getter
@Setter
public class Model {

    private Mesh mesh;
    private ShaderProgram shaderProgram;

    public Model() {

    }

    public Model(Mesh mesh, ShaderProgram shaderProgram) {
        this.mesh = mesh;
        this.shaderProgram = shaderProgram;
    }
}
