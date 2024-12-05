package oosd.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import oosd.model.ScoreManager;

public class HighScores extends JFrame {

    private ScoreManager scoreManager;
    private JButton returnButton;
    private JPanel scorePanel;

    public HighScores(ScoreManager scoreManager) {
        // Initialize ScoreManager
        if (scoreManager == null) {
            throw new IllegalArgumentException("ScoreManager cannot be null");
        }
        this.scoreManager = scoreManager;

        // Create scoreFrame
        this.setTitle("Connections");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 800));
        this.setResizable(false);

        // Create mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 800));
        mainPanel.setBackground(ColorCodes.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 20));

        // Create Title & Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(ColorCodes.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        headingPanel.setPreferredSize(new Dimension(700, 70));

        JLabel title = new JLabel("Connections");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        JLabel highScore = new JLabel("High Scores");
        highScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScore.setFont(new Font("Verdana", Font.BOLD, 20));
        highScore.setForeground(ColorCodes.purple);

        headingPanel.add(title);
        headingPanel.add(highScore);

        // Score Panel
        scorePanel = new JPanel();
        scorePanel.setBackground(ColorCodes.white);
        scorePanel.setLayout(new GridLayout(10, 1, 0,0)); // Space between rows
        scorePanel.setPreferredSize(new Dimension(700, 550));

        // Return Panel
        JPanel returnPanel = new JPanel();
        returnPanel.setBackground(ColorCodes.white);
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS));
        returnPanel.setPreferredSize(new Dimension(700, 30));

        returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(ColorCodes.purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 5));
        returnButton.setForeground(ColorCodes.white);
        returnButton.setActionCommand("RTM_HighScores");

        returnPanel.add(returnButton);

        // Add Components to Main Panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(headingPanel);
        mainPanel.add(scorePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        mainPanel.add(returnPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing

        // Populate Scores
        updateScores(scoreManager.getHighScores());

        // Set Frame Content
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    public void updateScores(List<Integer> scores) {
        // Clear existing score panel contents
        scorePanel.removeAll();
        // Populate with scores
        for (int i = 0; i < scores.size(); i++) {
            JLabel scoreLabel = new JLabel("Rank " + (i + 1) + ": " + scores.get(i), SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
            scorePanel.add(scoreLabel);
        }
        // Fill remaining ranks if less than 10
        for (int i = scores.size(); i < 10; i++) {
            JLabel scoreLabel = new JLabel("Rank " + (i + 1) + ": ---", SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
            scorePanel.add(scoreLabel);
        }
        // Refresh UI
        scorePanel.revalidate();
        scorePanel.repaint();
    }

    public JButton getReturnBut() {
        return returnButton;
    }
}
