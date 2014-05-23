package com.funtoginot.tetris.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by cdric on 22/05/2014.
 */

public class TetrisMenu extends JPanel implements ActionListener {

    JButton startG, exitG;
    JPanel panel;
    ImageIcon ii;
    JLabel picture;
    private BufferedImage img;
    public static final Color DEFAULT_COLOR = Color.BLACK;

    public TetrisMenu() {

        setBackground(DEFAULT_COLOR);

        setSize(200, 400);
        //Start Game Button
        startG = new JButton("Nouvelle Partie");
        startG.setBounds(50, 100, 100, 30);
        startG.setFocusable(false);
        add(startG);


        //Exit Game Button
        exitG = new JButton("Quitter");
        exitG.setBounds(50, 200, 100, 30);
        exitG.setFocusable(false);
        add(exitG);

        // load the background image
        try {
            img = ImageIO.read(new File("/Users/cdric/Google Drive/Documents/Polytech/S6 Polytech/Algo Prog 2/Tetris/src/com/funtoginot/tetris/content/images/logo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        startG.addActionListener(this);
        exitG.addActionListener(this);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint the background image and scale it to fill the entire space
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == startG) {
            System.out.println("New Game - button is working");


        } else if (e.getSource() == exitG) {
            System.out.println("Game is exiting - button is working");
            System.exit(0);
        }

    }
}
