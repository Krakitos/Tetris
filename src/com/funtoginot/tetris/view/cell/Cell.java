package com.funtoginot.tetris.view.cell;

import javax.swing.*;
import java.awt.*;


/**
 * Created by cdric on 15/05/2014.
 */

public class Cell extends JLabel {

    private Point point;
    private Component component;


    public Cell(Color background) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(background);
        setOpaque(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(25, 25);
    }
}