import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * TetrisPanel is the game panel
 * It manages painting to the screen, as well as keyboard events
 */
@SuppressWarnings("serial")
public class TetrisPanel extends JPanel implements KeyListener {

    /**
     * TetrisPanel constructor adds itself as a key listener and calls init
     * r is the number of rows in the game
     * c is the number of columns in the game
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
     * This is the standard paint function for off screen graphics
     * It depends on the function updateImage, which updates osg
     */
    public void paint(Graphics g) {
        dim = getSize();
        osi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        osg = osi.getGraphics();
        updateImage();
        g.drawImage(osi, 0, 0, this);
    }

    /**
     * This is called when the user dies
     * It calls init, which restarts the game
     */
    public void die() {
        System.out.printf("You scored %d. Starting a new game\n", score);
        init();
    }

    /**
     * addScore(s) adds s to the user's score and displays this on screen
     */
    public void addScore(int s) {
        score += s;
        repaint();
    }

    /**
     * keyPressed handles the user's keyboard events
     * The up key rotates clockwise
     * The right key moves the block right
     * The left key moves the block left
     * The space key fast forwards until the next piece drop
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
     * This restarts the game
     * If pieceManager is active, it calls pieceManager.stopSpawning
     * It resets the score to 0, creates a new grid, creates a new pieceManager
     * and starts spawning again
     */
    private void init() {
        if (pieceManager != null) pieceManager.stopSpawning();
        score = 0;
        grid = new TetrisGrid(this, nrows, ncols);
        pieceManager = new PieceManager(this, grid);
        pieceManager.startSpawning();
    }

    /**
     * This function updates the image
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

    private int score;
    private int nrows;
    private int ncols;

    private TetrisGrid grid;
    private PieceManager pieceManager;

    private BufferedImage osi;
    private Graphics osg;
    private Dimension dim;
}