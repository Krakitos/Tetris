package com.funtoginot.tetris.data;

import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.data.utils.TetrominoMoveSelector;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisBoard {

    public static final Color EMPTY_CELL = Color.BLACK;

    private final int width;
    private final int height;

    private Color[][] grid;

    public TetrisBoard(int defaultRowsNumber, int defaultColumnsNumber){
        width = defaultColumnsNumber;
        height = defaultRowsNumber;

        initGrid();
    }

    /**
     * Indique si le tetromino peut continuer sa descente
     * @param tetromino Le tetromino actuel
     * @param x La composante X du prochain mouvement du tetromino
     * @param y La composante Y du prochain mouvement du tetromino
     * @return Bitfield => 0 si aucun mouvement possible, voir ROTATE_LEFT, ROTATE_RIGHT, TRANSLATE_LEFT, TRANSLATE_RIGHT sinon
     */
    public TetrominoMoveSelector getAvailableMoves(Tetromino tetromino, int x, int y){
        TetrominoMoveSelector moves = new TetrominoMoveSelector();

        checkRight(moves, tetromino, x, y);
        checkLeft(moves, tetromino, x, y);
        checkBottom(moves, tetromino, x, y);
        checkRotations(moves, tetromino, x, y);

        return moves;
    }

    private void checkRight(TetrominoMoveSelector moves, Tetromino tetromino, int x, int y) {
        //Si on est pas entrain de sortir de l'écran
        if(x + tetromino.getWidth() == width || y == -1){
            moves.removeTranslateRight();
        }else {

            //Block nommé pour sortir de la boucle imbriquée
            loop :
            {
                for (int i = 0; i < tetromino.getWidth(); i++) {
                    for (int j = 0; j < tetromino.getHeight(); j++) {
                        //Si on a un carré à cette position
                        if (tetromino.hasSquareAt(i, j)) {
                            if (getColorAt(x + i + 1, y + j) != EMPTY_CELL) {
                                moves.removeTranslateRight();
                                break loop; //on sort de la boucle
                            }
                        }
                    }
                }

                moves.addTranslateRight();
            }
        }
    }

    private void checkLeft(TetrominoMoveSelector moves, Tetromino tetromino, int x, int y) {
        if(x == 0 || y == -1){
            moves.removeTranslateLeft();
        }else {
            //Block nommé pour sortir proprement de la double boucle imbriquée
            loop:
            {
                for (int i = 0; i < tetromino.getWidth(); i++) {
                    for (int j = 0; j < tetromino.getHeight(); j++) {
                        //Si on a un carré à cette position
                        if (tetromino.hasSquareAt(i, j)) {
                            //Et que la couleur n'est pas celle du cellule vide
                            if (getColorAt(x + i - 1, y + j) != EMPTY_CELL) {
                                moves.removeTranslateLeft();
                                break loop; //On sort de la boucle
                            }
                        }
                    }
                }
                moves.addTranslateLeft();
            }
        }
    }

    private void checkBottom(TetrominoMoveSelector moves, Tetromino tetromino, int x, int y) {
        if(y + tetromino.getHeight() == height){
            moves.removeTranslateBottom();
        }else{
            loop :
            {
                for (int i = 0; i < tetromino.getHeight(); i++) {
                    for (int j = 0; j < tetromino.getWidth(); j++) {
                        if (tetromino.hasSquareAt(j, i)) {
                            if (getColorAt(x + j, y + i + 1) != EMPTY_CELL) {
                                moves.removeTranslateBottom();
                                break loop;
                            }
                        }
                    }
                }
                moves.addTranslateBottom();
            }
        }
    }

    private void checkRotations(TetrominoMoveSelector moves, Tetromino tetromino, int x, int y) {
        //Si c'est un carré, la rotation est transparente
        if(tetromino.getHeight() == tetromino.getWidth()){
            moves.addRotateRight();
            moves.addRotateLeft();
        }else{

        }
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
                if(grid[i][j] == EMPTY_CELL){
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
        for (int i = 0; i < tetromino.getWorkingTetromino().getHeight(); i++) {
            for (int j = 0; j < tetromino.getWorkingTetromino().getWidth(); j++) {

                //Si un carré est défini à cette position dans la matrice
                if(tetromino.getWorkingTetromino().hasSquareAt(j, i)){

                    //On attribut la couleur
                    grid[tetromino.getRow() + i][tetromino.getColumn() + j] = tetromino.getWorkingTetromino().getColor();
                }
            }
        }
    }

    private void initGrid(){
        grid = new Color[height][width];
        for(Color[] rows : grid){
            Arrays.fill(rows, EMPTY_CELL);
        }

    }

    public Color getColorAt(int x, int y){
        return grid[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
