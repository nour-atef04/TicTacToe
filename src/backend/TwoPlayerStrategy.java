package backend;

public class TwoPlayerStrategy implements Strategy{
    @Override
    public void makeMove(TicTacToeGame game, int row, int col) {
        if(!game.getTurn()){
            //X's turn
            game.gameBoard[row][col].addShape(Shape.X);
        }else{
            //O's turn
            game.gameBoard[row][col].addShape(Shape.O);
        }
        game.incrementMoves();
        game.flipTurn();
    }
}
