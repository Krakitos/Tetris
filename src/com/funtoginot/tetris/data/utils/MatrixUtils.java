package com.funtoginot.tetris.data.utils;

/**
 * Created by Morgan on 15/05/2014.
 */

/**
 * Impossible de rendre générique ces fonctions, celles-ci utilisant des types natifs (byte[] par exemple).
 * Cela entrainerait de passer par des Wrapper et de reconstruire un tableau ...
 */
public class MatrixUtils {

    public static final byte[][] rotateRight(byte[][] from, byte[][] to){
        int n = from.length;

        copy2DArray(from, to);
        rotateRight(to);

        return to;
    }

    public static final byte[][] rotateLeft(byte[][] from, byte[][] to){
        copy2DArray(from, to);
        rotateLeft(to);

        return to;
    }

    public static final byte[][] rotateRight(byte[][] matrix){
        int n = matrix.length;

        for (int i = 0; i < n >> 1; i++) {
            for (int j = i; j< n - 1-i; j++) {
                byte tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1-i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }

        return matrix;
    }

    public static final byte[][] rotateLeft(byte[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < n >> 1; i++) {
            for (int j = i; j< n - 1 - i; j++) {
                byte tmp = matrix[i][j];
                matrix[i][j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = tmp;
            }
        }

        return matrix;
    }

    public static final byte[][] copy2DArray(byte[][] matrix, byte[][] copy){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                copy[i][j] = matrix[i][j];
            }
        }

        return copy;
    }
}
