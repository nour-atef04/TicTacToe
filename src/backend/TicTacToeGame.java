package backend;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame {

    private boolean turn;
    private boolean gameInPlay;
    public final Square[][] gameBoard = new Square[3][3];;
    private static volatile TicTacToeGame instance;
    private final List<Observer> observers = new ArrayList<>();


    private int moveCount = 0;

    private Strategy strategy = new TwoPlayerStrategy();

    private TicTacToeGame(){
        instance = null;
        gameInPlay = true;
        initBoard();
    }

    private void initBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                gameBoard[i][j] = new Square();
                gameBoard[i][j].row = i;
                gameBoard[i][j].col = j;
            }
        }
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
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

    public void validateMove(int row, int column) throws IllegalMoveException {
        if(row<0 || row >2 || column < 0 || column >2 || !gameBoard[row][column].isEmpty()){
            throw new IllegalMoveException();
        }
        strategy.makeMove(this,row,column);
        checkGameStatus();
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public boolean getTurn(){
        return this.turn;
    }

    void flipTurn(){
        turn = !turn;
    }

    void incrementMoves(){
        moveCount++;
    }


    private void checkGameStatus(){

        //if X or O won
            //check which one won
            if(checkXWin() || checkOWin()){
                notifyObserversWin();
                gameInPlay = false;
                return;
            }
        //if draw
        if(moveCount == 9){
            notifyObserversDraw();
            gameInPlay = false;
        }
    }

    void notifyObserversDraw(){
        for (Observer observer : observers){
            observer.notifyDraw();
        }
    }

    void notifyObserversWin(){
        for (Observer observer : observers){
            observer.notifyWin(turn);
        }
    }
    void notifySquare(int row, int column){
        for (Observer observer : observers){
            observer.notifySquare(row,column);
        }
    }

    private boolean checkWinInRows(Shape shape){
        //loop over every row
        for(int i=0; i<3; i++){
            if(checkConsecutive(shape, gameBoard[i][0], gameBoard[i][1], gameBoard[i][2])) return true;
        }
        return false;
    }

    private boolean checkWinInColumns(Shape shape){
        //loop over every column
        for(int i=0; i<3; i++){
            if(checkConsecutive(shape, gameBoard[0][i], gameBoard[1][i], gameBoard[2][i])) return true;
        }
        return false;
    }

    private boolean checkWinInDiagonals(Shape shape){
        //check both diagonals
        return(checkConsecutive(shape, gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]) ||
                checkConsecutive(shape, gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]));
    }

    private boolean checkConsecutive(Shape shape, Square s1, Square s2, Square s3){
        return(s1.shape != null && s1.shape.equals(shape) && s1.shape.equals(s2.shape) && s1.shape.equals(s3.shape));
    }

    public boolean checkXWin(){
        return(checkWinInRows(Shape.X) || checkWinInColumns(Shape.X) || checkWinInDiagonals(Shape.X));
    }

    public boolean checkOWin(){
        return(checkWinInRows(Shape.O) || checkWinInColumns(Shape.O) || checkWinInDiagonals(Shape.O));
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void decrementMoves() {
        moveCount--;
    }
}
