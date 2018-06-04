import java.util.Random;
public class TetrisPiece {

    public TetrisPiece(TetrisGrid grid) {
        System.out.printf("making a new tetris piece\n");
        tgrid = grid;
        initialize();
        draw();
    }

    public boolean occupiesTop() {
        System.out.printf("checking occupies the top\n");
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0 && tgrid.isTopRow(curRow+r)) return true;
            }
        }
        return false;
    }

    public boolean canMoveDown() {
        System.out.printf("checking if can move downn\n");
        for (int c = 0; c < 3; ++c) {
            int r;
            for (r=3; r>=0; --r) if (piece[r][c]!=0) break;
            if (r!=-1) {
                if (curRow+r+1 >= tgrid.getRows()) return false;
                if (!tgrid.empty(curRow+r+1, curCol+c)) return false;
            }
        }
        return true;
    }

    public void moveDown() {
        System.out.printf("moving down\n");
        undraw();
        ++curRow;
        draw();
    }

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

    private int curRow, curCol;
    private TetrisGrid tgrid;
    private int[][] piece;

    private void undraw() {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0) tgrid.drawCell(curRow+r, curCol+c, 0);
            }
        }
    }

    private void draw() {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (piece[r][c]!=0) tgrid.drawCell(curRow+r, curCol+c, piece[r][c]);
            }
        }
    }

    private void initialize() {

        Random rnd = new Random();
        int type = rnd.nextInt(5);
        int c = tgrid.randomColour();

        curRow = 0;
        curCol = rnd.nextInt(tgrid.getCols()-3);

        if (type==0) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {c, c, c, c},
                {0, 0, 0, 0}
            };
        } else if (type==1) {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, 0, c, 0},
                {c, c, c, 0},
                {0, 0, 0, 0}
            };
        } else if (type==2) {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, 0, 0},
                {c, c, c, 0},
                {0, 0, 0, 0}
            };
        } else if (type==3) {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, c, 0},
                {c, c, 0, 0},
                {0, 0, 0, 0}
            };
        } else {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, c, c, 0},
                {0, c, c, 0},
                {0, 0, 0, 0}
            };
        }

        int rot = rnd.nextInt(3);
        while (rot-- != 0) rotateClockwise();
    }
}