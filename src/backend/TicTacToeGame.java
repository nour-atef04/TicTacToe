package backend;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame {

    private boolean turn;
    private boolean gameInPlay;
    private final Square[][] gameBoard;
    private static volatile TicTacToeGame instance;
    private final List<Observer> observers = new ArrayList<>();

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

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

}
