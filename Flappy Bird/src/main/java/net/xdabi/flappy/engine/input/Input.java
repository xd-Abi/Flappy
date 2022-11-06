package net.xdabi.flappy.engine.input;


import net.xdabi.flappy.engine.device.WindowKeyListener;

import java.util.HashSet;
import java.util.Set;

public class Input implements WindowKeyListener {

    private Set<Integer> pushedKeys;
    private Set<Integer> keysHolding;
    private Set<Integer> releasedKeys;

    public Input() {
        pushedKeys = new HashSet<>();
        keysHolding = new HashSet<>();
        releasedKeys = new HashSet<>();
    }

    @Override
    public void onKeyPress(Integer keyCode) {
        if (!pushedKeys.contains(keyCode)) {
            pushedKeys.add(keyCode);
            keysHolding.add(keyCode);
        }
    }

    @Override
    public void onKeyRelease(Integer keyCode) {
        keysHolding.remove(keyCode);
        releasedKeys.add(keyCode);
    }

    public void update() {
        pushedKeys.clear();
        releasedKeys.clear();
    }

    public boolean isKeyPushed(Integer keycode) {
        return pushedKeys.contains(keycode);
    }

    public boolean isKeyReleased(Integer keycode) {
        return releasedKeys.contains(keycode);
    }

    public boolean isKeyHolding(Integer keycode) {
        return keysHolding.contains(keycode);
    }
}
