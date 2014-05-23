package com.funtoginot.tetris.view.menu;

import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.view.components.TetrominoPreview;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdric on 23/05/2014.
 */
public class TetrisMenuPane extends JPanel {

    private JPanel partieCours;
    private JPanel boutons;

    private TetrominoPreview preview;

    public TetrisMenuPane() {

        //Ajout d'un 1er bouton
        JButton boutonStart = new JButton("START");

        //Ajout d'un 2ème bouton
        JButton boutonQuit = new JButton("QUIT");

        //Deux lignes sur une colonne (une partie pour les infos de la partie en cours)
        setLayout(new GridLayout(2, 1));

        //On crée et ajoute les deux Panels qui vont êtres insérés dans les deux lignes du GridLayout
        partieCours = new JPanel();
        preview = new TetrominoPreview();
        boutons = new JPanel();

        //A l'intérieur de ces derniers, on insère deux GridLayout
        partieCours.setLayout(new GridLayout(1, 1));
        boutons.setLayout(new GridLayout(2, 1));

        //On ajoute les éléments souhaités
        partieCours.add(preview);
        boutons.add(boutonStart);
        boutons.add(boutonQuit);

        add(partieCours);
        add(boutons);
    }

    public void updateNextTetromino(Tetromino tetromino){
        preview.updateView(tetromino);
    }
}
