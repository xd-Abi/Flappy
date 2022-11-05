package net.xdabi.flappy.content.pipe;

import net.xdabi.flappy.renderer.RenderConfig;
import net.xdabi.flappy.renderer.RenderInfo;
import net.xdabi.flappy.renderer.model.Mesh;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;

public class PipeGeneratorRenderInfo extends RenderInfo {


    public PipeGeneratorRenderInfo(RenderConfig config, ShaderProgram shader, Mesh mesh) {
        super(config, shader, mesh);
    }

    @Override
    public void render() {
        PipeGenerator parent = (PipeGenerator) getParent();

        getConfig().enable();
        getShader().bind();
        getShader().updateUniforms(parent);

        for (int i = 0; i < PipeGenerator.MAX_PIPES; i++) {
            ((PipeGeneratorShader) getShader()).updateUniforms(parent, i);
            getMesh().draw();
        }

        getConfig().disable();
    }
}
