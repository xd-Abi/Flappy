package net.xdabi.flappy.engine.renderer;

import static org.lwjgl.opengl.GL11.*;

import lombok.Getter;
import net.xdabi.flappy.engine.scenegraph.NodeComponent;
import net.xdabi.flappy.engine.utils.ResourceLoader;

@Getter
public class Image extends NodeComponent {
    private final int id;
    private final int width;
    private final int height;

    public Image(String fileName) {
        int[] data = ResourceLoader.loadImage(fileName);
        id = data[0];
        width = data[1];
        height = data[2];
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    @Override
    public void delete() {
        glDeleteTextures(id);
    }

    public void noFilter() {
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    }
}
