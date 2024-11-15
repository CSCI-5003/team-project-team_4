package oosd.view;

//import java.awt.*;
//import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGame extends JFrame{
    public EndGame() {
        // color codes
        Color lightPurple = new Color(178, 169, 249);
        Color purple = new Color(187, 129, 197);
        Color blue = new Color(176, 196, 239);
        Color yellow = new Color(249, 223, 109);
        Color green = new Color(160, 195, 90);

        // Create menuFrame
        this.setTitle("Connections");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 800));
        this.setResizable(false);

        // Create mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 800));
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(purple, 20));

        // Create Title & Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setPreferredSize(new Dimension(700, 100));
        headingPanel.setBackground(Color.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(25, 30, 10, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        // Score Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setPreferredSize(new Dimension(700, 225));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setLayout(null);
        
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(80,0,500,200);
        scorePanel.setBackground(lightPurple);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        JLabel result = new JLabel();
        result.setText("Congratulations!");
        result.setText("Better Luck Next Time!");
        result.setAlignmentX(CENTER_ALIGNMENT);
        result.setFont(new Font("Veranda", Font.BOLD, 35));
        result.setBorder(BorderFactory.createEmptyBorder(30,5,5,5));

        JLabel yourScore = new JLabel();
        yourScore.setText("Your Score:");
        yourScore.setAlignmentX(CENTER_ALIGNMENT);
        yourScore.setFont(new Font("Veranda", Font.BOLD, 20));
        yourScore.setBorder(BorderFactory.createEmptyBorder(30,5,0,5));

        JLabel score = new JLabel();
        score.setText("46");
        score.setAlignmentX(CENTER_ALIGNMENT);
        score.setFont(new Font("Veranda", Font.BOLD, 25));
        score.setBorder(BorderFactory.createEmptyBorder(10,5,5,5));

        // Answer Panel
        JPanel answerPanel = new JPanel();
        answerPanel.setPreferredSize(new Dimension(700, 400));
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
        answerPanel.setAlignmentX(CENTER_ALIGNMENT);
        answerPanel.setAlignmentY(CENTER_ALIGNMENT);
        answerPanel.setLayout(null);
        answerPanel.setBackground(Color.WHITE);

        // Yellow
        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(yellow);
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
        greenPanel.setBackground(green);
        greenPanel.setBounds(175,100,300,75);
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
        bluePanel.setBackground(blue);
        bluePanel.setBounds(175,200,300,75);
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
        purplePanel.setBackground(purple);
        purplePanel.setBounds(175,300,300,75);
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



        answerPanel.add(yellowPanel);
        answerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        answerPanel.add(greenPanel);
        answerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        answerPanel.add(bluePanel);
        answerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        answerPanel.add(purplePanel);


        // Fill Panels
        this.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(resultPanel);
        mainPanel.add(answerPanel);

        headingPanel.add(title);

        scorePanel.add(result);
        scorePanel.add(yourScore);
        scorePanel.add(score);
        
        resultPanel.add(scorePanel);

        this.pack();
        this.setVisible(true);
    }
}
