package net.xdabi.flappy.image;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import lombok.Getter;
import net.xdabi.flappy.util.ImageLoader;

@Getter
public class Image {

    private final int id;
    private final int width;
    private final int height;

    public Image(String fileName) {
        int[] data = ImageLoader.loadImage(fileName);
        id = data[0];
        width = data[1];
        height = data[2];
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void delete() {
        glDeleteTextures(id);
    }

    public void noFilter() {
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    }
}
