package com.funtoginot.tetris.data.audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Morgan on 27/05/2014.
 */
public class AudioPlayer {
    private List<AudioWrapper> waitingMusics;
    private AudioThread audioThread;

    public AudioPlayer(){
        waitingMusics = new LinkedList<>();
        audioThread = new AudioThread();
        new Thread(audioThread).start();
    }

    public void play(File file, boolean loop){
        waitingMusics.add(new AudioWrapper(file, loop));
    }

    public void pause(){
        audioThread.pause(true);
    }

    public void unpause(){
        audioThread.pause(false);
    }

    private class AudioThread implements Runnable{

        private AtomicBoolean run = new AtomicBoolean(true);
        private AtomicBoolean paused = new AtomicBoolean(false);
        private List<AudioWrapper> runningMusics = new LinkedList<>();

        @Override
        public void run() {
            while(run.get()){
                if(!paused.get()){

                    if(waitingMusics.size() > 0) {
                        Iterator<AudioWrapper> itr = waitingMusics.iterator();
                        while (itr.hasNext()){
                            AudioWrapper wrapper = itr.next();
                            try {
                                wrapper.openStream();
                                runningMusics.add(wrapper);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }finally {
                                itr.remove();
                            }
                        }
                    }

                    for(AudioWrapper wrapper : runningMusics){
                        if(!wrapper.getClip().isRunning()){
                            wrapper.getClip().start();
                        }
                    }
                }else{
                    for(AudioWrapper wrapper : runningMusics){
                        wrapper.getClip().stop();
                    }
                }
            }
        }

        public void pause(boolean value){
            paused.set(value);
        }

        public void stop(){
            run.set(false);
        }

        private void loadMusic(AudioWrapper entry){
            try {
                entry.openStream();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }
}
