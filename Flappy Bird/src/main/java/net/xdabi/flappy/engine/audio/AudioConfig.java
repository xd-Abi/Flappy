package net.xdabi.flappy.engine.audio;

import lombok.NoArgsConstructor;
import net.xdabi.flappy.engine.utils.BufferUtil;
import org.lwjgl.openal.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@NoArgsConstructor
public class AudioConfig {

    private long device;
    private long context;

    public void enable() {
        device = ALC10.alcOpenDevice((ByteBuffer) null);
        ALCCapabilities deviceCapabilities = ALC.createCapabilities(device);
        context = ALC10.alcCreateContext(device, createOpenALContext());

        if (!ALC10.alcMakeContextCurrent(context)) {
            throw new RuntimeException("Failed to create OpenAL context");
        }

        AL.createCapabilities(deviceCapabilities);
    }

    private IntBuffer createOpenALContext() {
        IntBuffer openALContext = BufferUtil.createIntBuffer(16);

        openALContext.put(ALC10.ALC_REFRESH);
        openALContext.put(60);

        openALContext.put(ALC10.ALC_SYNC);
        openALContext.put(ALC10.ALC_FALSE);

        openALContext.put(EXTEfx.ALC_MAX_AUXILIARY_SENDS);
        openALContext.put(2);

        openALContext.put(0);
        openALContext.flip();

        return openALContext;
    }

    public void disable() {
        ALC10.alcDestroyContext(context);
        ALC10.alcCloseDevice(device);
    }
}
