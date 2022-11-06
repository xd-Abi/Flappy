package net.xdabi.flappy.content.background;

import net.xdabi.flappy.engine.renderer.MeshBuffer;
import net.xdabi.flappy.engine.renderer.RenderConfig;
import net.xdabi.flappy.engine.renderer.RenderInfo;
import net.xdabi.flappy.engine.renderer.ShaderProgram;

public class BackgroundRenderInfo extends RenderInfo {


    public BackgroundRenderInfo(RenderConfig config, ShaderProgram shader, MeshBuffer vbo) {
        super(config, shader, vbo);
    }

    @Override
    public void render() {
        Background parent = (Background) getParent();

        getConfig().enable();
        getShader().bind();
        getShader().updateUniforms(parent);

        for (int i = 0; i < Background.MAX_DRAW_INDEX; i++) {
            ((BackgroundShader) getShader()).updateUniforms(parent, i);
            getVbo().draw();
        }

        getConfig().disable();
    }
}