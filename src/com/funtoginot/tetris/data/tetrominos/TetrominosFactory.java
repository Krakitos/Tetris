package com.funtoginot.tetris.data.tetrominos;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrominosFactory {

    private static final byte[][][] tetrominos = new byte[7][][];
    private static final Color[] tetrominos_colors = new Color[7];

    static{
        //I
        tetrominos[0] = new byte[][]{{1,1,1,1},{0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        tetrominos_colors[0] = Color.RED;

        //O
        tetrominos[1] = new byte[][]{{1,1},{1,1}};
        tetrominos_colors[1] = Color.BLUE;

        //T
        tetrominos[2] = new byte[][]{{1, 1, 1}, {0, 1, 0}, {0, 0, 0}};
        tetrominos_colors[2] = Color.GRAY;

        //L
        tetrominos[3] = new byte[][]{{1, 0, 0}, {1, 0, 0}, {1, 1, 0}};
        tetrominos_colors[3] = Color.MAGENTA;

        //J
        tetrominos[4] = new byte[][]{{0, 0, 1}, {0, 0, 1}, {0, 1, 1}};
        tetrominos_colors[4] = Color.WHITE;

        //S
        tetrominos[5] = new byte[][]{{0, 1, 1}, {1, 1, 0}, {0, 0, 0}};
        tetrominos_colors[5] = Color.GREEN;

        //Z
        tetrominos[6] = new byte[][]{{1, 1, 0}, {0, 1, 1}, {0, 0, 0}};
        tetrominos_colors[6] = Color.CYAN;
    }

    private static TetrominosFactory ourInstance = new TetrominosFactory();

    private TetrominosFactory() {

    }

    public Tetromino getTetromino(){
        int index = (int)(Math.random() * (tetrominos.length - 1));

        return new Tetromino(tetrominos_colors[index], cloneTetromino(index));
    }

    public static TetrominosFactory getInstance() {
        return ourInstance;
    }

    private byte[][] cloneTetromino(int index) {
        byte[][] result = new byte[tetrominos[index].length][];

        for (int i = 0; i < result.length; i++) {
            result[i] = Arrays.copyOf(tetrominos[index][i], tetrominos[index][i].length);
        }

        return result;
    }
}
