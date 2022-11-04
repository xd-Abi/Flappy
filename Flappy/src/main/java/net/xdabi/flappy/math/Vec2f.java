package net.xdabi.flappy.math;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vec2f {

    private float x;
    private float y;

    public Vec2f() {
        x = 0;
        y = 0;
    }

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f(Vec2f v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(Vec2f r) {
        return x * r.getX() + y * r.getY();
    }

    public Vec2f normalize() {
        float length = length();

        x /= length;
        y /= length;

        return this;
    }

    public Vec2f add(Vec2f r) {
        return new Vec2f(this.x + r.getX(), this.y + r.getY());
    }

    public Vec2f add(float r) {
        return new Vec2f(this.x + r, this.y + r);
    }

    public Vec2f sub(Vec2f r) {
        return new Vec2f(this.x - r.getX(), this.y - r.getY());
    }

    public Vec2f sub(float r) {
        return new Vec2f(this.x - r, this.y - r);
    }

    public Vec2f mul(Vec2f r) {
        return new Vec2f(this.x * r.getX(), this.y * r.getY());
    }

    public Vec2f mul(float r) {
        return new Vec2f(this.x * r, this.y * r);
    }

    public Vec2f div(Vec2f r) {
        return new Vec2f(this.x / r.getX(), this.y / r.getY());
    }

    public Vec2f div(float r) {
        return new Vec2f(this.x / r, this.y / r);
    }

    public String toString() {
        return "[" + this.x + "," + this.y + "]";
    }
}
