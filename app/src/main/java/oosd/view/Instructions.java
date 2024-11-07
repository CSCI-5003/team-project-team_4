package oosd;
import java.awt.*;
import javax.swing.*;

public class Instructions {
    public static void main(String[] args) {
        Color purple = new Color(187, 129, 197);

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
        backButton.setText("Return to Menu");

        instructionPanel.add(instructionIcon);
        instructionPanel.add(backButton);
        instructionFrame.add(instructionPanel);

        instructionFrame.pack();
        instructionFrame.setVisible(true);
    } 
}
