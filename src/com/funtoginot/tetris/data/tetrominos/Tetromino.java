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
        matrix = MatrixUtils.rotateLeft2(matrix);
    }

    /**
     * Opère une rotation de 90° vers la droite sur le tetromino
     */
    public void rotateRight(){
        matrix = MatrixUtils.rotateRight2(matrix);
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
     * Largeur de la matrice définissant le tetromino
     * @return La largeur du tetromino
     */
    public int getWidth(){
        return matrix.length;
    }

    /**
     * Renvoi la largeur réelle du tetromino. getWidth() renvoie la largeur de la matrice utilisée pour la représentation
     * mais certaines cases d'une même ligne / colonne peuvent ne pas être utilisées. Cette méthode tient compte
     * de l'occupation des cases dans le tetromino.
     * @return Largueur réelle du tetromino
     */
    public int getRealWidth(){

        //TODO eviter de refaire le calcul à chaque fois. Le calculer uniquement lors d'une nouvelle rotation
        int width = getWidth();

        while(width > 0) {
            for (int i = 0; i < matrix.length; i++) {
                if(matrix[i][width - 1] != 0) return width;
            }

            --width;
        }

        System.out.println(width);

        return width - 1;
    }

    /**
     * Hauteur de la matrice définissant le tetromino
     * @return La hauteur du tetromino
     */
    public int getHeight(){
        return matrix[0].length;
    }

    /**
     * Renvoi la hauteur réelle du tetromino. Voir getRealWidth
     * @return hauteur réelle du tetromino
     */
    public int getRealHeight(){
        //TODO eviter de refaire le calcul à chaque fois. Le calculer uniquement lors d'une nouvelle rotation
        int height = getHeight() - 1;

        for(byte[] row : matrix){
            for (int i = 0; i < row.length; i++) {

                //Si on a pas un 0 cette position, alors on peut assurer que la largeur est maximale sur ce point
                if(row[i] != 0) return height;
            }

            System.out.println(height);

            --height;
        }

        return height - 1;
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
