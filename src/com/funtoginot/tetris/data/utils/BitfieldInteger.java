package com.funtoginot.tetris.data.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Morgan on 22/05/2014.
 * Classe permettant d'englober la manipulation de champ de bit (comme en C). Les méthodes proposées permettent
 * de manipuler indépendemment chaque bit d'un int. Les opérations sont : Mettre un bit à 1 ou 0, basculer un bit et
 * récupérer la valeur d'un bit donné.
 * Cette classe est Thread-safe.
 */
public class BitfieldInteger {
    private AtomicInteger bitfield;

    public BitfieldInteger(int initialVal){
        bitfield = new AtomicInteger(initialVal);
    }

    public int setBit(int bitwise){
        bitfield.set(bitfield.get() | (1 << bitwise));
        return bitfield.get();
    }

    public int getBit(int bit){
        return (bitfield.get() & (1 << bit)) == 0 ? 0 : 1;
    }

    public int toggleBit(int bit){
        bitfield.set(bitfield.get() ^ (1 << bit));
        return bitfield.get();
    }

    public int clearBit(int bit){
        bitfield.set(bitfield.get() & ~(1 << bit));
        return bitfield.get();
    }

    public int get(){
        return bitfield.get();
    }

    public short getShort(){
        return bitfield.shortValue();
    }

    public byte getByte(){
        return bitfield.byteValue();
    }

    protected void reset() {
        bitfield.set(0);
    }
}
