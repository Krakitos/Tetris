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
        this(GRID_COLS, GRID_ROWS);
    }

    public TetrisBoardPane(int width, int height) {
        setLayout(new TetrisLayout(width, height));

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = DEFAULT_COLOR;
                add(new Cell(color), new Point(col, row));
            }
        }
    }

    public void update(TetrisBoard board, TetrisEngine.MovementSequence sequence){

        refreshBoardView(board);

        if(sequence != null) {
            //Mise à jour du tetromino
            for (int i = 0; i < sequence.getWorkingTetromino().getWidth(); i++) {
                for (int j = 0; j < sequence.getWorkingTetromino().getHeight(); j++) {
                    if (sequence.getRow() >= 0) {
                        Cell cell = getCellAt(sequence.getColumn() + i, sequence.getRow() + j);

                        if (sequence.getWorkingTetromino().hasSquareAt(i, j)) {
                            cell.setBackground(sequence.getWorkingTetromino().getColor());
                        }
                    }
                }
            }
        }
    }

    /**
     * Supprime les lignes du plateau
     * @param rows Les lignes à supprimer
     */
    public void deleteRow(TetrisBoard board, int[] rows){

        //Pour chaque ligne du tableau
        for (int i = 0; i < rows.length; i++) {

            //On met à jour toutes les cellules
            for (int j = 0; j < board.getWidth(); j++) {
                Cell cell = getCellAt(j, i);
                cell.setBackground(DEFAULT_COLOR);
            }
        }

        refreshBoardView(board);
    }

    /**
     * Met à jour l'affichage en fonction du model sans tenir compte du tetromino en cours de placement
     */
    private void refreshBoardView(TetrisBoard board){
        int width = board.getWidth();
        int height = board.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = getCellAt(i, j);
                cell.setBackground(board.getColorAt(i, j));
            }
        }
    }

    private Cell getCellAt(int x, int y){
        return (Cell) getComponent(y * GRID_COLS + x);
    }
}
