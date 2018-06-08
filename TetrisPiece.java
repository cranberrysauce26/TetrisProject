import java.util.Random;

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
        tgrid = grid;
        initialize();
        draw();
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
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0 && tgrid.isTopRow(curRow+r)) return true;
            }
        }
        return false;
    }

    /**
     * @bried Returns whether the piece can move down
     * @details
     * It works by finding the lowest row that is non empty in each of the 4 columns
     * and checking if the row below that in the grid is empty
     * @return Returns whether the piece can move down
     */
    public boolean canMoveDown() {
        for (int c = 0; c < 4; ++c) {
            int r;
            for (r=3; r>=0; --r) if (piece[r][c]!=0) break;
            if (r!=-1) {
                if (curRow+r+1 >= tgrid.getRows()) return false;
                if (!tgrid.empty(curRow+r+1, curCol+c)) return false;
            }
        }
        return true;
    }

    /**
     * @brief Moves the piece down and draws to the grid
     */
    public void moveDown() {
        undraw();
        ++curRow;
        draw();
    }

    /**
     * @bried Returns whether the piece can move left
     * @details
     * It works by looping through each row, finding the leftmost occupied column,
     * and checking if the cell to the left in grid is occupied
     * @return Returns whether the piece can move left
     */
    public boolean canMoveLeft() {
        for (int r = 0; r < 4; ++r) {
            int c;
            for (c=0; c<4; ++c) if (piece[r][c]!=0) break;
            if (c!=4) {
                if (curCol+c-1 < 0) return false;
                if (!tgrid.empty(curRow+r, curCol+c-1)) return false;
            }
        }
        return true;
    }

    /**
     * @brief Moves the piece left and draws to the grid
     */
    public void moveLeft() {
        undraw();
        --curCol;
        draw();
    }

    /**
     * @bried Returns whether the piece can move left
     * @details
     * It works by looping through each row, finding the rightmost occupied column,
     * and checking if the cell to the right in grid is occupied
     * @return Returns whether the piece can move right
     */
    public boolean canMoveRight() {
        for (int r = 0; r < 4; ++r) {
            int c;
            for (c=3; c>=0; --c) if (piece[r][c]!=0) break;
            if (c!=-1) {
                if (curCol+c+1 >= tgrid.getCols()) return false;
                if (!tgrid.empty(curRow+r, curCol+c+1)) return false;
            }
        }
        return true;
    }

   /**
     * @brief Moves the piece right and draws to the grid
     */
    public void moveRight() {
        undraw();
        ++curCol;
        draw();
    }

    /**
     * @bried Returns whether the piece can rotate clockwise
     * @details
     * It works by noting that the i'th row and the j'th column of the rotated piece
     * is just the 3-j 'th row and i'th column of the current piece
     * @return Returns whether the piece can rotate clockwise
     */
    public boolean canRotateClockwise() {
        undraw();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (piece[3-j][i]!=0) {
                    if (!(0 <= curRow+i && curRow+i < tgrid.getRows() && 0 <= curCol+j  && curCol+j < tgrid.getCols())) {
                        draw();
                        return false;
                    }
                    if (!tgrid.empty(curRow+i, curCol+j)) {
                        draw();
                        return false;
                    }
                }
            }
        }
        draw();
        return true;
    }

     /**
     * @brief Rotates the piece clockwise and draws to the grid
     * @details This function rotates the piece clockwise using the observation that
     * the i'th row and the j'th column of the rotated piece
     * is just the 3-j 'th row and i'th column of the current piece
     */
    public void rotateClockwise() {
        undraw();
        int[][] old = new int[4][4];
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j) old[i][j] = piece[i][j];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
               piece[i][j] = old[3-j][i];
            }
        }
        draw();
    }

    /**
     * @brief Clears all occupied cells in the piece from the grid
     */
    private void undraw() {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0) tgrid.drawCell(curRow+r, curCol+c, 0);
            }
        }
    }

    /**
     * @brief Draws all occupied cells in the piece to the grid
     */
    private void draw() {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0) tgrid.drawCell(curRow+r, curCol+c, piece[r][c]);
            }
        }
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

        Random rnd = new Random();

        // c is a random integer in the interval [1, 7]
        int c = rnd.nextInt(7)+1;

        curRow = 0;
        curCol = rnd.nextInt(tgrid.getCols()-3);

        if (c==1) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {c, c, c, c},
                {0, 0, 0, 0}
            };
        } else if (c==2) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, 0, c, 0},
                {c, c, c, 0},
                {0, 0, 0, 0}
            };
        } else if (c==3) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, 0, 0},
                {c, c, c, 0},
                {0, 0, 0, 0}
            };
        } else if (c==4) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, c, 0},
                {c, c, 0, 0},
                {0, 0, 0, 0}
            };
        } else if (c == 5) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, c, 0},
                {0, c, c, 0},
                {0, 0, 0, 0}
            };
        } else if (c==6) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, c, 0},
                {0, 0, c, c},
                {0, 0, 0, 0}
            };
        } else {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, 0, 0},
                {0, c, c, c},
                {0, 0, 0, 0}
            };
        }

        int rot = rnd.nextInt(4);
        while (rot-- != 0) rotateClockwise();
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