package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.observers.TetrisObserver;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Created by Morgan on 14/05/2014.
 */

public class TetrisView extends JFrame implements TetrisObserver {


    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 600;
    public static final Color DEFAULT_COLOR = Color.DARK_GRAY;

    private static final String TITLE = "Tetris";

    private TetrisBoardPane centre;
    private TetrisMenuPane droite;

    private final TetrisEngine model;

    public TetrisView(TetrisEngine model) throws IOException {
        this.model = model;
        configureWindow();
    }

    private void configureWindow() throws IOException {


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame(TITLE);
       /* ImagePanel panel = new ImagePanel(new ImageIcon("/Users/cdric/Google Drive/Documents/Polytech/S6 Polytech/Algo Prog 2/Tetris/src/com/funtoginot/tetris/content/images/background.jpg").getImage());
        frame.getContentPane().add(panel);*/

        /* Set default close action */
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /* Add Menu */
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

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

        exitAction.setMnemonic('x');
        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Procédure Quitter");
                TetrisView.this.dispose();
                System.exit(0);
            }
        });
        fileMenu.add(exitAction);

        aproposAction.setMnemonic('a');
        aproposAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Procédure A Propos");
                //Boîte du message d'information
                JOptionPane jop1;
                jop1 = new JOptionPane();
                jop1.showMessageDialog(null, "Tetris développé par Morgan FUNTOWICZ et Cédric GINOT" +
                        "", "A Propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        questMenu.add(aproposAction);

        //On instancie les JPanels
        centre = new TetrisBoardPane();
        droite = new TetrisMenuPane();
        droite.setBackground(DEFAULT_COLOR);

        frame.add(centre, BorderLayout.CENTER);
        frame.add(droite, BorderLayout.EAST);


        frame.pack();


       // frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


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
