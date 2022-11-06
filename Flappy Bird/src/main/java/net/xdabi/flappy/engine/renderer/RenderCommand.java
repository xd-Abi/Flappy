package net.xdabi.flappy.engine.renderer;

import lombok.NoArgsConstructor;

import static org.lwjgl.opengl.GL11.*;

@NoArgsConstructor
public class RenderCommand {

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void setClearColor(float r, float g, float b, float a) {
        glClearColor(r, g, b, a);
    }

    public void setViewPort(int x, int y, int width, int height) {
        glViewport(x, y, width, height);
    }
}
