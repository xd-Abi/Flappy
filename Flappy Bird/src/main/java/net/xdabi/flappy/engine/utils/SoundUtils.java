package net.xdabi.flappy.engine.utils;

import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SoundUtils {

    public static long createBufferData(int p, String soundPath) throws UnsupportedAudioFileException, IOException {

        final int MONO = 1, STEREO = 2;

        InputStream audioSrc = ResourceLoader.createInputStream(soundPath);
        assert audioSrc != null;
        InputStream bufferedIn = new BufferedInputStream(audioSrc);
        AudioInputStream stream = AudioSystem.getAudioInputStream(bufferedIn);


        AudioFormat format = stream.getFormat();
        if (format.isBigEndian()) throw new UnsupportedAudioFileException("Can't handle Big Endian formats yet");

        int openALFormat = -1;
        switch (format.getChannels()) {
            case MONO:
                switch (format.getSampleSizeInBits()) {
                    case 8:
                        openALFormat = AL10.AL_FORMAT_MONO8;
                        break;
                    case 16:
                        openALFormat = AL10.AL_FORMAT_MONO16;
                        break;
                }
                break;
            case STEREO:
                switch (format.getSampleSizeInBits()) {
                    case 8:
                        openALFormat = AL10.AL_FORMAT_STEREO8;
                        break;
                    case 16:
                        openALFormat = AL10.AL_FORMAT_STEREO16;
                        break;
                    default:
                        break;
                }
                break;
        }



        byte[] b = IOUtils.toByteArray(stream);

        ByteBuffer data = BufferUtils.createByteBuffer(b.length).put(b);
        data.flip();

        AL10.alBufferData(p, openALFormat, data, (int) format.getSampleRate());

        return (long) (1000f * stream.getFrameLength() / format.getFrameRate());
    }
}