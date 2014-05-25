import com.funtoginot.tetris.controller.TetrisController;

import javax.swing.*;

/**
 * Created by Morgan on 14/05/2014.
 */
public class Tetris {
    public static void main(String[] args) {
        final TetrisController controller = new TetrisController();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller.handleStartAction();
            }
        });
    }
}
