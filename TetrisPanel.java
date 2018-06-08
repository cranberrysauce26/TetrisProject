import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * @brief The main game panel
 * @details The panel creates instances of TetrisGrid and PieceManager.
 * It handles user keyboard events as well as off screen graphics logic
 */
@SuppressWarnings("serial")
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
        nrows = r;
        ncols = c;
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        init();
    }

    /**
     * @brief paints the game onto the screen
     * @details This is the standard off screen graphics pattern used in class.
     * The real work is done in method updateImage
     * 
     * @param g The Graphics instance to draw with
     */
    public void paint(Graphics g) {
        dim = getSize();
        osi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        osg = osi.getGraphics();
        updateImage();
        g.drawImage(osi, 0, 0, this);
    }

    /**
     * @brief Prints the user's score and starts a new game
     * @details The method calls init, which resets and starts a new game
     */
    public void die() {
        System.out.printf("You scored %d. Starting a new game\n", score);
        init();
    }

    /**
     * @brief Increase the score by some amount and display to screen
     * 
     * @param s The amount to increase the score by
     */
    public void addScore(int s) {
        score += s;
        repaint();
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
		int code = ke.getKeyCode();
		switch (code) {
			case KeyEvent.VK_UP:
                pieceManager.rotateClockwise();
				break;
			case KeyEvent.VK_RIGHT:
                pieceManager.moveRight();
				break;
			case KeyEvent.VK_LEFT:
				pieceManager.moveLeft();
				break;
			case KeyEvent.VK_SPACE:
                pieceManager.fastForward();
				break;
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
        if (pieceManager != null) pieceManager.stopSpawning();
        score = 0;
        grid = new TetrisGrid(this, nrows, ncols);
        pieceManager = new PieceManager(this, grid);
        pieceManager.startSpawning();
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
        if (grid != null) {
            grid.display(osg, 0, dim.width, dim.height / 10, dim.height);
            osg.setColor(Color.RED);
            osg.drawString("Score: "+score, dim.width/2, dim.height / 12);
        }
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