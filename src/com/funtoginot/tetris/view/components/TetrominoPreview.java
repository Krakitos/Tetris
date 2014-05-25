package com.funtoginot.tetris.view.components;

import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.view.cell.Cell;

import javax.swing.*;
import java.awt.*;

/**
 * Created by morgan on 23/05/14.
 */
public class TetrominoPreview extends JPanel {

    private GridLayout gridLayout = new GridLayout(1,1);

    public TetrominoPreview() {
        super();
        setLayout(gridLayout);

        Cell cell = new Cell(Color.BLUE);
        add(cell);
    }

    public void updateView(Tetromino tetromino){
        /*gridLayout.setColumns(tetromino.getWidth());
        gridLayout.setRows(tetromino.getHeight());

        setLayout(gridLayout);

        for (int i = 0; i < tetromino.getWidth(); i++) {
            for (int j = 0; j < tetromino.getHeight(); j++) {
                if(tetromino.hasSquareAt(i, j)){
                    JLabel label = new JLabel();
                    label.setForeground(tetromino.getColor());
                    label.setOpaque(true);
                    add(label);
                }else{
                    add(new JLabel());
                }
            }
        }

        doLayout();
        invalidate();*/
    }
}
