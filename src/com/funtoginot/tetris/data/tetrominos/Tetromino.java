package com.funtoginot.tetris.data.tetrominos;

/**
 * Created by Morgan on 14/05/2014.
 */

import java.awt.*;
import java.util.Arrays;

/**
 * Represente un tetromino (figure géométrique composée de quatre carrés, chacun ayant au moins un côté complètement
 * partagé avec un autre). Les tetrominos sont stockés sous forme d'un tableau 2D de byte en mémoire. Une case peut
 * contenir soit la valeur 1 (un carré est présent), soit 0 (rien).
 */
public class Tetromino {
    public static final int TETROMINO_SQUARE_WIDTH = 3;

    private Color color;
    private byte[][] matrix;

    public Tetromino(Color color, byte[][] matrix){
        this.color = color;
        this.matrix = matrix;
    }

    /**
     * Opère une rotation de 90° vers la gauche sur le tetromino
     */
    public void rotateLeft(){
        int n = matrix.length;
        for (int i = 0; i < n >> 1; i++) {
            for (int j =i; j< n - 1 - i; j++) {
                byte tmp = matrix[i][j];
                matrix[i][j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = tmp;
            }
        }
    }

    /**
     * Opère une rotation de 90° vers la droite sur le tetromino
     */
    public void rotateRight(){
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
    }

    /**
     * Détermine si un carré est présent à la position x, y
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return True si un carré est présent, false sinon
     */
    public boolean hasSquareAt(int x, int y){
        assert(x > TETROMINO_SQUARE_WIDTH - 1 || y > TETROMINO_SQUARE_WIDTH -1): "Impossible de vérifier la présence d'un cube dans le tetromino à la position : " + x + "," + y;

        //On calcul le décalage x * matrix width + y

        //On test si après décalage le premier bit est à 1
        return matrix[x][y] == 1;
    }

    @Override
    public String toString() {
        String printM = "";
        for(byte[] line : matrix){
            printM += Arrays.toString(line) +"\n";
        }

        return "Tetromino{" +
                "color=" + color + '\n'+
                printM +
                '}';
    }
}
