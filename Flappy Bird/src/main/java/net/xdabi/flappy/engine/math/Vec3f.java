package net.xdabi.flappy.engine.math;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vec3f {

    private float x;
    private float y;
    private float z;

    public Vec3f() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3f(float r) {
        this.x = r;
        this.y = r;
        this.z = r;
    }

    public Vec3f(Vec3f v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vec3f r) {
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }

    public Vec3f cross(Vec3f r) {
        float x = y * r.getZ() - z * r.getY();
        float y = z * r.getX() - x * r.getZ();
        float z = x * r.getY() - y * r.getX();

        return new Vec3f(x, y, z);
    }

    public Vec3f normalize() {
        float length = this.length();

        x /= length;
        y /= length;
        z /= length;

        return this;
    }

    public Vec3f rotate(float angle, Vec3f axis) {
        float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

        float rX = axis.getX() * sinHalfAngle;
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getZ() * sinHalfAngle;
        float rW = cosHalfAngle;

        Vec4f rotation = new Vec4f(rX, rY, rZ, rW);
        Vec4f conjugate = rotation.conjugate();

        Vec4f w = rotation.mul(this).mul(conjugate);

        x = w.getX();
        y = w.getY();
        z = w.getZ();

        return this;
    }

    public Vec3f add(Vec3f r) {
        return new Vec3f(this.x + r.getX(), this.y + r.getY(), this.z + r.getZ());
    }

    public Vec3f add(float r) {
        return new Vec3f(this.x + r, this.y + r, this.z + r);
    }

    public Vec3f sub(Vec3f r) {
        return new Vec3f(this.x - r.getX(), this.y - r.getY(), this.z - r.getZ());
    }

    public Vec3f sub(float r) {
        return new Vec3f(this.x - r, this.y - r, this.z - r);
    }

    public Vec3f mul(Vec3f r) {
        return new Vec3f(this.x * r.getX(), this.y * r.getY(), this.z * r.getZ());
    }

    public Vec3f mul(float x, float y, float z) {
        return new Vec3f(this.x * x, this.y * y, this.z * z);
    }

    public Vec3f mul(float r) {
        return new Vec3f(this.x * r, this.y * r, this.z * r);
    }

    public Vec3f div(Vec3f r) {
        return new Vec3f(this.x / r.getX(), this.y / r.getY(), this.getZ() / r.getZ());
    }

    public Vec3f div(float r) {
        return new Vec3f(this.x / r, this.y / r, this.z / r);
    }

    public Vec3f abs() {
        return new Vec3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public boolean equals(Vec3f v) {
        if (x == v.getX() && y == v.getY() && z == v.getZ())
            return true;
        else return false;
    }

    public String toString() {
        return "[" + this.x + "," + this.y + "," + this.z + "]";
    }

}