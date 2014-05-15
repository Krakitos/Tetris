package com.funtoginot.tetris.data;

import com.funtoginot.tetris.data.tetrominos.Tetromino;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisBoard {

    private final int width;
    private final int height;

    private int[][] grid;

    public TetrisBoard(int defaultRowsNumber, int defaultColumnsNumber){
        width = defaultRowsNumber;
        height = defaultColumnsNumber;

        grid = new int[width][height];
    }

    /**
     * Indique si le tetromino actuel est en contact avec un ou plusieurs autres tetrominos déjà placés
     * @param tetromino Le tetromino actuellement en cours de placement
     * @return True si en colision, false sinon
     */
    public boolean hitTestTetrominos(Tetromino tetromino){
        return false;
    }

    /**
     * Indique si le tetromino peut continuer sa descente
     * @param tetromino Le tetromino actuel
     * @param x La composante X du prochain mouvement du tetromino
     * @param y La composante Y du prochain mouvement du tetromino
     * @return True si le mouvement est possible, false sinon
     */
    public boolean canGoOn(Tetromino tetromino, int x, int y){
        return false;
    }

    /**
     * Détermine les lignes complètement remplies et les supprime
     * @return Renvoie un tableau avec les lignes complètement remplies supprimées, ou null si aucune ne l'a été
     */
    public int[] deleteRowsFull(){
        return null;
    }

    public void mergeTetromino(TetrisEngine.MovementSequence tetromino){

    }

    public int getColorAt(int x, int y){
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Supprime une ligne dans la
     * @param row
     */
    private void removeRow(int row){

    }
}
