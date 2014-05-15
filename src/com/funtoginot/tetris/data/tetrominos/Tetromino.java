package com.funtoginot.tetris.data.tetrominos;

/**
 * Created by Morgan on 14/05/2014.
 */

import com.funtoginot.tetris.data.utils.MatrixUtils;

import java.awt.*;

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
        MatrixUtils.rotateLeft(matrix);
    }

    /**
     * Opère une rotation de 90° vers la droite sur le tetromino
     */
    public void rotateRight(){
        MatrixUtils.rotateRight(matrix);
    }

    /**
     * Détermine si un carré est présent à la position x, y
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return True si un carré est présent, false sinon
     */
    public boolean hasSquareAt(int x, int y){
        assert(x > TETROMINO_SQUARE_WIDTH - 1 || y > TETROMINO_SQUARE_WIDTH -1): "Impossible de vérifier la présence d'un cube dans le tetromino à la position : " + x + "," + y;

        return matrix[x][y] == 1;
    }

    /**
     * Largeur du Tetromino
     * @return La largeur du tetromino
     */
    public int getWidth(){
        return matrix.length;
    }

    /**
     * Hauteur du tetromino
     * @return La hauteur du tetromino
     */
    public int getHeight(){
        return matrix[0].length;
    }

    /**
     * Renvoie la couleur du tetromino
     * @return Couleur du tetromino
     */
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Tetromino{" +
                "color=" + color +
                '}';
    }
}
