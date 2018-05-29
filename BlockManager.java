import java.awt.Graphics;

public class BlockManager {
    public BlockManager(TetrisGrid grid) {
        tgrid = grid;
        piece = null;
    }

    public void startSpawning() {
        Timer mainLoopTimer = new Timer();
        TimerTask mainLoopTask = new TimerTask() {
            public void run() {
                if (dim != null) {
                    // go forward in time
                    timeStep();
                }
            }
        };
        mainLoopTimer.scheduleAtFixedRate(mainLoopTask, 0, updateInterval);
    }

    private void timeStep() {
        if (piece == null) {
            piece = new TetrisPiece();

        }
    }

    private static final int updateInterval = 2;

    private TetrisGrid tgrid;
    private TetrisPiece piece;
}