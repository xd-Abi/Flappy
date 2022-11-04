package net.xdabi.flappy.math;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vec4f {

    private float x;
    private float y;
    private float z;
    private float w;

    public Vec4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4f(Vec3f v, float w) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
        this.w = w;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vec4f normalize() {
        float length = length();

        x /= length;
        y /= length;
        z /= length;
        w /= length;

        return this;
    }

    public Vec4f conjugate() {
        return new Vec4f(-x, -y, -z, w);
    }

    public Vec4f mul(Vec4f r) {
        float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();

        return new Vec4f(x_, y_, z_, w_);
    }

    public Vec4f mul(Vec3f r) {
        float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ = w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = w * r.getZ() + x * r.getY() - y * r.getX();

        return new Vec4f(x_, y_, z_, w_);
    }

    public Vec4f div(float r) {
        float w_ = w / r;
        float x_ = x / r;
        float y_ = y / r;
        float z_ = z / r;
        return new Vec4f(x_, y_, z_, w_);
    }

    public Vec4f mul(float r) {
        float w_ = w * r;
        float x_ = x * r;
        float y_ = y * r;
        float z_ = z * r;
        return new Vec4f(x_, y_, z_, w_);
    }

    public Vec4f sub(Vec4f r) {
        float w_ = w - r.getW();
        float x_ = x - r.getX();
        float y_ = y - r.getY();
        float z_ = z - r.getZ();
        return new Vec4f(x_, y_, z_, w_);
    }

    public Vec4f add(Vec4f r) {
        float w_ = w + r.getW();
        float x_ = x + r.getX();
        float y_ = y + r.getY();
        float z_ = z + r.getZ();
        return new Vec4f(x_, y_, z_, w_);
    }

    public Vec3f xyz() {
        return new Vec3f(x, y, z);
    }

    public String toString() {
        return "[" + this.x + "," + this.y + "," + this.z + "," + this.w + "]";
    }
}
