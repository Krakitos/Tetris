package com.funtoginot.tetris.view;

import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.view.components.TetrominoPreview;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Morgan on 28/05/2014.
 */
public class TetrisMenuPane extends JPanel {

    private static final String POINTS_LABEL_TEXT_FORMAT = "Points : %d";
    private static final String LEVEL_LABEL_TEXT_FORMAT = "Niveau : %d";

    private TetrominoPreview preview;

    private JLabel levelLbl;
    private JLabel pointsLbl;

    private JButton playBtn;
    public static final String PLAY_BTN_NAME = "PLAY";

    private JButton muteBtn;
    public static final String MUTE_BTN_NAME = "MUTE";

    public TetrisMenuPane(){
        setLayout(null);
        preview = new TetrominoPreview(0, 0);
        add(preview);

        levelLbl = new JLabel();
        Font font = new Font("arial", Font.BOLD, 15);
        levelLbl.setFont(font);
        levelLbl.setForeground(Color.WHITE);
        levelLbl.setSize(110, 30);
        add(levelLbl);

        pointsLbl = new JLabel();
        pointsLbl.setFont(font);
        pointsLbl.setForeground(Color.WHITE);
        pointsLbl.setSize(110, 15);
        add(pointsLbl);

        playBtn = new JButton("► Jouer");
        playBtn.setName(PLAY_BTN_NAME);
        playBtn.setFont(new Font("Calibri",Font.BOLD,15));
        playBtn.setSize(110, 30);
        add(playBtn);

        muteBtn = new JButton("♪ Mute");
        muteBtn.setName(MUTE_BTN_NAME);
        muteBtn.setFont(new Font("Calibri",Font.BOLD,13));
        muteBtn.setSize(110, 30);
        add(muteBtn);
    }

    public void updateLevel(int level){
        levelLbl.setText(String.format(LEVEL_LABEL_TEXT_FORMAT, level));
    }

    public void updateScore(int score){
        pointsLbl.setText(String.format(POINTS_LABEL_TEXT_FORMAT, score));
    }

    public void updateNextTetromino(Tetromino tetromino) {
        preview.updateView(tetromino);
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        preview.setSize(new Dimension((int) preferredSize.getWidth(), (int) preferredSize.getWidth()));
        preview.setBorder(BorderFactory.createTitledBorder(null, "NEXT", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("arial", Font.PLAIN, 12), Color.white));

        levelLbl.setLocation(10, preview.getHeight() + 20);
        pointsLbl.setLocation(10, levelLbl.getY() + levelLbl.getHeight() + 20);

        playBtn.setLocation(10, pointsLbl.getY() + pointsLbl.getHeight() + 20);
        muteBtn.setLocation(10, playBtn.getY() + playBtn.getHeight() + 20);
        invalidate();
    }

    public void addActionListener(ActionListener listener){
        playBtn.addActionListener(listener);
        muteBtn.addActionListener(listener);
    }

    public void onGamePause() {
        if(SwingUtilities.isEventDispatchThread()){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    onGamePause();
                }
            });
            return;
        }

        playBtn.setText("► Reprendre");
    }

    public void onGameUnPaused() {
        if (SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    onGameUnPaused();
                }
            });
            return;
        }

        playBtn.setText("▌▌ Pause");
    }

    public void onGameStart(){
        if(SwingUtilities.isEventDispatchThread()){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    onGameStart();
                }
            });
            return;
        }

        playBtn.setText("▌▌ Pause");
    }

    public void onGameOver(){
        if(SwingUtilities.isEventDispatchThread()){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    onGameOver();
                }
            });
            return;
        }

        playBtn.setText("► Rejouer");
    }
}
