package oosd;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public class ConnectionsGUI {
    public static void main(String[] args) {
        // color codes
        Color purple = new Color(187, 129, 197);
        Color lightPurple = new Color(0);
        Color blue = new Color(176, 196, 239);
        Color yellow = new Color(249, 223, 109);
        Color green = new Color(0160, 195, 90);
        Color lightGray = new Color(239, 239, 230);
        Color darkGray = new Color(90, 89, 78);

        // Create mainFrame
        JFrame mainFrame = new JFrame("Connections");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(120, 200));

        // Create mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(purple, 20));

        

        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    



}
