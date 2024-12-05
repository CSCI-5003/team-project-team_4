package oosd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGame extends JFrame {
    
    private JButton returnButton;

    private JPanel groupOnePanel;
    private JPanel groupTwoPanel;
    private JPanel groupThreePanel;
    private JPanel groupFourPanel;

    private JLabel groupOneCategory;
    private JLabel groupTwoCategory;
    private JLabel groupThreeCategory;
    private JLabel groupFourCategory;

    private JLabel groupOneWords;
    private JLabel groupTwoWords;
    private JLabel groupThreeWords;
    private JLabel groupFourWords;

    private JLabel result;
    private JLabel score;

    public EndGame() {

        System.out.println("made it here!");
        // Create menuFrame
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
        headingPanel.setPreferredSize(new Dimension(700, 100));
        headingPanel.setBackground(ColorCodes.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(25, 30, 10, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        // Score Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setPreferredSize(new Dimension(700, 200));
        resultPanel.setBackground(ColorCodes.white);
        resultPanel.setLayout(null);
        
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(80,0,500,175);
        scorePanel.setBackground(ColorCodes.lightPurple);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        result = new JLabel();
        result.setText("");
        result.setAlignmentX(CENTER_ALIGNMENT);
        result.setFont(new Font("Veranda", Font.BOLD, 35));
        result.setBorder(BorderFactory.createEmptyBorder(25,5,5,5));

        JLabel yourScore = new JLabel();
        yourScore.setText("Your Score:");
        yourScore.setAlignmentX(CENTER_ALIGNMENT);
        yourScore.setFont(new Font("Veranda", Font.BOLD, 20));
        yourScore.setBorder(BorderFactory.createEmptyBorder(15,5,0,5));

        score = new JLabel();
        score.setText("");
        score.setAlignmentX(CENTER_ALIGNMENT);
        score.setFont(new Font("Veranda", Font.BOLD, 25));
        score.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));

        // Answer Panel
        JPanel answerPanel = new JPanel();
        answerPanel.setPreferredSize(new Dimension(700, 345));
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
        answerPanel.setAlignmentX(CENTER_ALIGNMENT);
        answerPanel.setAlignmentY(CENTER_ALIGNMENT);
        answerPanel.setLayout(null);
        answerPanel.setBackground(ColorCodes.white);

        // One
        groupOnePanel = new JPanel();
        groupOnePanel.setBounds(175,0,300,75);
        groupOnePanel.setLayout(new BoxLayout(groupOnePanel, BoxLayout.Y_AXIS));
        
        groupOneCategory = new JLabel();
        groupOneCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupOneCategory.setText("category placeholder 1");
        groupOneCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        groupOneWords = new JLabel();
        groupOneWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupOneWords.setText("words placeholder 1");
        groupOneWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        groupOnePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        groupOnePanel.add(groupOneCategory);
        groupOnePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        groupOnePanel.add(groupOneWords);

        // Two
        groupTwoPanel = new JPanel();
        groupTwoPanel.setBounds(175,90,300,75);
        groupTwoPanel.setLayout(new BoxLayout(groupTwoPanel, BoxLayout.Y_AXIS));

        groupTwoCategory = new JLabel();
        groupTwoCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupTwoCategory.setText("category placeholder 2");
        groupTwoCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        groupTwoWords = new JLabel();
        groupTwoWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupTwoWords.setText("words placeholder 2");
        groupTwoWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        groupTwoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        groupTwoPanel.add(groupTwoCategory);
        groupTwoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        groupTwoPanel.add(groupTwoWords);

        // Three
        groupThreePanel = new JPanel();
        groupThreePanel.setBounds(175,180,300,75);
        groupThreePanel.setLayout(new BoxLayout(groupThreePanel, BoxLayout.Y_AXIS));

        groupThreeCategory = new JLabel();
        groupThreeCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupThreeCategory.setText("category placeholder 3");
        groupThreeCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        groupThreeWords = new JLabel();
        groupThreeWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupThreeWords.setText("words placeholder 3");
        groupThreeWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        groupThreePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        groupThreePanel.add(groupThreeCategory);
        groupThreePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        groupThreePanel.add(groupThreeWords);

        // Four
        groupFourPanel = new JPanel();
        groupFourPanel.setBounds(175,270,300,75);
        groupFourPanel.setLayout(new BoxLayout(groupFourPanel, BoxLayout.Y_AXIS));

        groupFourCategory = new JLabel();
        groupFourCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupFourCategory.setText("category placeholder 4");
        groupFourCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        groupFourWords = new JLabel();
        groupFourWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupFourWords.setText("words placeholder 4");
        groupFourWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        groupFourPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        groupFourPanel.add(groupFourCategory);
        groupFourPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        groupFourPanel.add(groupFourWords);

        // Return Panel
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS)); 
        returnPanel.setBackground(ColorCodes.white);
        returnPanel.setPreferredSize(new Dimension(700, 75));
        
        returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(ColorCodes.darkGray);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(ColorCodes.darkGray, 5));
        returnButton.setForeground(ColorCodes.lightGray);
        returnButton.setActionCommand("RTM_EndGame");

        answerPanel.add(groupOnePanel);
        answerPanel.add(groupTwoPanel);
        answerPanel.add(groupThreePanel);
        answerPanel.add(groupFourPanel);
        answerPanel.add(returnButton);

        // Fill Panels
        
        mainPanel.add(headingPanel);
        mainPanel.add(resultPanel);
        mainPanel.add(answerPanel);
        mainPanel.add(returnPanel);

        headingPanel.add(title);

        scorePanel.add(result);
        scorePanel.add(yourScore);
        scorePanel.add(score);
        
        resultPanel.add(scorePanel);
        returnPanel.add(returnButton);
        this.add(mainPanel);

        this.pack();
    }

    public JButton getReturnBut() {
        return returnButton;
    }

    // Set Answer Bars
    public void setGroupOneAnswers(Color color, String category, String words) {
        groupOnePanel.setBackground(color);
        groupOneCategory.setText(category);
        groupOneWords.setText(words);
    }

    public void setGroupTwoAnswers(Color color, String category, String words) {
        groupTwoPanel.setBackground(color);
        groupTwoCategory.setText(category);
        groupTwoWords.setText(words);
    }

    public void setGroupThreeAnswers(Color color, String category, String words) {
        groupThreePanel.setBackground(color);
        groupThreeCategory.setText(category);
        groupThreeWords.setText(words);
    }

    public void setGroupFourAnswers(Color color, String category, String words) {
        groupFourPanel.setBackground(color);
        groupFourCategory.setText(category);
        groupFourWords.setText(words);
    }

    public void setWinLossMsg(String message) {
        result.setText(message);
    }

    public void setFinalScore(int playerScore) {
        String stringScore = String.valueOf(playerScore);
        score.setText(stringScore);
    }
}
