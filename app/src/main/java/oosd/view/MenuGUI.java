package oosd.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuGUI extends JFrame {

    private JButton playNowBut;
    private JButton highScoresBut;
    private JButton howToBut;
       
    public MenuGUI() {

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
        headingPanel.setBounds(0,0,700,200);
        headingPanel.setBackground(ColorCodes.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(75, 20, 10, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        // Create Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,200,700,200);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(ColorCodes.white);
        
        playNowBut = new JButton("Play Now");
        playNowBut.setFont(new Font("Verdana", Font.PLAIN, 30));
        playNowBut.setBounds(175,100,300,100);
        playNowBut.setBackground(ColorCodes.yellow);
        playNowBut.setOpaque(true);
        playNowBut.setBorderPainted(false);

        highScoresBut = new JButton("High Scores");
        highScoresBut.setFont(new Font("Verdana", Font.PLAIN, 30));
        highScoresBut.setBounds(175,250,300,100);
        highScoresBut.setBackground(ColorCodes.green);
        highScoresBut.setOpaque(true);
        highScoresBut.setBorderPainted(false);

        howToBut = new JButton("How To Play");
        howToBut.setFont(new Font("Verdana", Font.PLAIN, 30));
        howToBut.setBounds(175,400,300,100);
        howToBut.setBackground(ColorCodes.blue);
        howToBut.setOpaque(true);
        howToBut.setBorderPainted(false);

        // Fill Panels
        this.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(buttonPanel);
        
        headingPanel.add(title);
        buttonPanel.add(playNowBut);
        buttonPanel.add(highScoresBut);
        buttonPanel.add(howToBut);

        this.pack();
        this.setVisible(true);
    }

    public JButton getPlayNowBut() {
        return playNowBut;
    }

    public JButton gethighScoresBut() {
        return highScoresBut;
    }

    public JButton getHowToBut() {
        return howToBut;
    }

}

