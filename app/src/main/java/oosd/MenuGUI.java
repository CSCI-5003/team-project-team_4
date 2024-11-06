package oosd;
import java.awt.*;
import javax.swing.*;

public class MenuGUI extends JPanel {
       
    public static void main(String[] args) {
        // color codes
        Color purple = new Color(187, 129, 197);
        Color blue = new Color(176, 196, 239);
        Color yellow = new Color(249, 223, 109);
        Color green = new Color(160, 195, 90);

        // Create mainFrame
        JFrame mainFrame = new JFrame("Connections");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(700, 800));

        // Create mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 800));
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(purple, 20));

        // Create Title & Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setBounds(0,0,700,200);
        headingPanel.setBackground(Color.white);
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
        buttonPanel.setBackground(Color.WHITE);
        
        JButton playNowBut = new JButton("Play Now");
        playNowBut.setFont(new Font("Verdana", Font.PLAIN, 30));
        playNowBut.setBounds(175,100,300,100);
        playNowBut.setBackground(yellow);
        playNowBut.setOpaque(true);
        playNowBut.setBorderPainted(false);

        JButton highScoresBut = new JButton("High Scores");
        highScoresBut.setFont(new Font("Verdana", Font.PLAIN, 30));
        highScoresBut.setBounds(175,250,300,100);
        highScoresBut.setBackground(green);
        highScoresBut.setOpaque(true);
        highScoresBut.setBorderPainted(false);

        JButton howToBut = new JButton("How To Play");
        howToBut.setFont(new Font("Verdana", Font.PLAIN, 30));
        howToBut.setBounds(175,400,300,100);
        howToBut.setBackground(blue);
        howToBut.setOpaque(true);
        howToBut.setBorderPainted(false);

        // Fill Panels
        mainFrame.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(buttonPanel);
        
        headingPanel.add(title);
        buttonPanel.add(playNowBut);
        buttonPanel.add(highScoresBut);
        buttonPanel.add(howToBut);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
