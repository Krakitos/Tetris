package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.observer.TetrisObserver;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisView extends JFrame implements TetrisObserver {

    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 700;

    public TetrisView(){
        configureWindow();
    }

    private void configureWindow() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Set window size */
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        /* Center the window */
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((int)(screen.getWidth() - getWidth())/2), ((int)(screen.getHeight() - getHeight())/2));

        /* Set default close action */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /* Set layout manager */
        setLayout(new BorderLayout());

        /* Show window */
        setVisible(true);
    }

    @Override
    public void onCurrentTetrominoChanged(Tetromino tetromino) {

    }

    @Override
    public void onFullRowsDeleted(int[] rows) {

    }

    @Override
    public void onTimerTick(int delay, Tetromino current) {

    }

    @Override
    public void onPointsChanged(int points) {

    }
}
