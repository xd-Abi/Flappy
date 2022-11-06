package net.xdabi.flappy.engine.math;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transform {

    private Vec3f translation;
    private Vec3f rotation;
    private Vec3f scaling;

    public Transform() {
        translation = new Vec3f(0, 0, 0);
        rotation = new Vec3f(0,0,0);
        scaling = new Vec3f(1, 1, 1);
    }

    public Matrix4f getWorldMatrix() {
        final Matrix4f matrix4f = new Matrix4f();
        return matrix4f.makeTransform(translation, rotation, scaling);
    }

}