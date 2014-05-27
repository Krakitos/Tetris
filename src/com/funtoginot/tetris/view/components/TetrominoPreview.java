
package com.funtoginot.tetris.view.components;

import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.view.TetrisBoardPane;
import com.funtoginot.tetris.view.cell.Cell;
import com.funtoginot.tetris.view.layout.TetrisLayout;

import java.awt.*;

/**
 * Created by morgan on 23/05/14.
 */
public class TetrominoPreview extends TetrisBoardPane {

    private TetrisLayout gridLayout = new TetrisLayout(4,4);

    public TetrominoPreview() {
        super(4, 4);
    }

    public void updateView(Tetromino tetromino){

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Cell cell = (Cell) getComponent(j * 4 + i);
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
