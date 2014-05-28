package com.funtoginot.tetris.data.time;

import com.funtoginot.tetris.data.time.listeners.TickListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Morgan on 15/05/2014.
 */

/**
 * Classe permettant de gérer toute la partie temporelle du jeu. elle dispatche à intervalles réguliers un évènement
 * permettant à ses écouteurs de se synchroniser.
 * @see com.funtoginot.tetris.data.time.listeners.TickListener
 */
public class TimeManager {
    private List<TickListener> listeners;
    private Timer timer;
    private int tick;

    public TimeManager(int tick){
        listeners = new LinkedList<>();
        timer = new Timer("Time manager");
        this.tick = tick;
    }

    /**
     * Lance le gestionnaire de temps
     */
    public void run(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //Mise à jour de l'interface
                fireTimerTick(tick);
            }
        }, tick, tick);
    }

    /**
     * Change le delais du Timer
     * @param delay Delais à appliquer
     */
    public void changeDelay(int delay){
        timer.cancel();
        tick = delay;
        timer = new Timer("Time manager");
        run();
    }

    /**
     * Arrete le temps ! Sisi !
     */
    public void stop(){
        timer.cancel();
        timer.purge();
    }

    /**
     * Ajoute un écouteur d'évènements
     * @param listener L'écouteur à ajouter
     */
    public void addTickListener(TickListener listener){
        listeners.add(listener);
    }

    /**
     * Supprime un écouteur d'évènements
     * @param listener L'écouteur à supprimer
     */
    public void removeTickListener(TickListener listener){
        listeners.remove(listener);
    }

    /**
     * Dispatche le tick à tout les écouteurs d'évènements
     * @param tick Intervalle de temps depuis le dernier tick
     */
    private void fireTimerTick(int tick) {
        for(TickListener listener : listeners){
            listener.update(tick);
        }
    }

}
