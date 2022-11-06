package net.xdabi.flappy.engine.device;

import static org.lwjgl.glfw.GLFW.*;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.engine.renderer.RawImage;
import net.xdabi.flappy.engine.utils.ResourceLoader;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.List;

public class Window {

    public static final int MAX_SIZE = GLFW_DONT_CARE;

    @Getter
    private long id;

    @Getter
    private int width;

    @Getter
    private int height;

    @Getter
    private final String title;

    @Getter
    private boolean VSync;

    @Getter
    private List<WindowListener> listeners;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.VSync = false;
        this.listeners = new ArrayList<>();
    }

    public void create() {
        // Setup GLFW
        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize GLFW");
        }

        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        // Set window hints. The min required OpenGL version is 3.2
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        // Create window
        id = glfwCreateWindow(width, height, title, 0, 0);
        if (id == 0) {
            throw new RuntimeException("Failed to create window");
        }

        glfwMakeContextCurrent(id);
        GL.createCapabilities();

        // Setup callbacks
        glfwSetWindowSizeCallback(id, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long windowId, int newWidth, int newHeight) {


                for (WindowListener listener : listeners) {
                    if (listener instanceof WindowSizeListener) {
                        ((WindowSizeListener) listener).onWindowSizeChange(newWidth, newHeight);
                    }
                }

                width = newWidth;
                height = newHeight;
            }
        });

        glfwSetKeyCallback(id, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int keyCode, int scancode, int action, int mods) {

                for (WindowListener listener : listeners) {
                    if (listener instanceof WindowKeyListener) {

                        if (action == GLFW_PRESS) {
                            ((WindowKeyListener) listener).onKeyPress(keyCode);
                        } else if (action == GLFW_RELEASE) {
                            ((WindowKeyListener) listener).onKeyRelease(keyCode);
                        }
                    }
                }
            }
        });
    }

    public void destroy() {
        glfwDestroyWindow(id);
        glfwTerminate();
    }

    public void show() {
        glfwShowWindow(id);
    }

    public void update() {
        glfwPollEvents();
    }

    public void draw() {
        glfwSwapBuffers(id);
    }

    public void addListener(WindowListener listener) {
        this.listeners.add(listener);
    }

    public boolean isCloseRequested() {
        return glfwWindowShouldClose(id);
    }

    public void setMinMaxSize(int minWidth, int minHeight, int maxWidth, int maxHeight) {
        glfwSetWindowSizeLimits(id, minWidth, minHeight, maxWidth, maxHeight);
    }

    public void setIcon(String filepath) {
        RawImage rawImage = ResourceLoader.loadRawImage(filepath);
        GLFWImage image = GLFWImage.malloc();
        image.set(rawImage.width(), rawImage.height(), rawImage.image());

        GLFWImage.Buffer images = GLFWImage.malloc(1);
        images.put(0, image);

        glfwSetWindowIcon(id, images);
    }

    public float getAspectRatio() {
        return (float) width / (float) height;
    }

    public void setAspectRatio(int numer, int denom) {
        glfwSetWindowAspectRatio(id, numer, denom);

    }

    public void setVSync(boolean VSync) {
        this.VSync = VSync;
        glfwSwapInterval(VSync ? 1 : 0);
    }
}
