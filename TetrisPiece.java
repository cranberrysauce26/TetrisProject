import java.util.Random;
public class TetrisPiece {

    public TetrisPiece(TetrisGrid grid) {
        System.out.printf("creating a new tetris piece\n");
        tgrid = grid;
        initialize();
    }

    public boolean canMoveDown() {
        if (curRow+4 >= tgrid.getRows()) return false;
        for (int j = 0; j < 4; ++j) {
            if (piece[3][j] != 0 && !tgrid.empty(curRow+4, curCol+j)) {
                return false;
            }
        }
        return true;
    }

    public boolean isWithinGrid() {
        return true;
    }

    public void moveDown() {
        ++curRow;
    }

    public void draw() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                tgrid.drawCell(curRow+i, curCol+j, piece[i][j]);
            }
        }
    }

    private int curRow, curCol;
    private TetrisGrid tgrid;
    private int[][] piece;

    private void initialize() {

        Random rnd = new Random();
        int type = rnd.nextInt() % 5;

        curRow = 0;
        curCol = rnd.nextInt() % (tgrid.getCols()-3);

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