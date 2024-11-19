package oosd.view;

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

public class EndGame extends JFrame{
    
    private JButton returnButton;

    public EndGame() {

        // Create menuFrame
        JFrame frame = new JFrame();
        frame.setTitle("Connections");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 800));
        frame.setResizable(false);

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

        JLabel result = new JLabel();
        result.setText("Congratulations!");
        result.setText("Better Luck Next Time!");
        result.setAlignmentX(CENTER_ALIGNMENT);
        result.setFont(new Font("Veranda", Font.BOLD, 35));
        result.setBorder(BorderFactory.createEmptyBorder(25,5,5,5));

        JLabel yourScore = new JLabel();
        yourScore.setText("Your Score:");
        yourScore.setAlignmentX(CENTER_ALIGNMENT);
        yourScore.setFont(new Font("Veranda", Font.BOLD, 20));
        yourScore.setBorder(BorderFactory.createEmptyBorder(15,5,0,5));

        JLabel score = new JLabel();
        score.setText("46");
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

        // Yellow
        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(ColorCodes.yellow);
        yellowPanel.setBounds(175,0,300,75);
        yellowPanel.setLayout(new BoxLayout(yellowPanel, BoxLayout.Y_AXIS));
        
        JLabel yellowCategory = new JLabel();
        yellowCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        yellowCategory.setText("FOOD-RELATED JUMBLES");
        yellowCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        JLabel yellowWords = new JLabel();
        yellowWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        yellowWords.setText("HASH, SALAD, SCRAMBLE, STEW");
        yellowWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        yellowPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        yellowPanel.add(yellowCategory);
        yellowPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        yellowPanel.add(yellowWords);


        // Green
        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(ColorCodes.green);
        greenPanel.setBounds(175,90,300,75);
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));

        JLabel greenCategory = new JLabel();
        greenCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        greenCategory.setText("PUBLIC STANDING");
        greenCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        JLabel greenWords = new JLabel();
        greenWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        greenWords.setText("CHARACTER, IMAGE, NAME, REPUTATION");
        greenWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        greenPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        greenPanel.add(greenCategory);
        greenPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        greenPanel.add(greenWords);

        // Blue
        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(ColorCodes.blue);
        bluePanel.setBounds(175,180,300,75);
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.Y_AXIS));

        JLabel blueCategory = new JLabel();
        blueCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        blueCategory.setText("INFO ON A MUSEUM PLACARD");
        blueCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        JLabel blueWords = new JLabel();
        blueWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        blueWords.setText("ARTIST, MEDIUM, TITLE, YEAR");
        blueWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        bluePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        bluePanel.add(blueCategory);
        bluePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bluePanel.add(blueWords);

        // Purple
        JPanel purplePanel = new JPanel();
        purplePanel.setBackground(ColorCodes.purple);
        purplePanel.setBounds(175,270,300,75);
        purplePanel.setLayout(new BoxLayout(purplePanel, BoxLayout.Y_AXIS));

        JLabel purpleCategory = new JLabel();
        purpleCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
        purpleCategory.setText("ANAGRAMS OF FAMOUS PAINTERS");
        purpleCategory.setFont(new Font("Veranda", Font.BOLD, 15));
        
        JLabel purpleWords = new JLabel();
        purpleWords.setAlignmentX(Component.CENTER_ALIGNMENT);
        purpleWords.setText("DIAL, EGADS, MONTE, YOGA");
        purpleWords.setFont(new Font("Veranda", Font.PLAIN, 12));

        purplePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        purplePanel.add(purpleCategory);
        purplePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        purplePanel.add(purpleWords);

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

        answerPanel.add(yellowPanel);
        answerPanel.add(greenPanel);
        answerPanel.add(bluePanel);
        answerPanel.add(purplePanel);
        answerPanel.add(returnButton);

        // Fill Panels
        frame.add(mainPanel);
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

        frame.pack();
        frame.setVisible(true);
    }

    public JButton getReturnBut() {
        return returnButton;
    }
}
