package net.xdabi.flappy.content.pipe;

import net.xdabi.flappy.engine.renderer.MeshBuffer;
import net.xdabi.flappy.engine.renderer.RenderConfig;
import net.xdabi.flappy.engine.renderer.RenderInfo;
import net.xdabi.flappy.engine.renderer.ShaderProgram;

public class PipeRenderInfo extends RenderInfo {
    public PipeRenderInfo(RenderConfig config, ShaderProgram shader, MeshBuffer vbo) {
        super(config, shader, vbo);
    }

    @Override
    public void render() {
        PipeGenerator parent = (PipeGenerator) getParent();

        getConfig().enable();
        getShader().bind();
        getShader().updateUniforms(parent);

        for (int i = 0; i < PipeGenerator.MAX_PIPES; i++) {
            ((PipeShader) getShader()).updateUniforms(parent, i);
            getVbo().draw();
        }

        getConfig().disable();
    }
}
