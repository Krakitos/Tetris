package com.funtoginot.tetris.view;

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
        setLayout(new TetrisLayout(GRID_ROWS,GRID_COLS));
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                Color color = DEFAULT_COLOR;
                add(new Cell(color), new Point(col, row));
                index++;
            }
            index++;
        }
    }
}
