import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

/**
 * This class provides methods for drawing to the tetris grid
 */
/**
 * @brief Provides a toolbox for the tetris grid
 * @details
 * TetrisGrid displays the 2d tetris grid and allows clients to update or query
 * cells. It does not draw the updates onto the screen until the method finalize() is called.
 * It also adds an additional 4 rows above those drawn onto the screen. Therefore if there are r
 * rows displayed on the screen, the grid internally has r+4 rows. This is to allow pieces to begin at
 * the top of the grid without worrying about collisions
 */
public class TetrisGrid {

    /**
     * @brief TetrisGrid constructor
     * @details The constructor initializes grid to have an additional 4 rows for reasons explained above
     * 
     * @param p The TetrisPanel to draw to
     * @param r The number of rows displayed to the screen
     * @param c The number of columns displayed to the screen
     */
    public TetrisGrid(TetrisPanel p, int r, int c) {
        panel = p;
        nrows = r;
        ncols = c;
        grid = new int[nrows+4][ncols];
    }

    /**
     * @brief Draws the grid using graphics g in the specified box
     * @details The method centers the grid in the given rectangle and makes each cell a square
     * 
     * @param g The Graphics instance to draw with
     * @param lft The left bound of the rectangle
     * @param rt The right bound of the rectangle
     * @param tp The top bound of the rectangle
     * @param btm The bottom bound of the rectangle
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
     * @brief Clears all rows that are completely filled in the standard Tetris style
     * an adds the appropriate score to the TetrisPanel
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
     * @brief Returns whether row r is the top row that is drawn to the screen
     * @details Since there are 4 extra rows that are not drawn to the screen, the top row is row 4
     * 
     * @param r The row to query
     * @return Whether row r is the top row that is drawn to the screen
     */
    public boolean isTopRow(int r) {
        return r == 4;
    }

    /**
     * @brief Returns whether the cell in row r and column c is empty
     * 
     * @param r The row to query (note that the row may not be a row displayed on the screen)
     * @param c The column to query
     * 
     * @return Whether the cell in row r and column c is empty
     */
    public boolean empty(int r, int c) {
        return grid[r][c] == 0;
    }

    /**
     * @brief Fills the cell at row r and column c with colour cellColours[colour]
     * 
     * @param r The row to update
     * @param c The column to update
     * @param colour The index of the colour in the array cellColours
     */
    public void drawCell(int r, int c, int colour) {
        grid[r][c] = colour;
    }

    /**
     * @brief Draws the grid to the screen
     * @details This method must be called after updates in order for the changes to be displayed on screen
     */
    public void finalize() {
        panel.repaint();
    }
   
    /**
     * @return Returns the number of rows in the grid.
     * This is the number of rows visible to the user plus 4
     */
    public int getRows() {
        return nrows+4;
    }

    /**
     * @return The total number of columns
     */
    public int getCols() {
        return ncols;
    }

    // The TetrisPanel to draw to
    private TetrisPanel panel;
    // The internal representation of the grid
    // Each entry in grid contains the index of the colour to draw the corresponding cell
    private int[][] grid;
    // The number of rows visible to the user
    // Note the number of rows in grid is 4 more than nrows
    private int nrows;
    // The number of columns in the grid
    private int ncols;
    // The colours that can be drawn to the grid
    private static final Color[] cellColours = new Color[]{
        Color.BLACK, Color.BLUE, Color.RED,
        Color.YELLOW, Color.ORANGE, Color.GREEN,
        Color.CYAN, Color.MAGENTA
    };
}