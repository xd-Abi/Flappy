package net.xdabi.flappy.kernel;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glViewport;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

public class Window {

    private static int windowCount = 0;

    @Getter
    private long id;

    @Getter
    @Setter
    private int width;

    @Getter
    @Setter
    private int height;

    @Getter
    private final String title;

    @Getter
    private boolean VSync;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (windowCount == 0) {
            if (!glfwInit()) {
                throw new RuntimeException("Failed to initialize GLFW");
            }

            glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        id = glfwCreateWindow(width, height, title, 0, 0);

        if (id == 0) {
            throw new RuntimeException("Failed to create window");
        }

        glfwShowWindow(id);
        glfwMakeContextCurrent(id);
        glfwSetWindowSizeLimits(id, 1280, 700, GLFW_DONT_CARE, GLFW_DONT_CARE);
        glfwSetWindowAspectRatio(id, 16, 9);
        GL.createCapabilities();

        // callbacks
        glfwSetWindowSizeCallback(id, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                setWidth(width);
                setHeight(height);

                glViewport(0, 0, width, height);
            }
        });

        windowCount++;
    }

    public void destroy() {
        glfwDestroyWindow(id);
        windowCount--;

        if (windowCount == 0) {
            glfwTerminate();
        }
    }

    public void update() {
        glfwPollEvents();
    }

    public void draw() {
        glfwSwapBuffers(id);
    }

    public boolean isCloseRequested() {
        return glfwWindowShouldClose(id);
    }

    public void setVSync(boolean enabled) {
        glfwSwapInterval(enabled ? 1 : 0);
        VSync = enabled;
    }
}
