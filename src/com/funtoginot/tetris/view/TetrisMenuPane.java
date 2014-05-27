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
        panelPreview.setLayout(new BorderLayout());

        GridBagConstraints c;
        final Insets buttonInsets = new Insets(10, 10, 10, 10);

        panelPreview.setBorder(BorderFactory.createTitledBorder(null, "NEXT", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("arial", Font.PLAIN, 12), Color.white));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = c.gridy = 0;
        c.gridwidth = c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        panelPreview.add(preview, BorderLayout.CENTER);
        c.weightx = c.weighty = 70;
        add(panelPreview, c);


        //Ajout d'un 1er bouton
        JButton boutonStart = new JButton(new ImageIcon("images/StartButton.png"));
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
        JButton boutonPause = new JButton(new ImageIcon("images/pauselogo.jpg"));
        boutonPause.setPreferredSize(new Dimension(100, 40));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;

        panelB2.add(boutonPause);
        c.weightx = c.weighty = 30;
        add(panelB2, c);

        setPreferredSize(new Dimension(300, 0));
    }

    public void updateNextTetromino(Tetromino tetromino) {
        preview.updateView(tetromino);
    }
}
