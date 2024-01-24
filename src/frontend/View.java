package frontend;

import backend.*;
import backend.Shape;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GlyphMetrics;
import java.util.Stack;

public class View extends JFrame implements Observer {

    private final JPanel boardGUI = new JPanel(new GridLayout(3, 3));
    private TicTacToeGame game = TicTacToeGame.getInstance();
    boolean gameWon;
    boolean gameDraw;

    private final Stack<GameCommand> commandStack = new Stack<>();

    public View() {
        game.setStrategy(new TwoPlayerStrategy());
        this.setTitle("Tic Tac Toe");
        this.setResizable(false);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        constructBoardGUI();
        this.add(boardGUI);
        this.setVisible(true);
        game.addObserver(this);
    }

    public static void main(String[] args) {
        View view = new View();
    }

    private void constructBoardGUI() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Square square = new Square(i, j);
                square.addActionListener(square.buttonClickListener);
                boardGUI.add(square);
            }
        }
    }

    @Override
    public void notifyWin(boolean X){
        gameWon = true;

       /* if(X){

            //JOptionPane.showMessageDialog(this, "Player X wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            JOptionPane.showMessageDialog(this, "Player O wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }*/
    }


    @Override
    public void notifyDraw() {
        //JOptionPane.showMessageDialog(this, "DRAW!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        gameDraw = true;
    }


    private class Square extends JButton{
        private int row;
        private int column;

        public Square(int row, int column) {
            this.row = row;
            this.column = column;
            this.setPreferredSize(new Dimension(100, 100));
            this.setBackground(Color.WHITE);
        }

        ActionListener buttonClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    GameCommand command = new GameCommand(game, row, column);
                    command.makeMove();

                if(command.validMove) {
                    commandStack.push(command);


                    if(gameWon){
                        paintButtons();
                        if(game.getTurn()){
                            JOptionPane.showMessageDialog(null, "Player X wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Player O wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                        }
                        disableAllButtons();
                    }
                    if(gameDraw){
                        paintButtons();
                        JOptionPane.showMessageDialog(null, "Draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                        disableAllButtons();
                    }
                    paintButtons();

                }
            }
        };

        private void disableAllButtons() {
            Component[] components = boardGUI.getComponents();
            for (Component component : components) {
                if (component instanceof Square) {
                    ((Square) component).setEnabled(false);
                }
            }
        }

        private void paintButtons() {
            if(game.getTurn()){
                setText("X");
                setEnabled(false);
                setForeground(Color.BLACK);
            }
            else {
                setText("O");
                setEnabled(false);
                setForeground(Color.RED);
            }
            setFont(new Font("Arial", Font.PLAIN, 40));
            setFocusPainted(false);

            /*for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(!game.gameBoard[i][j].isEmpty()){
                        if(game.gameBoard[i][j].getShape().equals(Shape.X)){
                            setText("X");
                            setEnabled(false);
                            setForeground(Color.BLACK);
                        }
                        else{
                            setText("O");
                            setEnabled(false);
                            setForeground(Color.RED);
                        }
                        setFont(new Font("Arial", Font.PLAIN, 40));
                        setFocusPainted(false);
                    }
                }
            }*/

        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!isEnabled()) {
                // Custom appearance for disabled state
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(getForeground());
                g.setFont(getFont());
                drawCenteredString(g, getText(), getWidth(), getHeight());
            } else {
                super.paintComponent(g);
            }
        }

        private void drawCenteredString(Graphics g, String text, int width, int height) {
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int x = (width - metrics.stringWidth(text)) / 2;
            int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent();
            g.drawString(text, x, y);
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }
}

