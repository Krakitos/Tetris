package com.funtoginot.tetris.view.components;

import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;

/**
 * Created by morgan on 23/05/14.
 */
public class TetrominoPreview extends JPanel {

    private GridLayout gridLayout = new GridLayout();

    public TetrominoPreview() {
        super();
        setLayout(gridLayout);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JLabel label = new JLabel();
                label.setForeground(Color.BLUE);
                //label.setOpaque(true);
                add(label);
            }
        }
    }

    public void updateView(Tetromino tetromino){
        System.out.println(getSize());

        gridLayout.setColumns(tetromino.getWidth());
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

        invalidate();
    }
}
