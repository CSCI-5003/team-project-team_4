package oosd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class HighScores {

    public HighScores() {
        JFrame mainFrame = new JFrame("High Scores");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(350, 500));
        mainFrame.setLayout(new BorderLayout());

        // Main panel for the list of high scores
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 20));
        mainPanel.setLayout(new GridLayout(11, 1)); // One row for title, 10 rows for scores

        // Title label for high scores list
        JLabel titleLabel = new JLabel("High Scores:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel);

        // Adding 10 score labels
        for (int i = 1; i <= 10; i++) {
            JLabel scoreLabel = new JLabel("Score " + i, SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            mainPanel.add(scoreLabel);
        }

        // "Exit to Menu" button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        JButton exitButton = new JButton("Exit to Menu");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonPanel.add(exitButton);

        // Add mainPanel and buttonPanel to the frame
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(buttonPanel, BorderLayout.EAST);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HighScores());
    }
}
