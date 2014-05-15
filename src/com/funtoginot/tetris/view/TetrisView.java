package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.observers.TetrisObserver;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisView extends JFrame implements TetrisObserver {

    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 200;

    private static final String TITLE = "Tetris";

    private TetrisBoardPane boardPane;

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

        boardPane = new TetrisBoardPane();
        /* Set default close action */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(boardPane);
        frame.add(new JLabel("Hello"), BorderLayout.EAST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addKeyListener(KeyListener listener){
        boardPane.setFocusable(true);
        boardPane.addKeyListener(listener);
    }

    @Override
    public void onCurrentTetrominoChanged(TetrisEngine.MovementSequence current, Tetromino tetromino) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //TODO : Impl
            }
        });
    }

    @Override
    public void onFullRowsDeleted(final int[] rows) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardPane.deleteRow(rows);
            }
        });
    }

    @Override
    public void onTimerTick(int delay, final TetrisEngine.MovementSequence current) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boardPane.drawTetromino(current);
            }
        });
    }

    public void drawTetromino(final TetrisEngine.MovementSequence sequence){
        boardPane.drawTetromino(sequence);
    }

    @Override
    public void onPointsChanged(int points) {

    }

    @Override
    public void onGameStarted(TetrisEngine.MovementSequence current, Tetromino next) {

    }

    @Override
    public void onGamePaused() {

    }

    @Override
    public void onGameUnPaused() {

    }
}
