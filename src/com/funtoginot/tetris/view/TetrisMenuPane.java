package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.view.components.TetrominoPreview;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by cdric on 23/05/2014.
 */
public class TetrisMenuPane extends JPanel {

    public static final Color DEFAULT_COLOR = Color.DARK_GRAY;

    private JPanel panelPreview;
    private JPanel panelB1;
    private JPanel panelB2;
    private TetrominoPreview preview;

    public TetrisMenuPane() {


        panelPreview = new JPanel();
        panelB1 = new JPanel();
        panelB2 = new JPanel();
        preview = new TetrominoPreview();

        panelPreview.setBackground(DEFAULT_COLOR);
        panelB1.setBackground(DEFAULT_COLOR);
        panelB2.setBackground(DEFAULT_COLOR);

        setLayout(new GridBagLayout());


        GridBagConstraints c;
        final Insets buttonInsets = new Insets(10, 10, 10, 10);

        panelPreview.setBorder(BorderFactory.createTitledBorder(null, "NEXT", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("arial", Font.PLAIN, 12), Color.white));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = c.gridy = 0;
        c.gridwidth = c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        panelPreview.add(preview);
        c.weightx = c.weighty = 70;
        add(panelPreview, c);


        //Ajout d'un 1er bouton
        JButton boutonStart = new JButton(new ImageIcon("/Users/cdric/Google Drive/Documents/Polytech/S6 Polytech/Algo Prog 2/Tetris/src/com/funtoginot/tetris/view/images/StartButton.png"));
        boutonStart.setPreferredSize(new Dimension(100, 40));

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = buttonInsets;
        c.gridx = 0;
        c.gridy = 1;

        panelB1.add(boutonStart);
        c.weightx = c.weighty = 30;
        add(panelB1, c);

        //Ajout d'un 2ème bouton
        JButton boutonPause = new JButton(new ImageIcon("/Users/cdric/Google Drive/Documents/Polytech/S6 Polytech/Algo Prog 2/Tetris/src/com/funtoginot/tetris/view/images/pauselogo.jpg"));
        boutonPause.setPreferredSize(new Dimension(100, 40));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;

        panelB2.add(boutonPause);
        c.weightx = c.weighty = 30;
        add(panelB2, c);



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

    public void updateNextTetromino(Tetromino tetromino) {
        preview.updateView(tetromino);
    }
}
