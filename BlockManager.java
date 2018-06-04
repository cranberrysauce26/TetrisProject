import java.awt.Graphics;
import java.util.TimerTask;
import java.util.Timer;

public class BlockManager {

    public BlockManager(TetrisPanel p, TetrisGrid grid) {
        tgrid = grid;
        piece = null;
        tpanel = p;
    }

    public void startSpawning() {
        Timer mainLoopTimer = new Timer();
        TimerTask mainLoopTask = new TimerTask() {
            public void run() {
                timeStep();
            }
        };
        mainLoopTimer.scheduleAtFixedRate(mainLoopTask, updateDelay, updateInterval);
    }

    private void timeStep() {
        if (piece == null) {
            piece = new TetrisPiece(tgrid);
        } else {
            if (piece.canMoveDown()) {
                piece.moveDown();
            } else if (!piece.isWithinGrid()) {
                tpanel.die();
            } else {
                piece = new TetrisPiece(tgrid);
            }
        }
        tgrid.finalize();
    }

    private static final long updateInterval = 500;
    private static final long updateDelay = 0;

    private TetrisGrid tgrid;
    private TetrisPiece piece;
    private TetrisPanel tpanel;
}
