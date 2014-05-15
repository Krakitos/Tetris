package com.funtoginot.tetris.data.time.listeners;

/**
 * Created by Morgan on 15/05/2014.
 */
public interface TickListener {
    /**
     * Fonction de rappel pour la mise à jour à intervalles répetés
     * @param tick
     */
    public void update(int tick);
}
