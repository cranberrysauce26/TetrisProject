/**
 * @brief The main game panel
 * @details The panel creates instances of TetrisGrid and PieceManager.
 * It handles user keyboard events as well as off screen graphics logic
 */
public class TetrisPanel extends JPanel implements KeyListener {

    /**
     * @brief TetrisPanel constructor
     * @details Initializes the number of rows and columns of the grid,
     * and adds itself as a KeyListener. Finally, it calls init, which starts the game
     * 
     * @param r The number of rows in the Tetris game
     * @param c The number of columns in the Tetris game
     */
    public TetrisPanel(int r, int c) {
	 /**
     * TetrisPanel constructor adds itself as a key listener and calls the intialization function
     * r is declared as the number of rows in the game
     * c is declared the number of columns in the game
     */
    }

    /**
     * @brief paints the game onto the screen
     * @details This is the standard off screen graphics pattern used in class.
     * The real work is done in method updateImage
     * 
     * @param g The Graphics instance to draw with
     */
    public void paint(Graphics g) {
     /**
     * This is the standard paint function for off screen graphics
     * It depends on the function updateImage, which updates osg
     */
    }

    /**
     * @brief Prints the user's score and starts a new game
     * @details The method calls init, which resets and starts a new game
     */
    public void die() {
     /**
     * This is called when the user dies
     * It calls init() which restarts the game
     */
    }

    /**
     * @brief Increase the score by some amount and display to screen
     * 
     * @param s The amount to increase the score by
     */
    public void addScore(int s) {
     /**
     * addScore(s) adds s to the user's score and displays this on screen
     */
    }

    /**
     * @brief Handles key pressed events
     * @details
     * The up key rotates the piece clockwise
     * The right key moves the piece right
     * The left key moves the piece left
     * The space key fast forwards until the next piece drop or the game is over
     * 
     * @param ke The user's keyboard event
     */
	public void keyPressed(KeyEvent ke) {
		
     /**
     * keyPressed handles the user's keyboard events
     * The up key calls rotateclockwise() which rotates the piece clockwise
     * The right key calls moveright() which moves the block right
     * The left key calls moveleft() which moves the block left
     * The space key calls fastforwards() which makes the piece drop to the bottom
     */
	
		}
	}

	public void keyReleased(KeyEvent ke) {}
	
    public void keyTyped(KeyEvent ke) {}

    /**
     * @brief Stops the current game if a game is running and starts a new one
     * @details This method creates a new grid and pieceManager, resets the score, and calls
     * pieceManager.startSpawning() to start the game
     */
    private void init() {
		
     /**
     * This restarts the game
     * If pieceManager is active, it calls pieceManager.stopSpawning
     * It resets the score to 0, creates a new grid, creates a new pieceManager
     * and calls startsspawning() again
     */
	 
    }

    /**
     * @brief Updates the image with off screen graphics osg
     * @details
     * It lets grid update the image using grid.display
     * and it sets the top of the grid to dim.height / 10
     * Then it draws the user's score at y coordinate dim.height / 12
     * These numbers were chosen to approximately center the score string in the panel
     */
    private void updateImage() {
     /**
     * This function updates the image
     * It lets grid update the image using grid.display
     * and it sets the top of the grid to dim.height / 10
     * Then it draws the user's score at y coordinate dim.height / 12
     * These numbers were chosen to approximately center the score string in the panel
     */
    }

    // The user's score
    private int score;
    // The number of rows in the grid
    private int nrows;
    // The number of columns in the grid
    private int ncols;

    // The TetrisGrid
    private TetrisGrid grid;
    // The PieceManager
    private PieceManager pieceManager;

    // Buffered off screen image
    private BufferedImage osi;
    // Off screen graphics
    private Graphics osg;
    // Current screen dimensions
    private Dimension dim;
}