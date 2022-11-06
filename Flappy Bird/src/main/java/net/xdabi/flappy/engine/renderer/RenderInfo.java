package net.xdabi.flappy.engine.renderer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.xdabi.flappy.engine.scenegraph.NodeComponent;

@Getter
@AllArgsConstructor
public class RenderInfo extends NodeComponent {

    private RenderConfig config;
    private ShaderProgram shader;
    private MeshBuffer vbo;

    @Override
    public void render() {
        config.enable();
        shader.bind();
        shader.updateUniforms(getParent());
        vbo.draw();
        config.disable();
    }

    @Override
    public void delete() {
        vbo.delete();
        shader.delete();
    }
}
