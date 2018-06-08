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
     /**
     * The constructor for PieceManager
     * It is passed p, the current TetrisPanel and grid, the current TetrisGrid
     * It initializes the TetrisPiece as null
     */
    }
    
    /**
     * @brief Starts running the game
     * @details It creates a timer task and calls it at regular intervals
     */
    public void startSpawning() {
		
	/**
     * This function starts the game
	 It initializes a new TimeTask() with timestep()
	 mainLoopTimer is used to update intervals at a fixed rate
     */
    }
    
    /**
     * This function stops the game
     */
    /**
     * @brief Stops the game
     * @details If the mainLoopTimer is initialized, it cancels it and purges it
     */
    public void stopSpawning() {
	/**
	 This function cancels the mainLoopTimer and stops the game.
     */
    }

    /**
    * @brief Moves the piece left if possible
    */
    public void moveLeft() {
	 /**
     * This function first checks if the piece can move left.
	 If possible to move lieft, this function calls moveleft() and then finalizes tgrid.
     */
    }

    /**
     * @brief Moves the piece right if possible
     */
    public void moveRight() {
     /**
     * This function first checks if the piece can move right.
	 If possible to move lieft, this function calls moveright() and then finalizes tgrid.
     */
    }

	/**
    * @brief Rotates the piece clockwise if possible
    */
    public void rotateClockwise() {
		/* If piece is null this function does not return anything
		Otherwise this function checks if the piece can rotate clockwise.
		If possible this function calls rotateClockwise() and then finalizes tgrid
		*/
    }

    /**
     * @brief Fast forwards the game until the current piece has gone as far down as possible
     */
    public void fastForward() {
	/**
     * This function moves the piece down until it cannot go down anymore by repeatedly calling timestep() in a while loop.
     */
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
	/**
     * First this function constructs the piece if the piece is null
     * Then if the piece can move down, it moves the piece down
     * Otherwise if the piece is in the top row, it call tpanel.die()
     * Otherwise it draws the grid and creates a new piece
     * Finally, the function calls tgrid.finalize, which draws the results to the screen
     * The function returns true if the piece moved down and false otherwise
     */
    }

    // The time in milliseconds between consecutive time steps
    private static final long updateInterval;
    // The tetris grid
    private TetrisGrid tgrid;
    // The tetris piece
    private TetrisPiece piece;
    // The tetris panel
    private TetrisPanel tpanel;
    // The main timer
    private Timer mainLoopTimer = new Timer();
}
