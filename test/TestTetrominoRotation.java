import com.funtoginot.tetris.data.tetrominos.Tetromino;
import com.funtoginot.tetris.data.tetrominos.TetrominosFactory;

/**
 * Created by Morgan on 14/05/2014.
 */
public class TestTetrominoRotation {
    public static void main(String[] args) {
        Tetromino tetromino = TetrominosFactory.getInstance().getTetromino();

        System.out.println(tetromino);

        long start = System.nanoTime();


        tetromino.rotateRight();

        System.out.println("Executed in : " + (System.nanoTime() - start) +  " ns");

        System.out.println(tetromino);


        start = System.nanoTime();


        tetromino.rotateLeft();

        System.out.println("Executed in : " + (System.nanoTime() - start) +  " ns");


        System.out.println(tetromino);


    }
}
