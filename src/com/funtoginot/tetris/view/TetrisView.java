package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.observers.TetrisObserver;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.event.KeyListener;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisView extends JFrame implements TetrisObserver {

    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 200;

    private static final String TITLE = "Tetris";

    private TetrisBoardPane boardPane;
    private TetrisMenu menuPane;

    private final TetrisEngine model;

    public TetrisView(TetrisEngine model){
        this.model = model;
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

        //On instancie les Jpanels
        boardPane = new TetrisBoardPane();
        menuPane = new TetrisMenu();

        /* Set default close action */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(menuPane);

        //frame.add(new JLabel("Hello"), BorderLayout.EAST);
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
        //Rafraichissement de la vue
        refreshUI(current);
    }

    public void drawTetromino(final TetrisEngine.MovementSequence sequence){
        boardPane.update(model.getBoard(), sequence);
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

    /**
     * Met à jour l'affichage en prenant en compte le contexte d'exécution actuel (Quel Thread execute cette methode)
     * La mise à jour des éléments graphiques ne pouvant se faire uniquement dans le processus d'affichage (Event Dispatching Thread)
     * De ce fait, cette méthode demandera toujours un rafraichissement dans l'UI Thread.
     * @param sequence Tetromino en cours de placement
     */
    private void refreshUI(final TetrisEngine.MovementSequence sequence){

        //Si l'appel est issu du processus d'affichage, on l'exécute.
        if(SwingUtilities.isEventDispatchThread()){
            boardPane.update(model.getBoard(), sequence);
        }else{ //Sinon on décharge le processus appellant de la mise à jour pour la faire dans le processus d'UI
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    refreshUI(sequence);
                }
            });
        }
    }
}
