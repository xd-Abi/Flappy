package net.xdabi.flappy.config;

import lombok.NoArgsConstructor;
import net.xdabi.flappy.engine.renderer.RenderConfig;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

@NoArgsConstructor
public class GlobalRenderConfig implements RenderConfig {

    @Override
    public void enable() {
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
    }

    @Override
    public void disable() {

    }
}
