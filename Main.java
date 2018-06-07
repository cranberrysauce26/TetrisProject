import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * Input the rows and columns in the game
         */
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter the number of rows and columns in your game\n");
        int nrows = scan.nextInt();
        int ncols = scan.nextInt();
        scan.close();

        JFrame frame = new JFrame("Tetris Panel");
        frame.add(new TetrisPanel(nrows, ncols));
        frame.setSize(600, 800);
        frame.setLocation(10, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}