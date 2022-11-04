package net.xdabi.flappy.kernel;

import org.lwjgl.glfw.GLFWKeyCallback;
import  static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Input  {

    private Set<Integer> pushedKeys;
    private Set<Integer> keysHolding;
    private Set<Integer> releasedKeys;
    public Input(Window window) {
        pushedKeys = new HashSet<>();
        keysHolding = new HashSet<>();
        releasedKeys = new HashSet<>();


        glfwSetKeyCallback(window.getId(), new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW_PRESS) {
                    if (!pushedKeys.contains(key)) {
                        pushedKeys.add(key);
                        keysHolding.add(key);
                    }
                }
                else if (action == GLFW_RELEASE) {
                    keysHolding.remove(key);
                    releasedKeys.add(key);
                }
            }
        });
    }

    public void update() {
        pushedKeys.clear();
        releasedKeys.clear();
    }

    public boolean isKeyPushed(int keycode) {
        return pushedKeys.contains(keycode);
    }

    public boolean isKeyReleased(int keycode) {
        return releasedKeys.contains(keycode);
    }

    public boolean isKeyHolding(int keycode) {
        return keysHolding.contains(keycode);
    }

}
