package net.xdabi.flappy.content.pipe;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.scenegraph.Node;

public class Pipe extends Node {

    public static final float Z_POS = 0.08f;

    @Getter
    @Setter
    private float initialTranslationX;

    public Pipe(float x, float y) {
        super();

        getWorldTransform().setTranslation(new Vec3f(x, y, Z_POS));
        getWorldTransform().setScaling(new Vec3f(2f, 5f, 1));
        initialTranslationX = x;
    }
}
