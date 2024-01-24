package backend;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame {
    private boolean turn;
    private boolean gameInPlay;
    final Square[][] gameBoard;
    private static volatile TicTacToeGame instance;
    private final List<Observer> observers = new ArrayList<>();

    private int moves;

    private Strategy strategy;

    private TicTacToeGame(Strategy strategy){
        instance = null;
        gameInPlay = true;
        gameBoard = new Square[3][3];
        this.strategy = strategy;
    }

    // SINGLETON
    public static TicTacToeGame getInstance(Strategy strategy) {
        if (instance == null) {
            synchronized (TicTacToeGame.class){
                if(instance == null) instance = new TicTacToeGame(strategy);
            }

        }
        return instance;
    }

    public static void restart(){
        instance = null;
    }

    public void validateMove(int row, int column) throws IllegalMoveException {
        if(row<0 || row >2 || column < 0 || column >2 || !gameBoard[row][column].isEmpty()){
            throw new IllegalMoveException();
        }
        strategy.makeMove(this,row,column);
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public boolean isTurn() {
        return turn;
    }
    public int getMoves() {
        return moves;
    }

    public void incrementMoves(){
        moves++;
    }

    public boolean checkerWinner(Shape shape) {
        return true;
    }
}
