package backend;

public class TicTacToeGame {

    private boolean turn;
    private boolean gameInPlay;
    private final Square[][] gameBoard;
    private static volatile TicTacToeGame instance;

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

}
