package backend;

public class TicTacToeGame {

    private boolean turn;
    private boolean gameInPlay;
    private final Square[][] gameBoard;
    private static volatile TicTacToeGame instance;

    private TicTacToeGame(){
        instance = null;
        gameInPlay = true;
        gameBoard = new Square[3][3];
    }

    // SINGLETON
    public static TicTacToeGame getInstance() {
        if (instance == null) {
            synchronized (TicTacToeGame.class){
                if(instance == null) instance = new TicTacToeGame();
            }

        }
        return instance;
    }

    public static void restart(){
        instance = null;
    }

}
