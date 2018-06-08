import java.awt.Graphics;
import java.util.TimerTask;
import java.util.Timer;

/**
 * The purpose of PieceManager is to provide a wrapper around TetrisPiece
 * It handles updates a regular intervals using a Timer and
 * provides methods for TetrisPanel that rotate or move the tetris piece
 */
public class PieceManager {

    /**
     * The constructor for PieceManager
     * It is passed p, the current TetrisPanel and grid, the current TetrisGrid
     * It initializes the TetrisPiece as null
     */
    public PieceManager(TetrisPanel p, TetrisGrid grid) {
        tgrid = grid;
        piece = null;
        tpanel = p;
    }

    /**
     * This function starts the game
     */
    public void startSpawning() {
        TimerTask mainLoopTask = new TimerTask() {
            public void run() {
                timeStep();
            }
        };
        mainLoopTimer.scheduleAtFixedRate(mainLoopTask, 0, updateInterval);
    }

    /**
     * This function stops the game
     */
    public void stopSpawning() {
        if (mainLoopTimer != null) {
            mainLoopTimer.cancel();
            mainLoopTimer.purge();
        }
    }

    /**
     * This function moves the piece left if possible
     */
    public void moveLeft() {
        if (piece==null) return;
        if (piece.canMoveLeft()) {
            piece.moveLeft();
            tgrid.finalize();
        }
    }

    /**
     * This function moves the piece right if possible
     */
    public void moveRight() {
        if (piece == null) return;
        if (piece.canMoveRight()) {
            piece.moveRight();
            tgrid.finalize();
        }
    }

    /**
     * This function rotates the piece clockwise if possible
     */
    public void rotateClockwise() {
        if (piece == null) return;
        if (piece.canRotateClockwise()) {
            piece.rotateClockwise();
            tgrid.finalize();
        }
    }

    /**
     * This function moves the piece down until it cannot go down anymore
     */
    public void fastForward() {
        while (timeStep());
    }

    /**
     * This is the main function of PieceManager
     * First it constructs the piece if the piece is null
     * Then if the piece can move down, it moves the piece down
     * Otherwise if the piece is in the top row, it call tpanel.die
     * Otherwise, it draws the grid and creates a new piece
     * 
     * Finally, the function calls tgrid.finalize, which draws the results to the screen
     * 
     * The function returns true if the piece moved down and false otherwise
     */
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

    private TetrisGrid tgrid;
    private TetrisPiece piece;
    private TetrisPanel tpanel;
    private Timer mainLoopTimer = new Timer();
}
