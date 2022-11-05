package net.xdabi.flappy.config;

import lombok.NoArgsConstructor;
import net.xdabi.flappy.renderer.RenderConfig;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

@NoArgsConstructor
public class GlobalRenderConfig implements RenderConfig {

    @Override
    public void enable() {
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(1, 1, 1, 1);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void disable() {

    }
}
