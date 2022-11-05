package net.xdabi.flappy.content;

import lombok.Getter;
import net.xdabi.flappy.content.background.Background;
import net.xdabi.flappy.content.effects.VignetteEffect;
import net.xdabi.flappy.content.pipe.PipeGenerator;
import net.xdabi.flappy.kernel.Application;
import net.xdabi.flappy.kernel.Input;
import net.xdabi.flappy.kernel.Window;
import net.xdabi.flappy.math.Matrix4f;
import net.xdabi.flappy.renderer.Camera;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.content.bird.Bird;
import org.lwjgl.glfw.GLFW;

public class Level extends Renderable {

    @Getter
    private boolean isGameStarted;

    private final Input input;

    public Level() {
        super();

        isGameStarted = false;
        input = Application.getInstance().getInput();

        Matrix4f orthographicProjection = new Matrix4f().orthographicProjection(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
        addComponent(NodeComponentType.CAMERA, new Camera(orthographicProjection));

        addChild(new Background());
        addChild(new PipeGenerator());
        addChild(new VignetteEffect());
        addChild(new Bird());

        // When the window aspect ratio changes, the game objects need to change their aspect ratios.
        // At the moment, there is no way event callback system. Therefore, we need to check the current aspect ratio of the window
        // and change the aspect ratio properly to be 16:9
        Window window = Application.getInstance().getWindow();
        GLFW.glfwSetWindowSizeLimits(window.getId(), 1280, 700, GLFW.GLFW_DONT_CARE, GLFW.GLFW_DONT_CARE);
        GLFW.glfwSetWindowAspectRatio(window.getId(), 16, 9);
    }

    @Override
    public void update() {
        if (input.isKeyPushed(GLFW.GLFW_KEY_SPACE)) {
            isGameStarted = true;
        }

        if (isGameStarted) {
            super.update();
        }

    }
}
