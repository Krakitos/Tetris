package com.funtoginot.tetris.data.observer;

import com.funtoginot.tetris.data.tetrominos.Tetromino;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Morgan on 14/05/2014.
 */
public abstract class TetrisObservable {

    private List<TetrisObserver> observers = new LinkedList<>();

    protected TetrisObservable() {

    }

    /**
     * Ajoute un observateur
     * @param observer L'observateur à ajouter
     */
    public void addObserver(TetrisObserver observer) {
        observers.add(observer);
    }

    /**
     * Supprime un observateur
     * @param observer L'observateur à supprimer
     */
    public void removeObserver(TetrisObserver observer) {
        observers.remove(observer);
    }

    /**
     * Supprime tout les observateurs
     */
    public void removeObservers() {
        observers.clear();
    }

    /**
     * Informe du changement de tetromino à placer
     * @param tetromino Tetromino à placer
     */
    protected void fireCurrentTetrominoChanged(Tetromino tetromino){
        for(TetrisObserver observer : observers){
            observer.onCurrentTetrominoChanged(tetromino);
        }
    }

    /**
     * Informe qu'une ligne est complètement remplie et qu'il faut la supprimer
     * @param rows Les lignes à supprimer
     */
    protected void fireRowFull(int[] rows){
        for(TetrisObserver observer : observers){
            observer.onFullRowsDeleted(rows);
        }
    }


    /**
     * Informe qu'un tick s'est écoulé
     * @param delay L'interval de temps entre chaque tick
     * @param current Le tetromino actuellement en cours de placement
     */
    protected void fireTimerTick(int delay, Tetromino current){
        for(TetrisObserver observer : observers){
            observer.onTimerTick(delay, current);
        }
    }

    /**
     * Informe que le score à changer
     * @param points Le nombre de points actuels
     */
    protected void firePointsChanged(int points){
        for(TetrisObserver observer : observers){
            observer.onPointsChanged(points);
        }
    }
}