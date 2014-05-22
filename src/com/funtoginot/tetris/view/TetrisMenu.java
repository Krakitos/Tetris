package com.funtoginot.tetris.view;


import javax.swing.*;
import java.awt.event.KeyEvent;


/**
 * Created by cdric on 22/05/2014.
 */
public class TetrisMenu extends JPanel {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public TetrisMenu(){


        menuBar = new JMenuBar();


        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(
                "File menu");
        menuBar.add(menu);


        menuItem = new JMenuItem("New",
                new ImageIcon("images/new.gif"));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menu.add(menuItem);


        menu.addSeparator();

        menuItem = new JMenuItem("Pause", new ImageIcon("images/pause.gif"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menu.add(menuItem);

        menuItem = new JMenuItem("Exit", new ImageIcon("images/exit.gif"));
        menuItem.setMnemonic(KeyEvent.VK_E);
        menu.add(menuItem);


        frame.setJMenuBar(theJMenuBar);
    }


}
