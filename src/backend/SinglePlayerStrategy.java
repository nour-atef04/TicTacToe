package backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SinglePlayerStrategy implements Strategy{
    private Shape player;
    private Shape opponent;

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
        game.notifySquare(row,col);
        //game ended
        if (game.getMoveCount() == 9 || game.checkXWin() || game.checkOWin()){
            return;
        }

        Square aiMove = getBestOpponentMove(game);
        //computer plays
        aiMove.addShape(opponent);
        game.incrementMoves();
        game.notifySquare(aiMove.row , aiMove.col);
    }

    //here we are going to use the minimax algorithm, you can google it
    private Square getBestOpponentMove(TicTacToeGame game){
        Square bestMove;
        bestMove = minimax(game, opponent);
        return bestMove;
    }

    private Square minimax(TicTacToeGame game, Shape shape) {
        //this is just a placeholder which forces us to take the first possible move, it means
       // X is maximizing score and O minimizing
        int bestScore = shape == Shape.X? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Square bestChoice = null;
        for (int i = 0 ; i < 3 ; i++){
            for (int j =0 ; j < 3 ; j++){
                if (game.gameBoard[i][j] != null && game.gameBoard[i][j].isEmpty()){
                    game.gameBoard[i][j].addShape(shape);
                    game.incrementMoves();
                    int attemptedScore;
                    if (shape == Shape.X) {
                        attemptedScore = minimaxChecker(game, Shape.O);
                    }
                    else {
                        attemptedScore = minimaxChecker(game, Shape.X);
                    }
                    if ((shape == Shape.X && attemptedScore > bestScore)
                    || (shape == Shape.O && attemptedScore < bestScore)){
                        bestScore = attemptedScore;
                        bestChoice = game.gameBoard[i][j];
                     }
                    game.decrementMoves();
                    game.gameBoard[i][j].shape = null;
                }
            }
        }
        return bestChoice;
    }

    //this is to check all child node paths
    private int minimaxChecker(TicTacToeGame game, Shape shape){
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
            for (int j = 0; j < 3; j++) {
                if (game.gameBoard[i][j] != null && game.gameBoard[i][j].isEmpty()) {
                    game.gameBoard[i][j].addShape(shape);
                    game.incrementMoves();
                    if (shape == Shape.X) {
                        bestScore = Math.max(bestScore, minimaxChecker(game, Shape.O));
                    } else {
                        bestScore = Math.min(bestScore, minimaxChecker(game, Shape.X));
                    }
                    game.decrementMoves();
                    game.gameBoard[i][j].shape = null;
                }
            }
        }
        return bestScore;
    }
}
