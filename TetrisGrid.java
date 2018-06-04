import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class TetrisGrid {

    public TetrisGrid(TetrisPanel p, int r, int c) {
        System.out.printf("in tetris grid constructor with r = %d, c = %d\n", r, c);
        panel = p;
        nrows = r;
        ncols = c;
        grid = new int[nrows+4][ncols];
    }

    public void display(Graphics g, Dimension dim) {
        int width = dim.width / ncols;
        int height = dim.height / nrows;

        for (int r = 0; r < nrows; ++r) {
            for (int c = 0; c < ncols; ++c) {
                g.setColor(cellColours[grid[r+4][c]]);
                // g.setColor(cellColours[1]);
                g.fillRect(c*width, r*height, width, height);
                g.setColor(Color.white);
                g.drawRect(c*width, r*height, width, height);
            }
        }
    }

    public boolean empty(int r, int c) {
        return grid[r][c] == 0;
    }

    public void drawCell(int r, int c, int colour) {
        // be careful with array indices
        grid[r][c] = colour;
    }

    public void finalize() {
        panel.repaint();
    }

    public int getRows() {
        return nrows+4;
    }

    public int getCols() {
        System.out.printf("in getcols returning %d\n", ncols);
        return ncols;
    }

    private TetrisPanel panel;
    private int[][] grid;
    private int nrows;
    private int ncols;
    private static final Color[] cellColours = new Color[]{Color.BLACK, Color.BLUE};
}