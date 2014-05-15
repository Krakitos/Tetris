package com.funtoginot.tetris.data.tetrominos;

import java.awt.*;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrominosFactory {

    private static final byte[][][] tetrominos = new byte[7][][];
    private static final Color[] tetrominos_colors = new Color[7];

    static{
        tetrominos[0] = new byte[][]{{1,1,1,1},{0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        tetrominos_colors[0] = Color.RED;

        tetrominos[1] = null;
        tetrominos_colors[1] = Color.BLUE;

        tetrominos[2] = null;
        tetrominos_colors[2] = Color.GRAY;

        tetrominos[3] = null;
        tetrominos_colors[3] = Color.MAGENTA;

        tetrominos[4] = null;
        tetrominos_colors[4] = Color.WHITE;

        tetrominos[5] = null;
        tetrominos_colors[5] = Color.GREEN;

        tetrominos[6] = null;
        tetrominos_colors[6] = Color.CYAN;
    }

    private static TetrominosFactory ourInstance = new TetrominosFactory();

    private TetrominosFactory() {

    }

    public Tetromino getTetromino(){
        int index = (int)(Math.random() * (tetrominos.length - 1));

        return new Tetromino(tetrominos_colors[index], new byte[][]{{1,1,1,1},{0,0,0,0}, {0,0,0,0}, {0,0,0,0}});
    }

    public static TetrominosFactory getInstance() {
        return ourInstance;
    }
}
