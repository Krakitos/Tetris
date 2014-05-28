package com.funtoginot.tetris.view.components;

import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Morgan on 28/05/2014.
 */
public class TetrominoPreview extends JPanel {
    private GridLayout layout = new GridLayout(4, 4, 2, 2);

    public TetrominoPreview(int width, int height){
        setLayout(layout);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel label = new JLabel();
                label.setBackground(Color.BLACK);
                label.setOpaque(true);
                add(label);
            }
        }
    }

    public void updateView(Tetromino tetromino){

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel cell = (JLabel) getComponent(j * 4 + i);
                if(i < tetromino.getWidth() && j < tetromino.getHeight() && tetromino.hasSquareAt(i, j)){
                    cell.setBackground(tetromino.getColor());
                }else{
                    cell.setBackground(Color.BLACK);
                }
            }
        }

        invalidate();
    }

}
