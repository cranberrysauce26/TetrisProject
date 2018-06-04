import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;

@SuppressWarnings("serial")
public class TetrisPanel extends JPanel {

    public TetrisPanel() {
        grid = new TetrisGrid(this, 7, 4);
        blockManager = new BlockManager(this, grid);
        blockManager.startSpawning();
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
        blockManager.stopSpawning();
        grid = new TetrisGrid(this, 7, 4);
        blockManager = new BlockManager(this, grid);
        blockManager.startSpawning();
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