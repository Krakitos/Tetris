package com.funtoginot.tetris.data;

import com.funtoginot.tetris.data.tetrominos.Tetromino;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisBoard {

    public static final int ROTATE_LEFT = 1;
    public static final int ROTATE_RIGHT = 1 << 1;
    public static final int TRANSLATE_LEFT = 1 << 2;
    public static final int TRANSLATE_RIGHT = 1 << 3;
    public static final int TRANSLATE_BOTTOM = 1 << 4;

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
    public boolean gravityTest(Tetromino tetromino, int x, int y){
        return false;
    }

    /**
     * Indique si le tetromino peut continuer sa descente
     * @param tetromino Le tetromino actuel
     * @param x La composante X du prochain mouvement du tetromino
     * @param y La composante Y du prochain mouvement du tetromino
     * @return Bitfield => 0 si aucun mouvement possible, voir ROTATE_LEFT, ROTATE_RIGHT, TRANSLATE_LEFT, TRANSLATE_RIGHT sinon
     */
    public byte getAvailableMoves(Tetromino tetromino, int x, int y){
        byte moves = 0;

        //Si on touches un tetromino, alors y n'a plus d'espoir ...
        if(!gravityTest(tetromino, x, y)){

        }

        return moves;
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
