import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Scanner;

public class Main {

    /**
     * @brief The entry point into the program
     * @details The class inputs the row and column from standard input and creates a Tetris game
     * 
     * @param args Unused
     */
    public static void main(String[] args) {
        // Input rows and columns
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter the number of rows and columns in your game\n");
        int nrows = scan.nextInt();
        int ncols = scan.nextInt();
        scan.close();

        JFrame frame = new JFrame("Tetris Panel");
        frame.add(new TetrisPanel(nrows, ncols));
        frame.setSize(600, 800);
        frame.setLocation(10, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}