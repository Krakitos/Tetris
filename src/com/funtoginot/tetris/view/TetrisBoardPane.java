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

    private static final Color DEFAULT_COLOR = Color.BLACK;

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
                Cell cell = getCellAt(sequence.getColumn() + i, sequence.getRow() + j);

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
