import java.awt.Graphics;
import java.util.TimerTask;
import java.util.Timer;

/**
 * @brief Provides a wrapper around TetrisPiece
 * @details 
 * PieceManager uses the toolkit provided by TetrisPiece to perform game logic.
 * It handles regularly spawning and moving pieces down, and handling user commands
 * to rotate, shift, or fast forward
 */
public class PieceManager {

    /**
     * @brief PieceManager constructor
     * @details The construtor initializes piece as null
     * 
     * @param p The tetris panel
     * @param grid the tetris grid
     */
    public PieceManager(TetrisPanel p, TetrisGrid grid) {
        tgrid = grid;
        piece = null;
        tpanel = p;
    }

    /**
     * @brief Starts running the game
     * @details It creates a timer task and calls it at regular intervals
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
    /**
     * @brief Stops the game
     * @details If the mainLoopTimer is initialized, it cancels it and purges it
     */
    public void stopSpawning() {
        if (mainLoopTimer != null) {
            mainLoopTimer.cancel();
            mainLoopTimer.purge();
        }
    }

   /**
    * @brief Moves the piece left if possible
    */
    public void moveLeft() {
        if (piece==null) return;
        if (piece.canMoveLeft()) {
            piece.moveLeft();
            tgrid.finalize();
        }
    }

    /**
     * @brief Moves the piece right if possible
     */
    public void moveRight() {
        if (piece == null) return;
        if (piece.canMoveRight()) {
            piece.moveRight();
            tgrid.finalize();
        }
    }

   /**
    * @brief Rotates the piece clockwise if possible
    */
    public void rotateClockwise() {
        if (piece == null) return;
        if (piece.canRotateClockwise()) {
            piece.rotateClockwise();
            tgrid.finalize();
        }
    }

    /**
     * @brief Fast forwards the game until the current piece has gone as far down as possible
     */
    public void fastForward() {
        while (timeStep());
    }

    /**
     * @brief Moves forward one step in time
     * @details 
     * If the piece is null, it creates a new TetrisPiece initialized with the grid
     * If the piece can move down, it moves the piece down
     * Else if the piece is in the top row, it tells tpanel to die
     * Else it tells the grid to clear filled rows and create a new piece
     * Finally tgrid.finalize() is called, which draws the results to the screen
     * @return whether or not the piece was moved down a step
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

    // The time in milliseconds between consecutive time steps
    private static final long updateInterval = 500;
    // The tetris grid
    private TetrisGrid tgrid;
    // The tetris piece
    private TetrisPiece piece;
    // The tetris panel
    private TetrisPanel tpanel;
    // The main timer
    private Timer mainLoopTimer = new Timer();
}
