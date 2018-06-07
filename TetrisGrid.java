import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

/**
 * This class provides methods for drawing to the tetris grid
 */
public class TetrisGrid {

    /**
     * The constructor of TetrisGrid
     * p is the TetrisPanel
     * r is the number of rows
     * c is the number of cols
     * 
     * grid is the 2d array representing the tetris grid
     * I set the number of rows to grid to be 4 + nrows to
     * let a piece be drawn at the top without appearing on the screen
     */
    public TetrisGrid(TetrisPanel p, int r, int c) {
        panel = p;
        nrows = r;
        ncols = c;
        grid = new int[nrows+4][ncols];
    }

    /**
     * This function displays the grid onto the screen
     * lft, rt, tp, btm define the rectangular section of the panel to draw to
     * lft is the left, rt it the right, btm is the bottom, and tp is the top of the rectangular section
     * 
     * display centers the grid in the middle of the rectangular section and makes all cells square
     */
    public void display(Graphics g, int lft, int rt, int tp, int btm) {
        int len = Math.min((rt-lft) / ncols, (btm-tp) / nrows);

        int left = lft+((rt-lft) - ncols * len)/2;
        int top = tp+((btm-tp) - nrows * len)/2;

        for (int r = 0; r < nrows; ++r) {
            for (int c = 0; c < ncols; ++c) {
                g.setColor(cellColours[grid[r+4][c]]);
                g.fillRect(left+c*len, top+r*len, len, len);
                g.setColor(Color.white);
                g.drawRect(left+c*len, top+r*len, len, len);
            }
        }
    }

    /**
     * This function checks for rows that are completely filled and clears them
     * It does not draw to the screen
     */
    public void clearFilledRows() {
        int[][] old = new int[nrows+4][ncols];
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j)
                old[i][j] = grid[i][j];
        
        int numCleared = 0;

        int target = nrows+3;
        for (int r = nrows+3; r >= 0; --r) {
            boolean filled = true;
            for (int c = 0; c < ncols; ++c) {
                if (old[r][c]==0) filled = false;
                grid[target][c] = old[r][c];
            }

            if (!filled) {
                --target;
            } else {
                ++numCleared;
            }
        }

        panel.addScore(numCleared*100);
    }

    /**
     * This function returns true if row r is the top row that is drawn to the screen
     * Since there are 4 extra rows that are not drawn to the screen, the top row is row 4
     */
    public boolean isTopRow(int r) {
        return r == 4;
    }

    /**
     * Returns true if the cell at row r and column c in the grid is empty
     * Note that r is the row in the 2d array grid, not r in the grid displayed on the screen
     * So for example, if r = 0, then row 0 is not a row displayed in the screen
     */
    public boolean empty(int r, int c) {
        return grid[r][c] == 0;
    }

    /**
     * Draws the cell at row r and column c with colour
     */
    public void drawCell(int r, int c, int colour) {
        grid[r][c] = colour;
    }

    /**
     * This function is the public method used in order to draw changes from TetrisGrid onto the screen
     * It calls panel.repaint, which in turn will call the method display
     */
    public void finalize() {
        panel.repaint();
    }

    /**
     * Returns the total number of rows in the grid
     * Note that this is 4 more than the number of rows displayed on the screen
     */
    public int getRows() {
        return nrows+4;
    }

    /**
     * Returns the total number of columns
     */
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