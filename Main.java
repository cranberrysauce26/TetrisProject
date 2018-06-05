import javax.swing.JPanel;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Panel");
        frame.add(new TetrisPanel());
        frame.setSize(400, 800);
        frame.setLocation(10, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}