package backend;

public class GameCommand implements Command {
    TicTacToeGame ticTacToeGame;
    int row;
    int column;

    public boolean validMove = true;

    public GameCommand(TicTacToeGame game, int row, int column) {
        this.ticTacToeGame = game;
        this.row = row;
        this.column = column;
    }

    @Override
    public void makeMove() {
        try {
            ticTacToeGame.validateMove(row, column);
        } catch (IllegalMoveException e) {
            validMove = false;
            System.out.println("Illegal move");
        }
    }

}
