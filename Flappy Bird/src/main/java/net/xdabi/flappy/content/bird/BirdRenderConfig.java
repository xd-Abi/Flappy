package net.xdabi.flappy.content.bird;

import lombok.NoArgsConstructor;
import net.xdabi.flappy.engine.renderer.RenderConfig;

import static org.lwjgl.opengl.GL11.*;

@NoArgsConstructor
public class BirdRenderConfig implements RenderConfig {

    @Override
    public void enable() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void disable() {

    }
}
