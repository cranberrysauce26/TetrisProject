import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

public class TetrisGrid {

    public TetrisGrid(TetrisPanel p, int r, int c) {
        panel = p;
        nrows = r;
        ncols = c;
        grid = new int[nrows+4][ncols];
    }

    public void display(Graphics g, Dimension dim) {
        int len = Math.min(dim.width / ncols, dim.height / nrows);

        int left = (dim.width - ncols * len)/2;
        int top = (dim.height - nrows * len)/2;

        for (int r = 0; r < nrows; ++r) {
            for (int c = 0; c < ncols; ++c) {
                g.setColor(cellColours[grid[r+4][c]]);
                g.fillRect(left+c*len, top+r*len, len, len);
                g.setColor(Color.white);
                g.drawRect(left+c*len, top+r*len, len, len);
            }
        }
    }

    public void clearFilledRows() {
        int[][] old = new int[nrows+4][ncols];
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j)
                old[i][j] = grid[i][j];
        
        int target = nrows+3;
        for (int r = nrows+3; r >= 0; --r) {
            boolean filled = true;
            for (int c = 0; c < ncols; ++c) {
                if (old[r][c]==0) filled = false;
                grid[target][c] = old[r][c];
            }

            if (!filled) {
                --target;
            }
        }
    }

    public boolean isTopRow(int r) {
        return r == 4;
    }

    public boolean empty(int r, int c) {
        return grid[r][c] == 0;
    }

    public void drawCell(int r, int c, int colour) {
        grid[r][c] = colour;
    }

    public void finalize() {
        panel.repaint();
    }

    public int getRows() {
        return nrows+4;
    }

    public int getCols() {
        return ncols;
    }

    private TetrisPanel panel;
    private int[][] grid;
    private int nrows;
    private int ncols;
    private static final Color[] cellColours = new Color[]{
        Color.BLACK, Color.BLUE, Color.RED,
        Color.YELLOW, Color.ORANGE, Color.GREEN,
        Color.CYAN
    };
}