import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class TetrisPanel extends JPanel implements KeyListener {

    public TetrisPanel() {
        init();
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void paint(Graphics g) {
        dim = getSize();
        osi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        osg = osi.getGraphics();
        updateImage();
        g.drawImage(osi, 0, 0, this);
    }

    public void die() {
        System.out.println("I'm dead\n");
        init();
    }

    public void addScore(int s) {
        score += s;
        repaint();
    }

    // methods of KeyListener
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

    private void init() {
        if (pieceManager != null) pieceManager.stopSpawning();
        grid = new TetrisGrid(this, 20, 7);
        pieceManager = new PieceManager(this, grid);
        pieceManager.startSpawning();
    }

    private void updateImage() {
        if (grid != null) {
            grid.display(osg, 0, dim.width, dim.height / 10, dim.height);
            osg.setColor(Color.RED);
            osg.drawString("Score: "+score, dim.width/2, dim.height / 12);
        }
    }

    private int score;

    private TetrisGrid grid;
    private PieceManager pieceManager;

    private BufferedImage osi;
    private Graphics osg;
    private Dimension dim;
}