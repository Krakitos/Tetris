package com.funtoginot.tetris.data.audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Morgan on 27/05/2014.
 */
public class AudioPlayer {
    private boolean isPaused = false;
    private List<Clip> musics;

    public AudioPlayer(){
        musics = new LinkedList<>();
    }

    public void play(File file, int loop){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(loop);
            clip.start();

            musics.add(clip);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pause(){
        if(isPaused){
            unpause();
        }else {
            for (Clip clip : musics) {
                //On stocke la position actuelle
                int position = clip.getFramePosition();

                //On stoppe
                clip.stop();

                //On replace le flux
                clip.setFramePosition(position);
            }
        }

        isPaused = !isPaused;
    }

    private void unpause(){
        for(Clip clip : musics){
            clip.start();
        }
    }
}
