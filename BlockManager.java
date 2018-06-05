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
        TimerTask mainLoopTask = new TimerTask() {
            public void run() {
                timeStep();
            }
        };
        mainLoopTimer.scheduleAtFixedRate(mainLoopTask, updateDelay, updateInterval);
    }

    public void stopSpawning() {
        if (mainLoopTimer != null) {
            mainLoopTimer.cancel();
            mainLoopTimer.purge();
        }
    }

    private void timeStep() {
        if (piece == null) {
            piece = new TetrisPiece(tgrid);
        } else {
            if (piece.canMoveDown()) {
                piece.moveDown();
            } else if (piece.occupiesTop()) {
                tpanel.die();
            } else {
                tgrid.clearFilledRows();
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
    private Timer mainLoopTimer = new Timer();
}
