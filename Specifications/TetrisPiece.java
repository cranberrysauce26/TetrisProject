/**
 * @brief TetrisPiece handles all methods related to moving and drawing a tetris piece
 */
public class TetrisPiece {

    /**
     * @brief TetrisPiece constructor
     * @details It generates a random tetris piece and places it at the top of the grid
     * 
     * @param grid The TetrisGrid to draw to
     */
    public TetrisPiece(TetrisGrid grid) {
	/* initializes a Tetris grid and calls the draw function to display the grid onto the screen */
    }

    /**
     * This function returns true if the piece occupies the top drawable row of the grid
     * This function is used to check whether the game should end 
     */
    /**
     * @brief Returns whether the piece occupies the top drawable row in tgrid
     * This function is used to check whether the game should end
     * @return Whether the piece occupies the top drawable row in tgrid
     */
    public boolean occupiesTop() {
	/**
     *This function returns true if the piece occupies the top drawable row of the grid. Otherwise the function returns false.
	 *This function is used to check whether the game should end 
    */
    }

    /**
     * @brief Returns whether the piece can move down
     * @details
     * It works by finding the lowest row that is non empty in each of the 4 columns
     * and checking if the row below that in the grid is empty
     * @return Returns whether the piece can move down
     */
    public boolean canMoveDown() {
     /**
     * Returns true if the piece can move down
     * It works by finding the lowest row that is non empty in each of the 4 columns
     * and checking if the row below that in the grid is empty 
     */
    }

    /**
     * @brief Moves the piece down and draws to the grid
     */
    public void moveDown() {
	/**
     * This function moves the piece down
     * First it undraws the current piece
     * Then it updates curRow by adding 1 to the row 
     * Finally it draws the piece at the new position
     */
    }

    /**
     * @brief Returns whether the piece can move left
     * @details
     * It works by looping through each row, finding the leftmost occupied column,
     * and checking if the cell to the left in grid is occupied
     * @return Returns whether the piece can move left
     */
    public boolean canMoveLeft() {
	/**
     * This function checks whether the piece can move left
     * It works by looping through each row, finding the leftmost column,
     * and checking if the cell to the left is occupied
     */
    }

    /**
     * @brief Moves the piece left and draws to the grid
     */
    public void moveLeft() {
	/**
     * This function moves the piece left
	 First it undraws the current piece
	 Then it updates curCol by subtracting 1 to the column #
	 Finally it draws the piece at the new position
     */

    }

    /**
     * @brief Returns whether the piece can move left
     * @details
     * It works by looping through each row, finding the rightmost occupied column,
     * and checking if the cell to the right in grid is occupied
     * @return Returns whether the piece can move right
     */
    public boolean canMoveRight() {
     /**
     * This function checks if the piece can move right
     * It is a symmetrical to canMoveLeft
     */
    }

    /**
     * @brief Moves the piece right and draws to the grid
     */
    public void moveRight() {
    /**
     * This function moves the piece left
	 First it undraws the current piece
	 Then it updates curCol by adding 1 to the column #
	 Finally it draws the piece at the new position
     */
    }

    /**
     * @brief Returns whether the piece can rotate clockwise
     * @details
     * It works by noting that the i'th row and the j'th column of the rotated piece
     * is just the 3-j 'th row and i'th column of the current piece
     * @return Returns whether the piece can rotate clockwise
     */
    public boolean canRotateClockwise() {
	/**
     * This function checks if the piece can be rotated clockwise
     * Noting that the i'th row and the j'th column of the rotated piece
     * is  the 3-j 'th row and i'th column of the current piece, 
	 * if any of the desired rotated positions are full the function will return false
	 * otherwise the function returns true
     */
	
    }

    /**
     * @brief Rotates the piece clockwise and draws to the grid
     * @details This function rotates the piece clockwise using the observation that
     * the i'th row and the j'th column of the rotated piece
     * is just the 3-j 'th row and i'th column of the current piece
     */
    public void rotateClockwise() {
		
	/**
     * This function rotates the piece clockwise noting that
     * the i'th row and the j'th column of the rotated piece
     * is just the 3-j 'th row and i'th column of the current piece
	 * the pieces have its coordinates changed in a two for loop for the x coordinates and y coordinates four pieces respectively
     */
 
    }

    /**
     * @brief Clears all occupied cells in the piece from the grid
     */
    private void undraw() {
		
    /**
     * This function uses a double for loop that loops through the coordinates of all cells in a piece
	 * This function undraws all occupied cells in the piece from the grid
     */
   
    }

    /**
     * @brief Draws all occupied cells in the piece to the grid
     */
    private void draw() {
		/**
     * This function functions through a double for loop that loops through the coordinates of all cells in a piece
	 * This function draws all occupied cells in the piece from the grid
       */
    }

    /**
     * @brief Randomly generates a piece and gives the piece a
     * random rotation
     * @details
     * There are 7 different types of tetris pieces. This method generates a random integer
     * c in the range [1, 7] that identifies the type of tetris piece. The variable c is also used
     * as the colour index for TetrisGrid to draw. Finally, it generates a random integer rot in [0, 3]
     * and rotates clockwise rot times.
     */
    private void initialize() {

		/** this function generates a random number from 1 to 7.
		Depending on the randomly generated number this function calls one of 7 pieces that correspond to the 7 Tetris pieces
		Each piece is an arrangement of empty and filled cells in a 4 by 4 int 2D array.
		Position of the piece as initialized as Row 0 and Col in the middle of the grid.
		*/
    }

    // curRow is the row that the top of our 4x4 array is on in the grid
    private int curRow;

    // curCol is the column that the left of our 4x4 array is on in the grid
    private int curCol;

    // tgrid is the TetrisGrid that our TetrisPiece will draw to
    private TetrisGrid tgrid;

    // piece is a 4x4 array that represents the tetris piece
    // The nonzero cells store the colour to be drawn onto the grid
    private int[][] piece;
}