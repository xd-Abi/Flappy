package net.xdabi.flappy.content.background;

import net.xdabi.flappy.renderer.RenderConfig;
import net.xdabi.flappy.renderer.RenderInfo;
import net.xdabi.flappy.renderer.model.Mesh;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;

public class BackgroundRenderInfo extends RenderInfo {


    public BackgroundRenderInfo(RenderConfig config, ShaderProgram shader, Mesh mesh) {
        super(config, shader, mesh);
    }

    @Override
    public void render() {
        Background parent = (Background) getParent();

        getConfig().enable();
        getShader().bind();
        getShader().updateUniforms(parent);

        for (int i = 0; i < parent.getDrawAmount() + 4; i++) {
            ((BackgroundShader) getShader()).updateUniforms(parent, i);
            getMesh().draw();
        }

        getConfig().disable();
    }
}
