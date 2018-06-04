import java.awt.Graphics;
import java.util.TimerTask;
import java.util.Timer;

public class BlockManager {

    public BlockManager(TetrisPanel p, TetrisGrid grid) {
        System.out.printf("in blockmanager constructor\n");
        tgrid = grid;
        piece = null;
        tpanel = p;
    }

    public void startSpawning() {
        System.out.printf("SPAWNING IN BLOCK MANAGER\n");
        Timer mainLoopTimer = new Timer();
        System.out.printf("made a new timer\n");
        TimerTask mainLoopTask = new TimerTask() {
            public void run() {
                timeStep();
            }
        };
        System.out.printf("about to set the schedle\n");
        mainLoopTimer.scheduleAtFixedRate(mainLoopTask, updateDelay, updateInterval);
        System.out.printf("finished setting the schedule\n");
    }

    private void timeStep() {
        if (piece == null) {
            piece = new TetrisPiece(tgrid);
            piece.draw();
        } else {
            if (piece.canMoveDown()) {
                piece.moveDown();
            } else {
                piece = null;
            }
        }
        tgrid.finalize();
    }

    private static final long updateInterval = 1000;
    private static final long updateDelay = 500;

	// private Timer mainLoopTimer;
    private TetrisGrid tgrid;
    private TetrisPiece piece;
    private TetrisPanel tpanel;
}
