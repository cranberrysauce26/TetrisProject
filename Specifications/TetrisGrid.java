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
 * This class provides methods for drawing to the tetris grid
 */
    
    /**
     * @brief TetrisGrid constructor
     * @details The constructor initializes grid to have an additional 4 rows for reasons explained above
     * 
     * @param p The TetrisPanel to draw to
     * @param r The number of rows displayed to the screen
     * @param c The number of columns displayed to the screen
     */
    public TetrisGrid(TetrisPanel p, int r, int c) {
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
		
	/**
     * This function displays the grid onto the screen
     * lft, rt, tp, btm define the rectangular section of the panel to draw to
     * lft is the left, rt it the right, btm is the bottom, and tp is the top of the rectangular section
     * display centers the grid in the middle of the rectangular section and makes all cells square
	 * Color is set as white
     */
        }
    }
	
    /**
     * @brief Clears all rows that are completely filled in the standard Tetris style
     * an adds the appropriate score to the TetrisPanel
     */
    public void clearFilledRows() {
		
     /**
     * This function checks for rows that are completely filled and clears them
     * It does not draw to the screen. 100 points are added to the integer score.
     */
	 
    }

    /**
     * @brief Returns whether row r is the top row that is drawn to the screen
     * @details Since there are 4 extra rows that are not drawn to the screen, the top row is row 4
     * 
     * @param r The row to query
     * @return Whether row r is the top row that is drawn to the screen
     */
    public boolean isTopRow(int r) {
	/**
     * This function returns true if row r is the top row that is drawn to the screen
     * Since there are 4 extra rows that are not drawn to the screen, the top row is row 4
     */
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
     /**
     * Returns true if the cell at row r and column c in the grid is empty
     * Note that r is the row in the 2d array grid, not r in the grid displayed on the screen
     * So for example, if r = 0, then row 0 is not a row displayed in the screen
     */
    }

    public void drawCell(int r, int c, int colour) {
        /**
     * Draws the cell at row r and column c with colour
     */
    }
    
    /**
     * @brief Draws the grid to the screen
     * @details This method must be called after updates in order for the changes to be displayed on screen
     */
    public void finalize() {
        /**
     * This function is the public method used in order to draw changes from TetrisGrid onto the screen
     * It calls panel.repaint() which in turn will call the method display
     */
    }
    
    /**
     * @return Returns the number of rows in the grid.
     * This is the number of rows visible to the user plus 4
     */
    public int getRows() {
/**
     * Returns the total number of rows in the grid
     * Note that this is 4 more than the number of rows displayed on the screen
     */    
	}
    
    /**
     * @return The total number of columns
     */
    public int getCols() {
	/**
     * Returns the total number of columns
     */    
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
        //Array of 8 available colours for the cells
    };
}