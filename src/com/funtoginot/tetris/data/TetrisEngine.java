package com.funtoginot.tetris.data;

import com.funtoginot.tetris.data.observer.TetrisObservable;
import com.funtoginot.tetris.data.tetrominos.Tetromino;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisEngine extends TetrisObservable {

    public static final int DEFAULT_COLUMNS_NUMBER = 10;
    public static final int DEFAULT_ROWS_NUMBER = 22;

    private static final int DEFAULT_TIMER_TICK = 500;

    private Timer timer;
    private AtomicBoolean isRunning;

    private Tetromino current;
    private TetrisBoard gameboard;

    private int points;

    /**
     * Crée un nouveau moteur de jeu avec les valeurs par défaut
     */
    public TetrisEngine() throws Exception {
        this(DEFAULT_ROWS_NUMBER, DEFAULT_COLUMNS_NUMBER);
    }

    /**
     * Crée un nouveau moteur de jeu avec les valeur passées en paramètres
     * @param rows Nombre de lignes
     * @param columns Nombre de colonnes
     */
    public TetrisEngine(int rows, int columns) throws Exception {
        if(rows < 3) throw new Exception("Impossible de créer un jeu de tetris avec moins de 3 lignes");
        if(columns < 3) throw new Exception("Impossible de créer un jeu de tetris avec moins de 3 colonnes");

        current = null;
        gameboard = new TetrisBoard(rows, columns);

        isRunning = new AtomicBoolean(false);
        timer = new Timer("Game Loop");
    }

    /**
     * Lance le jeu
     */
    public void startGame(){
        isRunning.set(true);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isRunning.get()) {
                    //TODO : Logique


                    //Mise à jour de l'interface
                    fireTimerTick(DEFAULT_TIMER_TICK, current);
                }
            }
        }, DEFAULT_TIMER_TICK, DEFAULT_TIMER_TICK);
    }

    /**
     * Met en pause le jeu
     */
    public void pause(){
        isRunning.set(false);
    }


    public int getScore(){
        return points;
    }
}
