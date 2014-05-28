package com.funtoginot.tetris.controller;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.audio.AudioPlayer;
import com.funtoginot.tetris.view.TetrisMenuPane;
import com.funtoginot.tetris.view.TetrisView;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisController implements KeyListener, ActionListener{

    private static final String TETRIS_MAIN_THEME_FILE = "music/Tetris-Theme-Original.wav";

    private AudioPlayer audioPlayer;
    private TetrisEngine engine;
    private TetrisView view;

    public TetrisController() throws IOException {

        try {
            engine = new TetrisEngine(TetrisEngine.DEFAULT_ROWS_NUMBER, TetrisEngine.DEFAULT_COLUMNS_NUMBER);
            audioPlayer = new AudioPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.view = new TetrisView(engine);
        view.addKeyListener(this);
        view.addActionListener(this);
        engine.addObserver(view);
    }

    /**
     * Méthode de traitement pour la mise en pause du jeu
     */
    public void handlePauseAction(){
        audioPlayer.pause();
        engine.togglePause();
    }

    /**
     * Méthode de traitement pour démarrer le jeu
     */
    public void handleStartAction(){
        if(!engine.isPlaying()){
            engine.startGame();
            audioPlayer.play(new File(TETRIS_MAIN_THEME_FILE), Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(final KeyEvent e) {
        if(isValidKeyboardInput(e.getKeyCode())) {
            view.drawTetromino(engine.handleKeyPressed(e.getKeyCode()));
        }
    }

    /**
     * Verifie que la touche pressée par l'utilisateur est valide
     * @param keycode Le code de la touche pressée
     * @return True si la touche pressée est valide (mouvement autorisé), false sinon
     */
    private boolean isValidKeyboardInput(int keycode){
        return keycode == KeyEvent.VK_SPACE
                || keycode == KeyEvent.VK_DOWN
                || keycode == KeyEvent.VK_RIGHT
                || keycode == KeyEvent.VK_LEFT
                || keycode == KeyEvent.VK_UP
                || keycode == KeyEvent.VK_SPACE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component source = (Component)e.getSource();

        if(source.getName().equalsIgnoreCase(TetrisMenuPane.PLAY_BTN_NAME)){
            if(!engine.isPlaying()){
                handleStartAction();
            }else {
                handlePauseAction();
            }
        }else if(source.getName().equalsIgnoreCase(TetrisMenuPane.MUTE_BTN_NAME)){
            System.out.println("Mute");
        }
    }
}
