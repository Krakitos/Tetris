package com.funtoginot.tetris.view.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdric on 23/05/2014.
 */
public class TetrisMenuPane extends JPanel {


    public TetrisMenuPane() {

        //Ajout du premier Label
        JLabel label = new JLabel("Tetris Game");

        //Ajout d'un 1er bouton
        JButton boutonStart = new JButton("START");

        //Ajout d'un 2ème bouton
        JButton boutonQuit = new JButton("QUIT");

        //Trois lignes sur deux colonnes
        setLayout(new GridLayout(3, 1));


        //On ajoute le bouton au content pane de la JFrame
        add(label);
        add(boutonStart);
        add(boutonQuit);

    }
}
