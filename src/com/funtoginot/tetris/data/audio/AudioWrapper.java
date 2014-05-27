package com.funtoginot.tetris.data.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Morgan on 27/05/2014.
 */
public class AudioWrapper {

    private File audioFile;
    private AudioInputStream stream;
    private Clip clip;
    private boolean loop;

    public AudioWrapper(File audioFile, boolean loop) {
        this.audioFile = audioFile;
        this.loop = loop;
    }

    public File getAudioFile() {
        return audioFile;
    }

    public AudioInputStream getStream() {
        return stream;
    }

    public Clip getClip(){ return clip; }

    public boolean isLoop() {
        return loop;
    }

    public void openStream() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        stream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(stream);
        clip.loop(loop ? Clip.LOOP_CONTINUOUSLY : 1);
    }
}
