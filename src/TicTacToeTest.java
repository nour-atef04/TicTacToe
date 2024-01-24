import java.util.Scanner;
import backend.TicTacToeGame;
import backend.SinglePlayerStrategy;
import backend.IllegalMoveException;
import backend.TwoPlayerStrategy;

public class TicTacToeTest {

    public static void main(String[] args) {

        TicTacToeGame game = TicTacToeGame.getInstance();
        game.setStrategy(new SinglePlayerStrategy());

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main game loop
        while (game.getMoveCount() < 9) {

            // Get user input for row and column
            System.out.print("Enter the row (0, 1, or 2): ");
            int row = scanner.nextInt();

            System.out.print("Enter the column (0, 1, or 2): ");
            int col = scanner.nextInt();

            try {
                // Validate and make the move
                game.validateMove(row, col);
            } catch (IllegalMoveException e) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Print the current state of the board
            printBoard(game);
            if(game.checkOWin()){
                System.out.println("O WON");
                break;
            }
            if(game.checkXWin()){
                System.out.println("X WON");
                break;
            }
            if(game.getMoveCount() == 9){
                System.out.println("DRAW");
                break;
            }
        }
        // Close the scanner
        scanner.close();
    }

    // Helper method to print the current state of the board
    private static void printBoard(TicTacToeGame game) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(game.gameBoard[i][j].printShape() + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
