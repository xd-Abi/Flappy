package net.xdabi.flappy.content.bird.types;

import net.xdabi.flappy.content.bird.Bird;
import net.xdabi.flappy.engine.renderer.Image;

public class ReversyBird extends Bird {

    public static final float FALL_SPEED = -0.01f;
    public static final float JUMP_VELOCITY = -0.15f;

    public ReversyBird() {
        super(new Image("images/birds/reversy.png"), FALL_SPEED, JUMP_VELOCITY);
    }
}
