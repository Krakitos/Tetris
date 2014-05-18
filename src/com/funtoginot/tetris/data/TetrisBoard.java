package com.funtoginot.tetris.data;

import com.funtoginot.tetris.data.tetrominos.Tetromino;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisBoard {

    public static final int ROTATE_LEFT = 1;
    public static final int ROTATE_RIGHT = 1 << 1;
    public static final int TRANSLATE_LEFT = 1 << 2;
    public static final int TRANSLATE_RIGHT = 1 << 3;
    public static final int TRANSLATE_BOTTOM = 1 << 4;
    public static final int ALL_MOVES_AVAILABLE = ROTATE_LEFT + ROTATE_RIGHT + TRANSLATE_LEFT + TRANSLATE_RIGHT + TRANSLATE_BOTTOM;

    private final int width;
    private final int height;

    private Color[][] grid;

    public TetrisBoard(int defaultRowsNumber, int defaultColumnsNumber){
        width = defaultRowsNumber;
        height = defaultColumnsNumber;

        initGrid();
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

        //Calcul du point le plus éloigné du point d'origine de la piece (haut gauche).
        Point bottomRight = new Point(x + tetromino.getWidth(), y + tetromino.getHeight());

        //Gestion des sorties de plateau
        if(bottomRight.y < height){

            //On est encore sur le plateau
            moves |= TRANSLATE_BOTTOM;
        }

        if(bottomRight.x < width){
            moves |= TRANSLATE_RIGHT;
        }

        if(x > 0){
            moves |= TRANSLATE_LEFT;
        }

        return moves;
    }

    /**
     * Détermine les lignes complètement remplies et les supprime.
     * Cette méthode par du bas du plateau de jeu pour remonter au fur et a mesure. Les blocs étant empilés de bas en
     * haut, procéder de cette manière permet d'éviter de traverser un bon nombre de lignes qui ne sont potentiellement
     * pas rempli
     * @return Renvoie un tableau avec les lignes complètement remplies supprimées, ou null si aucune ne l'a été
     */
    public int[] deleteRowsFull(){

        //Vide
        int[] rows = new int[0];

        for (int i = grid.length - 1; i >= 0; --i) {
            for (int j = 0; j < grid[i].length; j++) {
                //Si la valeur de la case est 0 (valeur par défaut), cette ligne n'st pas complète
                if(grid[i][j] == Color.BLACK){
                    continue;
                }

                //Si on arrive ici, c'est que l'on a eu une ligne complete, on peut l'ajouter
                int index = rows.length;

                //Augmente la capacité du tableau de 1
                rows = Arrays.copyOf(rows, index + 1);

                //Stocke la ligne
                rows[index] = j;
            }
        }

        return rows;
    }

    public void mergeTetromino(TetrisEngine.MovementSequence tetromino){
        for (int i = 0; i < tetromino.getWorkingTetromino().getWidth(); i++) {
            for (int j = 0; j < tetromino.getWorkingTetromino().getHeight(); j++) {

                //Si un carré est défini à cette position dans la matrice
                if(tetromino.getWorkingTetromino().hasSquareAt(i, j)){

                    //On attribut la couleur
                    grid[tetromino.getRow() + i][tetromino.getColumn() + j] = tetromino.getWorkingTetromino().getColor();
                }
            }
        }
    }

    private void initGrid(){
        grid = new Color[width][height];
        for(Color[] rows : grid){
            Arrays.fill(rows, Color.BLACK);
        }

    }

    public Color getColorAt(int x, int y){
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
