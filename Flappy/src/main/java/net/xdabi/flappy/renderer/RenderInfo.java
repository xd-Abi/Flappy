package net.xdabi.flappy.renderer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;
import net.xdabi.flappy.renderer.scenegraph.NodeComponent;
import net.xdabi.flappy.renderer.model.Mesh;

@Getter
@AllArgsConstructor
public class RenderInfo extends NodeComponent {

    private RenderConfig config;
    private ShaderProgram shader;
    private Mesh mesh;

    @Override
    public void render() {
        config.enable();
        shader.bind();
        shader.updateUniforms(getParent());
        mesh.draw();
        config.disable();
    }

    @Override
    public void delete() {
        mesh.delete();
        shader.delete();
    }
}
