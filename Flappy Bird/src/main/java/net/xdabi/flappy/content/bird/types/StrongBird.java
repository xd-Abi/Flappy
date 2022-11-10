package net.xdabi.flappy.content.bird.types;

import net.xdabi.flappy.content.bird.Bird;
import net.xdabi.flappy.engine.renderer.Image;

public class StrongBird extends Bird {

    public static final float JUMP_VELOCITY = 0.20f;

    public StrongBird() {
        super(new Image("images/birds/strong.png"), DefaultBird.FALL_SPEED, JUMP_VELOCITY);
    }
}
