package oosd.view;
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
        mainFrame.setPreferredSize(new Dimension(700, 850));

        // Create mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 850));
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(purple, 20));

        // Create Title & Heading
        JPanel headingPanel = new JPanel();
        //headingPanel.setBounds(0,0,700,200);
        headingPanel.setPreferredSize(new Dimension(700, 100));
        headingPanel.setBackground(Color.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(20, 0,0,0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        JLabel instructions = new JLabel("Create Groups of Four!");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Verdana", Font.BOLD, 20));
        instructions.setForeground(purple);

        // Create Word Grid
        // try replacing this with call to wordgrid class
        JPanel gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(700, 185));
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
        mistakePanel.setLayout(null);
        mistakePanel.setBackground(Color.WHITE);

        JLabel mistakes = new JLabel("Mistakes Remaining: ");
        mistakes.setBounds(130,0,260,75);

        JLabel life1 = new JLabel("⬤");
        life1.setForeground(darkGray);
        life1.setBounds(275,0,20,75);
        JLabel life2 = new JLabel("⬤");
        life2.setForeground(darkGray);
        life2.setBounds(300,0,20,75);
        JLabel life3 = new JLabel("⬤");
        life3.setForeground(darkGray);
        life3.setBounds(325,0,20,75);
        JLabel life4 = new JLabel("⬤");
        life4.setForeground(darkGray);
        life4.setBounds(350,0,20,75);

        // Create Gameplay Buttons
        JButton shuffle = new JButton("Shuffle");
        shuffle.setBounds(100,75,120,50);
        shuffle.setFont(new Font("Verdana", Font.PLAIN, 12));
        shuffle.setBackground(darkGray);
        shuffle.setForeground(Color.WHITE);
        shuffle.setOpaque(true);
        shuffle.setBorderPainted(false);

        JButton deselect = new JButton("Deselect All");
        deselect.setBounds(240,75,160,50);
        deselect.setFont(new Font("Verdana", Font.PLAIN, 12));
        deselect.setForeground(Color.WHITE);
        deselect.setBackground(darkGray);
        deselect.setOpaque(true);
        deselect.setBorderPainted(false);
        
        JButton submit = new JButton("Submit");
        submit.setBounds(425,75,120,50);
        submit.setFont(new Font("Verdana", Font.PLAIN, 12));
        submit.setForeground(Color.WHITE);
        submit.setBackground(darkGray);
        submit.setOpaque(true);
        submit.setBorderPainted(false);

        JButton menu = new JButton("Return to Menu");
        menu.setBounds(240,145,160,25);
        menu.setFont(new Font("Verdana", Font.PLAIN, 12));
        menu.setForeground(Color.WHITE);
        menu.setBackground(darkGray);
        menu.setOpaque(true);
        menu.setBorderPainted(false);

        // Fill Panels
        mainFrame.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(gridPanel);
        mainPanel.add(mistakePanel);

        headingPanel.add(title);
        headingPanel.add(instructions);
        mistakePanel.add(mistakes);
        mistakePanel.add(life1);
        mistakePanel.add(life2);
        mistakePanel.add(life3);
        mistakePanel.add(life4);

        mistakePanel.add(shuffle);
        mistakePanel.add(deselect);
        mistakePanel.add(submit);
        mistakePanel.add(menu);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
