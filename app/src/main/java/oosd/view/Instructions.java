package oosd;
import java.awt.*;
import javax.swing.*;

public class Instructions {
    public static void main(String[] args) {
        Color purple = new Color(187, 129, 197);
        Color lightPurple = new Color(0);
        Color blue = new Color(176, 196, 239);
        Color yellow = new Color(249, 223, 109);
        Color green = new Color(0160, 195, 90);
        Color lightGray = new Color(239, 239, 230);
        Color darkGray = new Color(90, 89, 78);

        JFrame instructionFrame = new JFrame("Instructions");
        instructionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructionFrame.setPreferredSize(new Dimension(700, 800));

        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(Color.white);
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
        instructionPanel.setBorder(BorderFactory.createLineBorder(purple, 20));

        ImageIcon originalIcon = new ImageIcon("app/src/images/instructions.png");
        Image originalImage = originalIcon.getImage();

        Image resizedImage = originalImage.getScaledInstance(600, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel instructionIcon = new JLabel(resizedIcon);

        JButton backButton = new JButton();
        backButton.setText("Back");

        instructionPanel.add(instructionIcon);
        instructionPanel.add(backButton);
        instructionFrame.add(instructionPanel);

        instructionFrame.pack();
        instructionFrame.setVisible(true);
    } 
}
