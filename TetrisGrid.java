import java.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

public class TetrisGrid {

    public TetrisGrid(JPanel p, int nrows, int ncols) {
        panel = p;
    }

    public void display(Graphics g, Dimension dim) {
        // TODO: draw it
    }

    public bool empty(int r, int c) {
        return grid[r][c] == 0;
    }

    public void drawCell(int r, int c, int colour) {
        // be careful with array indices
        grid[r][c] = colour;
    }

    public void finalize() {
        panel.repaint();
    }

    private JPanel panel;
    private int[][] grid;
}