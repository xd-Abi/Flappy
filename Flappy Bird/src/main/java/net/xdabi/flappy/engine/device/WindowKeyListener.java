package net.xdabi.flappy.engine.device;

public interface WindowKeyListener extends WindowListener {

    void onKeyPress(Integer keyCode);

    void onKeyRelease(Integer keyCode);
}
