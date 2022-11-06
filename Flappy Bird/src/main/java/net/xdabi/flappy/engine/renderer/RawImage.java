package net.xdabi.flappy.engine.renderer;

import lombok.Getter;

import java.nio.ByteBuffer;

public record RawImage(@Getter ByteBuffer image, @Getter int width, @Getter int height) {
}
