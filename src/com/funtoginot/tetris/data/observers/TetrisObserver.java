package com.funtoginot.tetris.data.observers;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

/**
 * Created by Morgan on 14/05/2014.
 */
public interface TetrisObserver {


    /**
     * Indique un changement du tetromino à placer
     * @param current Le nouveau tetromino à placer
     * @param tetromino Le prochain tetromino
     */
    public void onCurrentTetrominoChanged(TetrisEngine.MovementSequence current, Tetromino tetromino);

    /**
     * Indique qu'une ou plusieurs lignes sont complètement remplies
     * @param rows Les lignes complètement remplies
     */
    public void onFullRowsDeleted(int[] rows);

    /**
     * Indique qu'une unité de temps s'est écoulée. Cette méthode est typiquement utilisée pour gérer le déplacement
     * des tetrominos sur le plateau ainsi que la mise à jour d'un compteur de temps.
     * @param delay Le temps écoulé depuis le dernier tick
     * @param current Le tetromino actuellement en cours de placement
     */
    public void onTimerTick(int delay, TetrisEngine.MovementSequence current);

    /**
     * Indique un changement dans le nombre de points
     * @param points Le nombre de points actuel
     */
    public void onPointsChanged(int points);

    /**
     * Indique le début d'une partie
     * @param current Le tetromino actuellement en cours de placement
     * @param next Le prochain tetromino
     */
    public void onGameStarted(TetrisEngine.MovementSequence current, Tetromino next);

    /**
     * Indique que le jeu à été mis en pause
     */
    public void onGamePaused();

    /**
     * Indique que le jeu à repris
     */
    public void onGameUnPaused();
}
