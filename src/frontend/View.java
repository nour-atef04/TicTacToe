package frontend;

import backend.Observer;
import backend.TicTacToeGame;

import javax.swing.*;
import java.awt.*;

public class View implements Observer {

    private final JFrame boardFrame = new JFrame("Tic Tac Toe");
    private final JPanel boardGUI = new JPanel(new GridLayout(3, 3));
    //private TicTacToeGame board = TicTacToeGame.getInstance();



}
