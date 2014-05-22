package com.funtoginot.tetris.view.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by cdric on 15/05/2014.
 */
public class QuitterAction extends AbstractAction {

    public QuitterAction(String texte){
        super(texte);
    }

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
