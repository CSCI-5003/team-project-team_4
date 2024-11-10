package oosd.view;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Instructions extends JFrame {

    public Instructions(ActionListener backActionListener) {
        Color purple = new Color(187, 129, 197);

        this.setTitle("Connections");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 800));
        this.setResizable(false);

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
        backButton.addActionListener(backActionListener);

        instructionPanel.add(instructionIcon);
        instructionPanel.add(backButton);
        this.add(instructionPanel);

        this.pack();
        this.setVisible(true);
    } 
}
