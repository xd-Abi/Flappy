package net.xdabi.flappy.content.bird.types;

import net.xdabi.flappy.content.bird.Bird;
import net.xdabi.flappy.engine.renderer.Image;

public class WeakBird extends Bird {

    public static final float JUMP_VELOCITY = 0.08f;

    public WeakBird() {
        super(new Image("images/birds/weak.png"), DefaultBird.FALL_SPEED, JUMP_VELOCITY);
    }
}
