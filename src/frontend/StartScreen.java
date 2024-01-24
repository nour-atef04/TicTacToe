package frontend;

import backend.SinglePlayerStrategy;
import backend.TicTacToeGame;
import backend.TwoPlayerStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen {

    private JPanel buttonPanel = new JPanel();
    private final JFrame startFrame = new JFrame("Start");

    TicTacToeGame game;

    StartScreen() {

        // designing the frame;
        designFrame();

        // create panel for buttons
        createButtonPanel();

        // create buttons
        createSinglePlayerButton();
        createTwoPlayerButton();

        startFrame.setVisible(true);
    }

    private void createButtonPanel() {
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        buttonPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        startFrame.add(buttonPanel, gbc);
    }

    private void designFrame() {
        startFrame.setSize(400, 200);
        startFrame.setLocationRelativeTo(null);
        startFrame.setLayout(new GridBagLayout());
        startFrame.setResizable(false);
    }

    private void createSinglePlayerButton() {
        JButton button = new JButton();
        button.setText("Single Player");
        button.setFocusPainted(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                TicTacToeGame g = TicTacToeGame.getInstance();
                g.restart();
                View view = new View(new SinglePlayerStrategy());
            }
        });

        button.setBackground(Color.WHITE);
        buttonPanel.add(button, gbc);
    }

    private void createTwoPlayerButton() {
        JButton button = new JButton();
        button.setText("  Two Player  ");
        button.setFocusPainted(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);

        button.addActionListener(e -> {
            startFrame.setVisible(false);
            TicTacToeGame g = TicTacToeGame.getInstance();
            g.restart();
            View view = new View(new TwoPlayerStrategy());
        });

        button.setBackground(Color.WHITE);
        buttonPanel.add(button, gbc);
    }
}

