package com.funtoginot.tetris.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by cdric on 22/05/2014.
 */


public class TetrisMenu extends JPanel implements ActionListener {

    JButton startG,exitG;
    JPanel panel;
    ImageIcon ii;
    JLabel picture;

    public TetrisMenu()
    {

        //Start Game Button
        startG = new JButton("Start Game");
        startG.setBounds(50, 100, 100, 30);
        startG.setFocusable(false);
        add(startG);


        //Exit Game Button
        exitG = new JButton("Exit Game");
        exitG.setBounds(50,200,100,30);
        exitG.setFocusable(false);
       add(exitG);

        //Add background
//        ii = new ImageIcon(this.getClass().getResource("1.jpg"));
        //picture = new JLabel(new ImageIcon(ii.getImage()));
       // add(picture);

        startG.addActionListener(this);
        exitG.addActionListener(this);


        setSize(200,400);
        setVisible(true);
    }



    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == startG)
        {
            System.out.println("New Game - button is working");


        }
        else if(e.getSource() == exitG)
        {
            System.out.println("Game is exiting - button is working");
            System.exit(0);
        }

    }
}
