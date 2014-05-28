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
        if(x + tetromino.getWidth() >= width || y == -1){
            moves.removeTranslateRight();
        }else {

            //Block nommé pour sortir de la boucle imbriquée
            loop :
            {
                for (int i = 0; i < tetromino.getWidth() && x + i  < width; i++) {
                    for (int j = 0; j < tetromino.getHeight() && y + j < height; j++) {
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
        if(x <= 0 || y == -1){
            moves.removeTranslateLeft();
        }else {
            //Block nommé pour sortir proprement de la double boucle imbriquée
            loop:
            {
                for (int i = 0; i < tetromino.getWidth() && x + i < width; i++) {
                    for (int j = 0; j < tetromino.getHeight() && y + j < height; j++) {
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
        if(y + tetromino.getHeight() >= height){
            moves.removeTranslateBottom();
        }else{
            loop :
            {
                for (int i = 0; i < tetromino.getHeight() && y + i < height; i++) {
                    for (int j = 0; j < tetromino.getWidth() && x + j < width; j++) {
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
            TetrominoMoveSelector rotations = new TetrominoMoveSelector();

            //Rotation à gauche
            tetromino.rotateLeft();

            checkBottom(rotations, tetromino, x, y);
            checkLeft(rotations, tetromino, x, y);
            checkRight(rotations, tetromino, x, y);

            if(rotations.canTranslateBottom() && rotations.canTranslateLeft() && rotations.canTranslateRight()){
                moves.addRotateLeft();
            }

            //Rotation a droite
            tetromino.rotateRight();
            tetromino.rotateRight();

            rotations.clear();

            checkBottom(rotations, tetromino, x, y);
            checkLeft(rotations, tetromino, x, y);
            checkRight(rotations, tetromino, x, y);

            if(rotations.canTranslateBottom() && rotations.canTranslateLeft() && rotations.canTranslateRight()){
                moves.addRotateRight();
            }

            //On remet en place
            tetromino.rotateLeft();
        }
    }

    /**
     * Détermine les lignes complètement remplies et les supprime.
     * Cette méthode par du bas du plateau de jeu pour remonter au fur et a mesure. Les blocs étant empilés de bas en
     * haut, procéder de cette manière permet d'éviter de traverser un bon nombre de lignes qui ne sont potentiellement
     * pas rempli
     * @return Renvoie le nombre de lignes complètement remplies supprimées, ou 0 si aucune ne l'a été
     */
    public int checkFullRows(){
        int totalFullRows = 0; //Nombre de ligne complètes sur le plateau

        int row = -1; //Numéro de ligne
        int count = 0; // Nombre de ligne complète successives

        for (int i = 0; i < height; i++) {
            if(isRowFull(i)){
                if(row == -1){
                    row = i;
                }
                ++count;
                ++totalFullRows;
            }else{
                if(row > -1 && count > 0){
                    dropLine(row, count);
                    row = -1;
                    count = 0;
                }
            }
        }

        //Dans le cas de la dernière itération de la boucle, pas de passage dans else possible.
        if(row > -1 && count > 0){
            dropLine(row, count);
        }

        return totalFullRows;
    }

    /**
     * Incruste un tetromino sur le plateau
     * @param tetromino Le tetromino à placer
     */
    public void mergeTetromino(TetrisEngine.MovementSequence tetromino){

        for (int i = 0; i < tetromino.getWorkingTetromino().getWidth(); i++) {
            for (int j = 0; j < tetromino.getWorkingTetromino().getHeight(); j++) {

                //Si un carré est défini à cette position dans la matrice
                if(tetromino.getWorkingTetromino().hasSquareAt(i, j)){

                    //On attribut la couleur
                    setColorAt(tetromino.getColumn() + i, tetromino.getRow() + j, tetromino.getWorkingTetromino().getColor());
                }
            }
        }
    }

    /**
     * Initialise la grille
     */
    private void initGrid(){
        grid = new Color[height][width];
        for(Color[] rows : grid){
            Arrays.fill(rows, EMPTY_CELL);
        }

    }

    /**
     * Défini la couleur de la cellule (x, y)
     * @param x La composante X de la cellule
     * @param y La composante Y de la cellule
     * @param color La couleur à appliquer
     */
    private void setColorAt(int x, int y, Color color){
        grid[y][x] = color;
    }

    /**
     * Renvoie la couleur de la cellule (x, y)
     * @param x La composante X de la cellule
     * @param y La composante Y de la cellule
     * @return La couleur de la cellule
     */
    public Color getColorAt(int x, int y){
        return grid[y][x];
    }

    /**
     * Indique si la ligne est complète
     * @param y Ligne à vérifier
     * @return True si plein, false sinon
     */
    private boolean isRowFull(int y){
        boolean result = true;

        for (int i = 0; i < width; i++) {
            result &= (getColorAt(i, y) != EMPTY_CELL);
        }

        return result;
    }

    /**
     * Supprime les lignes
     * @param rowStart La première ligne
     * @param count Le nombre de ligne successives
     */
    private void dropLine(int rowStart, int count){
        //Toutes les lignes avant rowStart
        for (int i = rowStart - 1; i >= 0; i--) {

            //Pour toutes les cases
            for (int j = 0; j < width; j++) {

                //On les abaisses
                Color color = getColorAt(j, i);
                setColorAt(j, i + count, color);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int drop(TetrisEngine.MovementSequence sequence) {
        int lastRow = sequence.getRow();

        TetrominoMoveSelector moveSelector = new TetrominoMoveSelector();

        do{
            checkBottom(moveSelector, sequence.getWorkingTetromino(), sequence.getColumn(), ++lastRow);
        }while(moveSelector.canTranslateBottom());

        return lastRow;
    }

    public void reset() {
        for(Color[] rows : grid){
            Arrays.fill(rows, EMPTY_CELL);
        }
    }
}
