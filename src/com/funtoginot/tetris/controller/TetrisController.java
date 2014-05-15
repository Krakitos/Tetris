package com.funtoginot.tetris.controller;

import com.funtoginot.tetris.data.TetrisEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisController implements KeyListener {

    private TetrisEngine engine;

    /**
     * Méthode de traitement pour la mise en pause du jeu
     */
    public void handlePauseAction(){
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
    public void keyTyped(KeyEvent e) {
        if(isValidKeyboardInput(e.getKeyCode())) {
            engine.handleKeyPressed(e.getKeyCode());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

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
                || keycode == KeyEvent.VK_LEFT;
    }
}
