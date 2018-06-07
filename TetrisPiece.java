import java.util.Random;
/**
 * Class tetrisPiece handles all methods related to moving and drawing a tetris pieces
 */
public class TetrisPiece {

    /**
     * TetrisPiece takes TetrisGrid grid as a parameter
     * It calls initialize, which randomly generates a piece
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
    public boolean occupiesTop() {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0 && tgrid.isTopRow(curRow+r)) return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the piece can move down
     * It works by finding the lowest row that is non empty in each of the 4 columns
     * and checking if the row below that in the grid is empty 
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
     * This function moves the piece down
     * First it undraws the current piece
     * Then it updates curRow
     * Then it draws the piece at the new position
     */
    public void moveDown() {
        undraw();
        ++curRow;
        draw();
    }

    /**
     * This function checks whether the piece can move left
     * It works by looping through each row, finding the leftmost column,
     * and checking if the cell to the left is occupied
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
     * This function moves the piece left
     */
    public void moveLeft() {
        undraw();
        --curCol;
        draw();
    }

    /**
     * This function checks if the piece can move right
     * It is almost identical to canMoveLeft
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
     * This function moves the piece right
     */
    public void moveRight() {
        undraw();
        ++curCol;
        draw();
    }

    /**
     * This function checks if the piece can be rotated clockwise
     * It works by noting that the i'th row and the j'th column of the rotated piece
     * is just the 3-j 'th row and i'th column of the current piece
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
     * This function rotates the piece clockwise using the observation that
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
     * This function undraws all occupied cells in the piece from the grid
     */
    private void undraw() {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0) tgrid.drawCell(curRow+r, curCol+c, 0);
            }
        }
    }

    /**
     * This function draws all occupied cells in the piece to the grid
     */
    private void draw() {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0) tgrid.drawCell(curRow+r, curCol+c, piece[r][c]);
            }
        }
    }

    /**
     * This function randomly generates a piece and rotates
     * it a random number of times in [0, 3]
     */
    private void initialize() {

        Random rnd = new Random();
        int c = rnd.nextInt(6)+1;

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
        } else {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, c, 0},
                {0, c, 0, 0},
                {0, c, 0, 0}
            };
        }

        int rot = rnd.nextInt(3);
        while (rot-- != 0) rotateClockwise();
    }

    private int curRow, curCol;
    private TetrisGrid tgrid;
    private int[][] piece;
}