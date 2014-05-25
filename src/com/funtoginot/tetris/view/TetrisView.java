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

    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 600;

    private static final String TITLE = "Tetris";

    private TetrisBoardPane centre;
    private TetrisMenuPane droite;
    //private TetrisMenu menuPane;

    private final TetrisEngine model;

    public TetrisView(TetrisEngine model) {
        this.model = model;
        configureWindow();
    }

    private void configureWindow() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Set default close action */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Show window */
        setTitle("Tetris");

        /* Add Menu */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("Fichier");
        JMenu questMenu = new JMenu("?");
        menuBar.add(fileMenu);
        menuBar.add(questMenu);

        // Create and add simple menu item to one of the drop down menu
        JMenuItem newAction = new JMenuItem("Nouvelle Partie");
        JMenuItem exitAction = new JMenuItem("Quitter");
        JMenuItem aproposAction = new JMenuItem("A Propos");

        fileMenu.add(newAction);
        fileMenu.add(exitAction);
        questMenu.add(aproposAction);

        /* Center the window
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(((int) (screen.getWidth() - getWidth()) / 2), ((int) (screen.getHeight() - getHeight()) / 2));*/

        //On instancie les Jpanels
        centre = new TetrisBoardPane();
        droite = new TetrisMenuPane();
        // menuPane = new TetrisMenu();


        setLayout(new BorderLayout());

        add(centre, BorderLayout.CENTER);
        add(droite, BorderLayout.LINE_END);

        pack();

        //Centrer la fenêtre
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);

        /* Set window size */
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    }




    public void addKeyListener(KeyListener listener) {
        centre.setFocusable(true);
        centre.addKeyListener(listener);
    }

    @Override
    public void onCurrentTetrominoChanged(TetrisEngine.MovementSequence current, final Tetromino tetromino) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                  droite.updateNextTetromino(tetromino);
            }
        });
    }

    @Override
    public void onFullRowsDeleted(final int[] rows) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                centre.deleteRow(model.getBoard(), rows);
            }
        });
    }

    @Override
    public void onTimerTick(int delay, final TetrisEngine.MovementSequence current) {
        //Rafraichissement de la vue
        refreshUI(current);
    }

    public void drawTetromino(final TetrisEngine.MovementSequence sequence) {
        centre.update(model.getBoard(), sequence);
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
     *
     * @param sequence Tetromino en cours de placement
     */
    private void refreshUI(final TetrisEngine.MovementSequence sequence) {

        //Si l'appel est issu du processus d'affichage, on l'exécute.
        if (SwingUtilities.isEventDispatchThread()) {
            centre.update(model.getBoard(), sequence);
        } else { //Sinon on décharge le processus appellant de la mise à jour pour la faire dans le processus d'UI
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    refreshUI(sequence);
                }
            });
        }
    }
}
