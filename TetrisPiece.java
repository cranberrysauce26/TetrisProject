import java.util.Random;
public class TetrisPiece {

    public TetrisPiece(TetrisGrid grid) {
        tgrid = grid;
        initialize();
        draw();
    }

    public boolean canMoveDown() {
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
        undraw();
        ++curRow;
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

        curRow = 0;
        curCol = rnd.nextInt(tgrid.getCols()-3);

        if (type==0) {
            piece = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
            };
        } else if (type==1) {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
            };
        } else if (type==2) {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
            };
        } else if (type==3) {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0}
            };
        } else {
             piece = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
            };
        }
    }
}