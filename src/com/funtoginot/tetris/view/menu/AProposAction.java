package com.funtoginot.tetris.view.menu;

import com.funtoginot.tetris.view.TetrisView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by cdric on 15/05/2014.
 */

public class AProposAction extends AbstractAction {
    private TetrisView fenetre;

    public AProposAction(TetrisView fenetre, String texte){

        super(texte);

        this.fenetre = fenetre;
    }

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(fenetre, "Ce Tetris a été développé par Morgan FUNTOWICZ et Cédric GINOT");
    }
}
