package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.TetrisBoard;
import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.view.cell.Cell;
import com.funtoginot.tetris.view.layout.TetrisLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdric on 15/05/2014.
 */

public class TetrisBoardPane extends JPanel {

    public static final int GRID_ROWS = TetrisEngine.DEFAULT_ROWS_NUMBER;
    public static final int GRID_COLS = TetrisEngine.DEFAULT_COLUMNS_NUMBER;

    public static final Color DEFAULT_COLOR = Color.BLACK;

    public TetrisBoardPane() {
        int index = 0;
        setLayout(new TetrisLayout(GRID_ROWS, GRID_COLS));
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                Color color = DEFAULT_COLOR;
                add(new Cell(color), new Point(col, row));
                index++;
            }
            index++;
        }
    }

    public void update(TetrisBoard board, TetrisEngine.MovementSequence sequence){

        refreshBoardView(board);

        //Mise à jour du tetromino
        for (int i = 0; i < sequence.getWorkingTetromino().getWidth(); i++) {
            for (int j = 0; j < sequence.getWorkingTetromino().getHeight(); j++) {
                Cell cell = (Cell) getComponent((i + sequence.getRow()) * GRID_COLS + (j + sequence.getColumn()));

                if(sequence.getWorkingTetromino().hasSquareAt(i, j)){
                    cell.setBackground(sequence.getWorkingTetromino().getColor());
                }
            }
        }
    }

    /**
     * Supprime les lignes du plateau
     * @param rows Les lignes à supprimer
     */
    public void deleteRow(int[] rows){

    }

    /**
     * Détermine la couleur à appliquer à la cellule {x,y}
     * @param x Coordonnée en x sur le plateau
     * @param y Coordonnée en y sur le plateau
     * @param cell La cellule à colorier
     * @param sequence Sequence de jeu
     * @return Couleur à appliquer
     */
    private Color computeCellColor(int x, int y, Cell cell,  TetrisEngine.MovementSequence sequence){

        if(inside(x, y, sequence)){

        }

        return cell.getBackground() == null ? DEFAULT_COLOR : cell.getBackground();
    }

    /**
     * Indique si le couple coordonnées x, sont recouvertes par le tetromino
     * @param x Coordonnée en x sur le plateau
     * @param y Coordonnée en y sur le plateau
     * @param tetromino Sequence de jeu
     * @return True si le couple coordonnées sont recouvertes par le tetromino
     */
    private boolean inside(int x, int y, TetrisEngine.MovementSequence tetromino){
        return false;
    }

    /**
     * Met à jour l'affichage en fonction du model sans tenir compte du tetromino en cours de placement
     */
    private void refreshBoardView(TetrisBoard board){
        int width = board.getWidth();
        int height = board.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = (Cell) getComponent(i * GRID_COLS + j);
                cell.setBackground(board.getColorAt(i, j));
            }
        }
    }
}
