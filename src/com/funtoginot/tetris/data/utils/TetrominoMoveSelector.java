package com.funtoginot.tetris.data.utils;

/**
 * Created by Morgan on 22/05/2014.
 */
public class TetrominoMoveSelector {

    public static final int ROTATE_LEFT = 1;
    public static final int ROTATE_RIGHT = 1 << 1;
    public static final int TRANSLATE_LEFT = 1 << 2;
    public static final int TRANSLATE_RIGHT = 1 << 3;
    public static final int TRANSLATE_BOTTOM = 1 << 4;
    public static final int ALL_MOVES_AVAILABLE = ROTATE_LEFT + ROTATE_RIGHT + TRANSLATE_LEFT + TRANSLATE_RIGHT + TRANSLATE_BOTTOM;

    private BitfieldInteger bitfield;

    public TetrominoMoveSelector(){
        bitfield = new BitfieldInteger(0);
    }

    public void addTranslateBottom(){
        bitfield.setBit(TRANSLATE_BOTTOM);
    }

    public void removeTranslateBottom() {
        bitfield.clearBit(TRANSLATE_BOTTOM);
    }

    public boolean canTranslateBottom(){
        return bitfield.getBit(TRANSLATE_BOTTOM) == 1;
    }

    public void addTranslateRight(){
        bitfield.setBit(TRANSLATE_RIGHT);
    }

    public void removeTranslateRight(){
        bitfield.clearBit(TRANSLATE_RIGHT);
    }

    public boolean canTranslateRight(){
        return bitfield.getBit(TRANSLATE_RIGHT) == 1;
    }

    public void addTranslateLeft(){
        bitfield.setBit(TRANSLATE_LEFT);
    }

    public void removeTranslateLeft(){
        bitfield.clearBit(TRANSLATE_LEFT);
    }

    public boolean canTranslateLeft(){
        return bitfield.getBit(TRANSLATE_LEFT) == 1;
    }

    public void addRotateRight(){
        bitfield.setBit(ROTATE_RIGHT);
    }

    public void removeRotateRight(){
        bitfield.clearBit(ROTATE_RIGHT);
    }

    public boolean canRotateRight(){
        return bitfield.getBit(ROTATE_RIGHT) == 1;
    }

    public void addRotateLeft(){
        bitfield.setBit(ROTATE_LEFT);
    }

    public void removeRotateLeft(){
        bitfield.clearBit(ROTATE_LEFT);
    }

    public boolean canRotateLeft(){
        return bitfield.getBit(ROTATE_LEFT) == 1;
    }

    public void clear(){
        bitfield.reset();
    }
}
