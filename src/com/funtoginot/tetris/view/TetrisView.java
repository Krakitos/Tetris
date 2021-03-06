package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.TetrisEngine;
import com.funtoginot.tetris.data.observers.TetrisObserver;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created by Morgan on 14/05/2014.
 */

public class TetrisView extends JFrame implements TetrisObserver {


    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;
    public static final Color DEFAULT_COLOR = Color.DARK_GRAY;

    private static final String TITLE = "Tetris ©";

    private TetrisBoardPane centre;
    private TetrisMenuPane droite;

    private final TetrisEngine model;

    public TetrisView(TetrisEngine model) throws IOException {
        super(TITLE);

        this.model = model;
        configureWindow();
    }

    private void configureWindow() throws IOException {


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Set default close action */
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /* Add Menu */
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

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

        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Procédure Quitter");
                TetrisView.this.dispose();
                System.exit(0);
            }
        });
        fileMenu.add(exitAction);

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
        //droite.setSize(400, 0);
        droite.setBackground(DEFAULT_COLOR);

        add(centre, BorderLayout.CENTER);
        add(droite, BorderLayout.EAST);

        droite.setPreferredSize(new Dimension(150, getHeight()));

        pack();

        centerWindow();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                centre.requestFocus();
            }
        });

        setResizable(false);
        setVisible(true);
    }


    public void addKeyListener(KeyListener listener) {
        centre.setFocusable(true);
        centre.addKeyListener(listener);
    }

    public void addActionListener(ActionListener listener){
        droite.addActionListener(listener);
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
        droite.updateScore(points);
    }

    @Override
    public void onLevelChanged(int level) {
        droite.updateLevel(level);
    }

    @Override
    public void onGameStarted(TetrisEngine.MovementSequence current, Tetromino next) {
        droite.onGameStart();
    }

    @Override
    public void onGameOver(int points, int level) {
        JOptionPane.showMessageDialog(this, "Partie terminée ! Niveau : " + level + " - Points : " + points, "Perdu ! ", JOptionPane.INFORMATION_MESSAGE);
        droite.onGameOver();
    }

    @Override
    public void onGamePaused() {
        droite.onGamePause();
    }

    @Override
    public void onGameUnPaused() {
        droite.onGameUnPaused();
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

    private void centerWindow(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screen.getWidth() - getWidth()) /2;
        int y = (int) (screen.getHeight() - getHeight()) / 2;
        setLocation(x, y);
    }
}
