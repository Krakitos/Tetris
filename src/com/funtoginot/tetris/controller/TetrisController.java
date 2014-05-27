package com.funtoginot.tetris.controller;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.audio.AudioPlayer;
import com.funtoginot.tetris.view.TetrisView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisController implements KeyListener {

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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(final KeyEvent e) {
        if(isValidKeyboardInput(e.getKeyCode())) {
            view.drawTetromino(engine.handleKeyPressed(e.getKeyCode()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

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
                || keycode == KeyEvent.VK_UP;
    }
}
