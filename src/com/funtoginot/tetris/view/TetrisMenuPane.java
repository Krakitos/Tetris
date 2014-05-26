package com.funtoginot.tetris.view;

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


        preview = new TetrominoPreview();
        setLayout(new GridBagLayout());

        GridBagConstraints c;
        final Insets buttonInsets = new Insets(10, 10, 10, 10);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(preview, c);

        //Ajout d'un 1er bouton
        JButton boutonStart = new JButton("START");

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = buttonInsets;
        c.gridx = 0;
        c.gridy = 1;
        preview = new TetrominoPreview();
        add(boutonStart, c);

        //Ajout d'un 2ème bouton
        JButton boutonQuit = new JButton("QUIT");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        preview = new TetrominoPreview();
        add(boutonQuit, c);



        /*
        //Ajout d'un 1er bouton
        JButton boutonStart = new JButton("START");

        //Ajout d'un 2ème bouton
        JButton boutonQuit = new JButton("QUIT");

        //Deux lignes sur une colonne (preview du tetromino suivant + boutons partie en cours)
        GridLayout grid = new GridLayout(2,1);
        setLayout(grid);

        preview = new TetrominoPreview();
        //On crée et ajoute les deux Panels qui vont êtres insérés dans les deux lignes du GridLayout
        partieCours = new JPanel();
        boutons = new JPanel();

        //Partie preview
        GridLayout gridPreview = new GridLayout(1,1);
        partieCours.setLayout(gridPreview);

        // Partie boutons
        GridLayout gridInt = new GridLayout(2,1,8,8);
        boutons.setLayout(gridInt);


        //On ajoute les éléments souhaités
        partieCours.add(preview);
        boutons.add(boutonStart);
        boutons.add(boutonQuit);

        add(partieCours);
        add(boutons);

*/


    }

    public void updateNextTetromino(Tetromino tetromino){
        preview.updateView(tetromino);
    }
}
