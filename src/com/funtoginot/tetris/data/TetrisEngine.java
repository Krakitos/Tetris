package com.funtoginot.tetris.data;

import com.funtoginot.tetris.data.observers.TetrisObservable;
import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.data.tetrominos.TetrominosFactory;
import com.funtoginot.tetris.data.time.TimeManager;
import com.funtoginot.tetris.data.time.listeners.TickListener;
import com.funtoginot.tetris.data.utils.TetrominoMoveSelector;

import java.awt.event.KeyEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TetrisEngine extends TetrisObservable implements TickListener {

    public static final int DEFAULT_COLUMNS_NUMBER = 10;
    public static final int DEFAULT_ROWS_NUMBER = 22;

    private static final int DEFAULT_TIMER_TICK = 800;

    private TimeManager timeManager;
    private AtomicBoolean isRunning; //Utilisation d'un AtomicBoolean car Timer et L'UI accèdent de façon concurrente à cette variable
    private AtomicBoolean isPaused; //Idem

    private Tetromino current;
    private Tetromino next;

    private TetrominosFactory tetrominosFactory;

    private TetrisBoard gameboard;

    private MovementSequence sequence;

    private Executor threadPool = Executors.newSingleThreadExecutor();

    private int level;
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

        gameboard = new TetrisBoard(rows, columns);

        isRunning = new AtomicBoolean(false);
        isPaused = new AtomicBoolean(false);

        current = next = null;
        tetrominosFactory = TetrominosFactory.getInstance();
    }

    /**
     * Lance le jeu
     */
    public void startGame(){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                points = 0;
                level = 1;

                firePointsChanged(points);
                fireLevelChanged(level);

                gameboard.reset();

                timeManager = new TimeManager(DEFAULT_TIMER_TICK / level);
                timeManager.addTickListener(TetrisEngine.this);

                sequence = new MovementSequence();

                newSequence();

                isRunning.set(true);
                timeManager.run();

                fireGameStarted(sequence, next);
            }
        });
    }

    /**
     * Met en pause le jeu
     */
    public void togglePause(){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
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
        });
    }

    @Override
    public void update(final int tick) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                //Si la mise à jour à entrainer la fin du mouvement (collision)
                if(sequence.update()){

                    //Game over
                    if(sequence.getRow() < sequence.getWorkingTetromino().getHeight()){
                        timeManager.stop();
                        isRunning.set(false);
                        fireGameOver(points, level);
                        return;
                    }else {

                        //On place la piece sur le plateau
                        gameboard.mergeTetromino(sequence);

                        //On verifie les lignes pleines
                        int fullLines = gameboard.checkFullRows();

                        //Si on a des lignes pleines
                        if (fullLines > 0) {

                            //On augmente les points de 100 * le nombre de lignes pleines ce tour ci
                            points += 100 * fullLines;

                            //Vérification changement de niveau + mise à jour des points
                            checkPoints();
                        }

                        newSequence();
                    }
                }

                fireTimerTick(tick, sequence);
            }
        });
    }


    /**
     * Défini cette touche comme le dernier évènement clavier. Une seule opération de mouvement est possible en un tick
     * @param keycode Le code de la touche pressée
     */
    public MovementSequence handleKeyPressed(int keycode){
        if(sequence != null){
            sequence.handleKeyboardEvent(keycode);
        }

        return sequence;
    }

    /**
     * Renvoie le score actuel
     * @return Score du joueur
     */
    public int getScore(){
        return points;
    }

    /**
     * Renvoie le niveau actuel
     * @return Niveau actuel
     */
    public int getLevel() { return level; }

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

    public TetrisBoard getBoard(){
        return gameboard;
    }


    /**
     * Démarre une nouvelle sequence de placement d'un tetromino
     */
    private void newSequence() {
        if(next == null) {
            current = tetrominosFactory.getTetromino();
            next = tetrominosFactory.getTetromino();
        }else{
            current = next;
            next = tetrominosFactory.getTetromino();
        }

        sequence.newSequence(current);

        fireCurrentTetrominoChanged(sequence, next);
    }

    private void checkPoints(){
        //On informe la vue que le score à changer
        firePointsChanged(points);

        checkLevel();
    }

    private void checkLevel() {
        if(level * 1000 < points) {
            ++level;
            timeManager.changeDelay(DEFAULT_TIMER_TICK / level);
            fireLevelChanged(level);
        }
    }


    /**
     * Classe gérant toute la partie spatiale du tetromino en cours de placement
     */
    public class MovementSequence {

        private Tetromino workingTetromino;
        private int row;
        private int column;

        private TetrominoMoveSelector availableMoves;

        /**
         * Renvoi le tetromino en cours de placement
         * @return Tetromino en cours de placement
         */
        public Tetromino getWorkingTetromino() {
            return workingTetromino;
        }

        /**
         * Démarre une nouvelle séquence de placement
         * @param newTetromino
         */
        public void newSequence(Tetromino newTetromino){
            workingTetromino = newTetromino;
            row = -1;
            column = (int)(Math.random() * (gameboard.getWidth() - workingTetromino.getWidth()));
        }

        /**
         * Met à jour la position du tetromino
         * @return True si le mouvement est fini, false sinon
         */
        public boolean update() {

            availableMoves = gameboard.getAvailableMoves(workingTetromino, column, row);

            if(availableMoves.canTranslateBottom()){
                ++row;
                return false;
            }else{
                return true;
            }
        }

        public void handleKeyboardEvent(int keycode){
            availableMoves = gameboard.getAvailableMoves(workingTetromino, column, row);

            //Gestion des mutateurs
            switch(keycode) {
                case KeyEvent.VK_UP:{
                    if(availableMoves.canRotateRight()) {
                        workingTetromino.rotateRight();
                    }
                    break;
                }
                case KeyEvent.VK_DOWN : {
                    if(availableMoves.canRotateLeft()) {
                        workingTetromino.rotateLeft();
                    }
                    break;
                }
                case KeyEvent.VK_RIGHT : {
                    if(availableMoves.canTranslateRight()) {
                        ++column;
                    }
                    break;
                }
                case KeyEvent.VK_LEFT : {
                    if(availableMoves.canTranslateLeft()) {
                        --column;
                    }
                    break;
                }
                case KeyEvent.VK_SPACE : {
                    row = gameboard.drop(this);
                    break;
                }
                default:{}
            }
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }
}
