package backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SinglePlayerStrategy implements Strategy{
    private Shape player;
    private Shape opponent;
    TicTacToeGame game;

    public SinglePlayerStrategy(TicTacToeGame game) {
        this.game = game;
        if(!game.getTurn()){
            player = Shape.X;
            opponent = Shape.O;
        } else {
            player = Shape.O;
            opponent = Shape.X;
        }
    }

    @Override
    public void makeMove(TicTacToeGame game, int row, int col) {
        //player plays first
        if(!game.getTurn()){
            player = Shape.X;
            opponent = Shape.O;
        } else {
            player = Shape.O;
            opponent = Shape.X;
        }
        game.gameBoard[row][col].addShape(player);
        game.incrementMoves();
        //game ended
        if (game.getMoveCount() == 9){
            return;
        }
        Square aiMove = getBestOpponentMove();
        //computer plays
        aiMove.addShape(opponent);
        game.incrementMoves();
    }

    //here we are going to use the minimax algorithm, you can google it
    private Square getBestOpponentMove(){
        Square bestMove;
        bestMove = minimax(player);
        return bestMove;
    }

    private Square minimax(Shape shape) {
        //this is just a placeholder which forces us to take the first possible move, it means
       // X is maximizing score and O minimizing
        int bestScore = shape == Shape.X? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Square bestChoice = null;
        for (int i = 0 ; i < 3 ; i++){
            for (int j =0 ; j < 3 ; i++){
                if (game.gameBoard[i][j].isEmpty()){
                    game.gameBoard[i][j].addShape(shape);
                    int attemptedScore = minimaxChecker(shape);
                    if ((shape == Shape.X && attemptedScore > bestScore)
                    || (shape == Shape.O && attemptedScore < bestScore)){
                        bestScore = attemptedScore;
                        bestChoice = game.gameBoard[i][j];
                    }
                    game.gameBoard[i][j] = null;
                }
            }
        }
        return bestChoice;
    }

    //this is to check all child node paths
    private int minimaxChecker(Shape shape){
        //we must compare with X and O for it to work
        if (game.checkXWin()){
            return 1; //indicate X won
        } else if (game.checkOWin()) {
            return -1; //indicate O won
        } else if (game.getMoveCount() == 9) {
            return 0; //draw occurred
        }
        //if none of that happened we will try out all child moves, thus repeating minimax
        int bestScore = shape == Shape.X? Integer.MIN_VALUE : Integer.MAX_VALUE; // for child here
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0; j < 3; i++) {
                if (game.gameBoard[i][j].isEmpty()) {
                    game.gameBoard[i][j].addShape(shape);
                    if (shape == Shape.X) {
                        bestScore = Math.max(bestScore, minimaxChecker(Shape.O));
                    } else {
                        bestScore = Math.min(bestScore, minimaxChecker(Shape.X));
                    }
                    game.gameBoard[i][j] = null;
                }
            }
        }
        return bestScore;
    }
}
