package net.xdabi.flappy.config;

import net.xdabi.flappy.engine.math.Matrix4f;

public class StaticCamera {

    public static Matrix4f PROJECTION = new Matrix4f().orthographicProjection(-10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 1.0f);
}
