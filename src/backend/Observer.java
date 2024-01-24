package backend;

public interface Observer {
    void notifyWin(boolean X);
    void notifyDraw();
    void notifySquare(int row, int column);
}
