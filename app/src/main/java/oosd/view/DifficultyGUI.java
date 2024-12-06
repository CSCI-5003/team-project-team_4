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

public class DifficultyGUI extends JFrame {
    
    private DifficultyGUI difficulty;
    private JButton returnButton;
    private JButton easy;
    private JButton medium;
    private JButton hard;
       
    public DifficultyGUI() {

        difficulty = this;

        // Create mainFrame
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

        JLabel choose = new JLabel("Choose a Dificulty Level");
        choose.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        choose.setAlignmentX(Component.CENTER_ALIGNMENT);
        choose.setAlignmentY(Component.CENTER_ALIGNMENT);
        choose.setFont(new Font("Verdana", Font.BOLD, 20));
        choose.setForeground(ColorCodes.purple);
        
        // Create Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,200,700,500);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(ColorCodes.white);
        
        easy = new JButton("Easy");
        easy.setFont(new Font("Verdana", Font.PLAIN, 30));
        easy.setBounds(175,50,300,100);
        easy.setBackground(ColorCodes.yellow);
        easy.setOpaque(true);
        easy.setBorderPainted(false);

        medium = new JButton("Medium");
        medium.setFont(new Font("Verdana", Font.PLAIN, 30));
        medium.setBounds(175,200,300,100);
        medium.setBackground(ColorCodes.green);
        medium.setOpaque(true);
        medium.setBorderPainted(false);

        hard = new JButton("Hard");
        hard.setFont(new Font("Verdana", Font.PLAIN, 30));
        hard.setBounds(175,350,300,100);
        hard.setBackground(ColorCodes.blue);
        hard.setOpaque(true);
        hard.setBorderPainted(false);

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
        returnButton.setActionCommand("RTM_Difficulty");

        // Fill Panels
        this.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(returnPanel);
        
        headingPanel.add(title);
        headingPanel.add(choose);
        buttonPanel.add(easy);
        buttonPanel.add(medium);
        buttonPanel.add(hard);
        returnPanel.add(returnButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        this.pack();
        this.setVisible(true);
    }
    
    public JButton getEasyBut() {
        return easy;
    }
    
    public JButton getMediumBut() {
        return medium;
    }

    public JButton getHardBut() {
        return hard;
    }

    public JButton getReturnBut() {
        return returnButton;
    }
}

