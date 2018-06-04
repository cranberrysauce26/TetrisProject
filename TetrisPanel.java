import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;

@SuppressWarnings("serial")
public class TetrisPanel extends JPanel {

    public TetrisPanel() {
        System.out.printf("in tetris panel constructor\n");
        grid = new TetrisGrid(this, 20, 20);
        blockManager = new BlockManager(this, grid);
        startSpawning();
    }

    private void startSpawning() {
        System.out.printf("SPAWNING in TetrisPanel\n");
        blockManager.startSpawning();
    }

    public void paint(Graphics g) {
        dim = getSize();
        osi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        osg = osi.getGraphics();
        updateImage();
        g.drawImage(osi, 0, 0, this);
    }

    public void Die() {

    }

    private void updateImage() {
        if (grid != null) {
            grid.display(osg, dim);
        }
    }

    private TetrisGrid grid;
    private BlockManager blockManager;

    private BufferedImage osi;
    private Graphics osg;
    private Dimension dim;
}