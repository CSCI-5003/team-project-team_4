package oosd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HighScores extends JFrame{
    
    private JButton returnButton;

    public HighScores() {

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
        headingPanel.setBounds(0,0,700,200);
        headingPanel.setBackground(ColorCodes.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        JLabel highScore = new JLabel("High Scores");
        highScore.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        highScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScore.setAlignmentY(Component.CENTER_ALIGNMENT);
        highScore.setFont(new Font("Verdana", Font.BOLD, 20));
        highScore.setForeground(ColorCodes.purple);

        // Score Panel
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(0,200,700,500);
        scorePanel.setBackground(ColorCodes.white);
        scorePanel.setLayout(new GridLayout(10,1));

        JLabel[] scoreLabels = new JLabel[11];

        for (int i = 1; i < 11; i++) {
            String word = "Score " + i;
            scoreLabels[i] = new JLabel(String.valueOf(word), SwingConstants.CENTER);
            scoreLabels[i].setFont(new Font("Veranda", Font.PLAIN, 15));
            scoreLabels[i].setBackground(ColorCodes.white);

            scorePanel.add(scoreLabels[i]);
        }

        // Return Panel
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS)); 
        returnPanel.setBackground(ColorCodes.white);
        
        returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(ColorCodes.purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 5));
        returnButton.setForeground(ColorCodes.white);

        // Fill Panels
        this.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(scorePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(returnPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        headingPanel.add(title);
        headingPanel.add(highScore);
        returnPanel.add(returnButton);

        this.pack();
        this.setVisible(true);
    }

    public JButton getReturnBut() {
        return returnButton;
    }
}
