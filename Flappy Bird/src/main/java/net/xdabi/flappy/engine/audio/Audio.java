package net.xdabi.flappy.engine.audio;

import static org.lwjgl.openal.AL10.*;

import lombok.Getter;
import net.xdabi.flappy.engine.utils.BufferUtil;
import net.xdabi.flappy.engine.utils.SoundUtils;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.nio.IntBuffer;

public class Audio {

    @Getter
    private IntBuffer buffer;

    @Getter
    private int source;

    @Getter
    private long time;

    public Audio(String soundPath) {
        buffer = BufferUtil.createIntBuffer(1);
        alGenBuffers(buffer);

        try
        {
            SoundUtils.createBufferData(buffer.get(0), soundPath);
            time = (buffer.get(0) + 1);
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            e.printStackTrace();
        }

        source = alGenSources();
        alSourcei(source, AL_BUFFER, buffer.get(0));
        alSource3f(source, AL_POSITION, 0, 0, 0f);
        alSource3f(source, AL_VELOCITY, 0f, 0f, 0f);

        alSourcef(source, AL_PITCH, 1f);
        alSourcef(source, AL_GAIN, 1f);
        alSourcei(source, AL_LOOPING, AL_FALSE);
    }

    public void play() {
        alSourcePlay(source);
    }

    public void pause() {
        alSourcePause(source);
    }

    public void stop() {
        alSourceStop(source);
    }

    public void delete() {
        stop();
        alDeleteBuffers(buffer);
        alDeleteBuffers(source);
    }

    public void setLoop(boolean loop)
    {
        alSourcei(source, AL_LOOPING, loop ? AL_TRUE : AL_FALSE);
    }

}

