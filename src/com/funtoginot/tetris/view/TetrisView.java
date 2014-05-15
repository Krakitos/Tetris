package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.observers.TetrisObserver;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisView extends JFrame implements TetrisObserver {

    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 200;

    private static final String TITLE = "Tetris";

    public TetrisView(){
        configureWindow();
    }

    private void configureWindow() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Show window */
        JFrame frame = new JFrame(TITLE);

        /* Add Menu */
        JMenuBar menuBar = new JMenuBar();

        JMenu menu1 = new JMenu("Quitter");

        JMenuItem quitter = new JMenuItem();
        menu1.add(quitter);

        menuBar.add(menu1);

        JMenu menu2 = new JMenu("?");

        JMenuItem aPropos = new JMenuItem();
        menu2.add(aPropos);

        menuBar.add(menu2);

        frame.setJMenuBar(menuBar);

        /* Set window size */
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        /* Center the window
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(((int) (screen.getWidth() - getWidth()) / 2), ((int) (screen.getHeight() - getHeight()) / 2));*/

        /* Set default close action */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TetrisBoardPane());
        frame.add(new JLabel("Hello"), BorderLayout.EAST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void onCurrentTetrominoChanged(Tetromino tetromino) {

    }

    @Override
    public void onFullRowsDeleted(int[] rows) {

    }

    @Override
    public void onTimerTick(int delay, TetrisEngine.MovementSequence current) {

    }

    @Override
    public void onPointsChanged(int points) {

    }

    @Override
    public void onGameStarted(Tetromino current, Tetromino next) {

    }

    @Override
    public void onGamePaused() {

    }

    @Override
    public void onGameUnPaused() {

    }
}
