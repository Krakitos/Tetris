package com.funtoginot.tetris.data;

import com.funtoginot.tetris.data.time.TimeManager;
import com.funtoginot.tetris.data.time.listeners.TickListener;
import com.funtoginot.tetris.data.observers.TetrisObservable;
import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.data.tetrominos.TetrominosFactory;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisEngine extends TetrisObservable implements TickListener {

    public static final int DEFAULT_COLUMNS_NUMBER = 10;
    public static final int DEFAULT_ROWS_NUMBER = 22;

    private static final int DEFAULT_TIMER_TICK = 500;

    private TimeManager timeManager;
    private AtomicBoolean isRunning; //Utilisation d'un AtomicBoolean car Timer s'exécute dans un autre processus.
    private AtomicBoolean isPaused;

    private Tetromino current;
    private Tetromino next;

    private TetrominosFactory tetrominosFactory;

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

        current = next = null;
        tetrominosFactory = TetrominosFactory.getInstance();

        gameboard = new TetrisBoard(rows, columns);

        isRunning = new AtomicBoolean(false);
        isPaused = new AtomicBoolean(false);
    }

    /**
     * Lance le jeu
     */
    public void startGame(){
        isRunning.set(true);
    }

    /**
     * Met en pause le jeu
     */

    public void togglePause(){
        if(isPaused.get()){
            isPaused.set(false);
            timeManager.run();

            fireGameUnPaused();
        }else{
            isPaused.set(true);
            timeManager.stop();

            fireGamePaused();
        }
    }

    @Override
    public void update(int tick) {

    }


    /**
     * défini cette touche comme le dernier évènement clavier. Une seule opération de mouvement est possible en un tick
     * @param keycode Le code de la touche pressée
     */
    public void handleKeyPressed(int keycode){

    }

    /**
     * Renvoi le score actuel
     * @return Score du joueur
     */
    public int getScore(){
        return points;
    }

    /**
     * Indique si le jeu est en cours d'exécution. Cette variable ne tient pas coupe de l'état 'en pause' du jeu.
     * @return True si une partie a été lancée, false sinon
     */
    public boolean isPlaying(){
        return isRunning.get();
    }

    /**
     * Indique si le jeu est en pause
     * @return True si le jeu est en pause, false sinon
     */
    public boolean isPaused(){
        return isPaused.get();
    }


    private class TetrominoPlacementSequence {

        private Tetromino workingTetromino;
        private int x;
        private int y;

        private int lastKeyPressed;

        private TetrominoPlacementSequence(Tetromino tetromino){

        }

        public Tetromino getWorkingTetromino() {
            return workingTetromino;
        }

        public void setWorkingTetromino(Tetromino workingTetromino) {
            this.workingTetromino = workingTetromino;
        }

        public void update() {
            switch(lastKeyPressed) {
                case KeyEvent.VK_DOWN : {

                    break;
                }
                case KeyEvent.VK_RIGHT : {

                    break;
                }
                case KeyEvent.VK_LEFT : {

                    break;
                }
                case KeyEvent.VK_SPACE : {

                    break;
                }
            }
        }
    }
}
