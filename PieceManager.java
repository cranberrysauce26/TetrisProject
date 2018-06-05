import java.awt.Graphics;
import java.util.TimerTask;
import java.util.Timer;

public class PieceManager {

    public PieceManager(TetrisPanel p, TetrisGrid grid) {
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

    public void moveLeft() {
        if (piece==null) return;
        if (piece.canMoveLeft()) {
            piece.moveLeft();
            tgrid.finalize();
        }
    }

    public void moveRight() {
        if (piece == null) return;
        if (piece.canMoveRight()) {
            piece.moveRight();
            tgrid.finalize();
        }
    }

    public void rotateClockwise() {
        if (piece == null) return;
        if (piece.canRotateClockwise()) {
            piece.rotateClockwise();
            tgrid.finalize();
        }
    }

    public void fastForward() {
        while (timeStep());
    }

    private boolean timeStep() {
        boolean movedDown = false;
        if (piece == null) {
            piece = new TetrisPiece(tgrid);
        } else {
            if (piece.canMoveDown()) {
                piece.moveDown();
                movedDown = true;
            } else if (piece.occupiesTop()) {
                tpanel.die();
            } else {
                tgrid.clearFilledRows();
                piece = new TetrisPiece(tgrid);
            }
        }
        tgrid.finalize();
        return movedDown;
    }

    private static final long updateInterval = 500;
    private static final long updateDelay = 0;

    private TetrisGrid tgrid;
    private TetrisPiece piece;
    private TetrisPanel tpanel;
    private Timer mainLoopTimer = new Timer();
}
