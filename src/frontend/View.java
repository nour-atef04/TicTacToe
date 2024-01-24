package frontend;

import backend.IllegalMoveException;
import backend.Observer;
import backend.SinglePlayerStrategy;
import backend.TicTacToeGame;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements Observer {

    private final JPanel boardGUI = new JPanel(new GridLayout(3, 3));
    private TicTacToeGame game = TicTacToeGame.getInstance();

    public View() {
        this.setTitle("Tic Tac Toe");
        this.setResizable(false);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        constructBoardGUI();
        this.add(boardGUI);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        View view = new View();
    }

    private void constructBoardGUI() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                boardGUI.add(new Square(i, j));
            }
    }

    @Override
    public void notifyWin(boolean X) {

    }

    @Override
    public void notifyDraw() {

    }


    private class Square extends JButton {
        private int row;
        private int column;

        public Square(int row, int column) {
            this.row = row;
            this.column = column;
            this.setPreferredSize(new Dimension(100, 100));
        }

        ActionListener buttonClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    game.validateMove(row,column);
                } catch (IllegalMoveException ex) {
                    System.out.println("Illegal move");
                }
                paintButton();
            }
        };

        private void paintButton() {
            if(game.getMoveCount() % 1 == 0){
                setText("X");
            }
            else {
                setText("O");
            }
            setFont(new Font("Arial", Font.PLAIN, 40));
            setFocusPainted(false);
        }


        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }
}

