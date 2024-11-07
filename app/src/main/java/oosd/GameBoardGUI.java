package oosd;
import java.awt.*;
import javax.swing.*;

public class GameBoardGUI extends JFrame {
    public static void main(String[] args) {
        // color codes
        Color purple = new Color(187, 129, 197);
        Color lightGray = new Color(239, 239, 230);
        Color darkGray = new Color(90, 89, 78);

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
        headingPanel.setPreferredSize(new Dimension(700, 100));
        headingPanel.setBackground(Color.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(25, 0,0,0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        JLabel instructions = new JLabel("Create Groups of Four!");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Verdana", Font.BOLD, 20));
        instructions.setForeground(purple);

        // Create Word Grid
        // try replacing this with call to wordgrid class
        JPanel gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(700, 450));
        gridPanel.setLayout(null);
        gridPanel.setBackground(Color.WHITE);
        
        int width = 130;
        int height = 95;
        int[] x = new int[]{55,195,335,475,55,195,335,475,55,195,335,475,55,195,335,475};
        int[] y = new int[]{25,25,25,25,130,130,130,130,235,235,235,235,340,340,340,340};

        JButton[] buttons = new JButton[16];

        for (int i = 0; i < 16; i++) {
            String word = "Word " + i;
            buttons[i] = new JButton(String.valueOf(word));
            buttons[i].setEnabled(true);
            buttons[i].setBounds(x[i], y[i], width, height);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            buttons[i].setBackground(lightGray);

            gridPanel.add(buttons[i]);
        }

        // Create Mistake Tracker
        JPanel mistakePanel = new JPanel();
        mistakePanel.setLayout(new BoxLayout(mistakePanel, BoxLayout.X_AXIS));
        mistakePanel.setPreferredSize(new Dimension(700, 30));
        mistakePanel.setAlignmentY(CENTER_ALIGNMENT);
        mistakePanel.setAlignmentX(CENTER_ALIGNMENT);
        mistakePanel.setBackground(Color.WHITE);
        
        JLabel mistakes = new JLabel("Mistakes Remaining: ");
        mistakes.setFont(new Font("Verdana", Font.PLAIN, 15));

        JLabel life1 = new JLabel(" ⬤");
        JLabel life2 = new JLabel(" ⬤");
        JLabel life3 = new JLabel(" ⬤");
        JLabel life4 = new JLabel(" ⬤");

        life1.setFont(new Font("Verdana", Font.PLAIN, 20));
        life2.setFont(new Font("Verdana", Font.PLAIN, 20));
        life3.setFont(new Font("Verdana", Font.PLAIN, 20));
        life4.setFont(new Font("Verdana", Font.PLAIN, 20));

        life1.setForeground(darkGray);
        life2.setForeground(darkGray);
        life3.setForeground(darkGray);
        life4.setForeground(darkGray);

        // Create Gameplay Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(700, 150));
        buttonPanel.setAlignmentY(CENTER_ALIGNMENT);
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.setBackground(Color.WHITE);
        
        JButton submit = new JButton("Submit Guess");
        submit.setFont(new Font("Verdana", Font.PLAIN, 15));
        submit.setPreferredSize(new Dimension(20,50));
        submit.setForeground(Color.WHITE);
        submit.setBackground(darkGray);
        submit.setOpaque(true);
        submit.setBorderPainted(false);

        // Return Panel
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(null); 
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS)); 
        buttonPanel.setPreferredSize(new Dimension(700, 50));
        returnPanel.setBackground(Color.white);
        returnPanel.setBorder(BorderFactory.createEmptyBorder(10,0,45,0));
        
        JButton returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(purple, 5));
        returnButton.setForeground(Color.white);

        // Fill Panels
        mainFrame.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(gridPanel);
        mainPanel.add(mistakePanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(returnPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        headingPanel.add(title);
        headingPanel.add(instructions);
        mistakePanel.add(mistakes);
        mistakePanel.add(life1);
        mistakePanel.add(life2);
        mistakePanel.add(life3);
        mistakePanel.add(life4);

        buttonPanel.add(submit);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        returnPanel.add(returnButton);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
