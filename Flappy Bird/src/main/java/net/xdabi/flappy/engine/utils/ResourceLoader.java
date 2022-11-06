package net.xdabi.flappy.engine.utils;

import static org.lwjgl.stb.STBImage.*;

import net.xdabi.flappy.engine.renderer.RawImage;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.lwjgl.opengl.GL11.*;
public class ResourceLoader {

    public static InputStream createInputStream(String filepath) {
        return ResourceLoader.class.getClassLoader().getResourceAsStream(filepath);
    }

    public static String loadShader(String fileName) {
        InputStream inputStream = createInputStream(fileName);

        StringBuilder shaderSource = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shaderSource.toString();
    }

    public static int[] loadImage(String file) {

        ByteBuffer imageBuffer;
        try {
            imageBuffer = ioResourceToByteBuffer(file, 128 * 128);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        // Use info to read image metadata without decoding the entire image.
        if (!stbi_info_from_memory(imageBuffer, w, h, c)) {
            throw new RuntimeException("Failed to read image information: " + stbi_failure_reason());
        }

        // Decode the image
        ByteBuffer image = stbi_load_from_memory(imageBuffer, w, h, c, 0);
        if (image == null) {
            throw new RuntimeException("Failed to load image: " + stbi_failure_reason());
        }

        int width = w.get(0);
        int height = h.get(0);
        int comp = c.get(0);

        int texId = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, texId);

        if (comp == 3) {
            if ((width & 3) != 0) {
                glPixelStorei(GL_UNPACK_ALIGNMENT, 2 - (width & 1));
            }
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, image);
        } else {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
        }

        stbi_image_free(image);

        int[] data = {texId, w.get(), h.get()};

        return data;
    }

    public static RawImage loadRawImage(String filepath) {
        ByteBuffer imageBuffer;
        try {
            imageBuffer = ioResourceToByteBuffer(filepath, 128 * 128);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);

        // Use info to read image metadata without decoding the entire image.
        if (!stbi_info_from_memory(imageBuffer, w, h, c)) {
            throw new RuntimeException("Failed to read image information: " + stbi_failure_reason());
        }

        // Decode the image
        ByteBuffer image = stbi_load_from_memory(imageBuffer, w, h, c, 0);
        if (image == null) {
            throw new RuntimeException("Failed to load image: " + stbi_failure_reason());
        }

        return new RawImage(image, w.get(0), h.get(0));
    }

    public static ByteBuffer ioResourceToByteBuffer(String filepath, int bufferSize) throws IOException {

        ByteBuffer buffer;

        Path path = Paths.get(filepath);
        if (Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
                while (fc.read(buffer) != -1) {}
            }
        } else {
            try (
                    InputStream source = createInputStream(filepath);
                    ReadableByteChannel rbc = Channels.newChannel(source)
            ) {
                buffer = BufferUtils.createByteBuffer(bufferSize);

                while (true) {
                    int bytes = rbc.read(buffer);
                    if (bytes == -1) {
                        break;
                    }
                    if (buffer.remaining() == 0) {
                        buffer = BufferUtil.resizeBuffer(buffer, buffer.capacity() * 2);
                    }
                }
            }
        }

        buffer.flip();
        return buffer;
    }
}
