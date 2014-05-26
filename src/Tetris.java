import com.funtoginot.tetris.controller.TetrisController;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Morgan on 14/05/2014.
 */
public class Tetris {
    public static void main(String[] args) throws IOException {
        final TetrisController controller = new TetrisController();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller.handleStartAction();
            }
        });
    }
}
