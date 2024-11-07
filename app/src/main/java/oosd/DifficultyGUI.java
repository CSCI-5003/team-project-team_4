package oosd;
import java.awt.*;
import javax.swing.*;

public class DifficultyGUI extends JPanel {
       
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

        JLabel choose = new JLabel("Choose a Dificulty Level");
        choose.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        choose.setAlignmentX(Component.CENTER_ALIGNMENT);
        choose.setAlignmentY(Component.CENTER_ALIGNMENT);
        choose.setFont(new Font("Verdana", Font.BOLD, 20));
        choose.setForeground(purple);
        
        // Create Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,200,700,500);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        
        JButton easy = new JButton("Easy");
        easy.setFont(new Font("Verdana", Font.PLAIN, 30));
        easy.setBounds(175,50,300,100);
        easy.setBackground(yellow);
        easy.setOpaque(true);
        easy.setBorderPainted(false);

        JButton medium = new JButton("Medium");
        medium.setFont(new Font("Verdana", Font.PLAIN, 30));
        medium.setBounds(175,200,300,100);
        medium.setBackground(green);
        medium.setOpaque(true);
        medium.setBorderPainted(false);

        JButton hard = new JButton("Hard");
        hard.setFont(new Font("Verdana", Font.PLAIN, 30));
        hard.setBounds(175,350,300,100);
        hard.setBackground(blue);
        hard.setOpaque(true);
        hard.setBorderPainted(false);

        // Return Panel
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(null); 
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS)); 
        returnPanel.setBackground(Color.white);
        
        JButton returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(purple, 5));
        returnButton.setForeground(Color.white);

        // Fill Panels
        mainFrame.add(mainPanel);
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

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}

