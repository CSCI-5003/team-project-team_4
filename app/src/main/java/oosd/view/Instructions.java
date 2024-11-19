package oosd.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Instructions extends JFrame {

    private JButton returnButton;

    public Instructions() {

        // Frame setup
        this.setTitle("Connections");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 800));
        this.setResizable(false);

        // main panel
        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(Color.white);
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
        instructionPanel.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 20));

        // image panel
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(Color.WHITE);
        imgPanel.setPreferredSize(new Dimension(700, 600));
        imgPanel.setLayout(null);

        // image set up
        ImageIcon originalIcon = new ImageIcon("src/images/instructions.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(600, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel instructionIcon = new JLabel(resizedIcon);
        instructionIcon.setBounds(50, 50, 600, 500);

        // Return Panel
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS)); 
        returnPanel.setBackground(Color.white);
        returnPanel.setPreferredSize(new Dimension(700, 200));
        
        returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(ColorCodes.purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 5));
        returnButton.setForeground(Color.white);

        this.add(instructionPanel);
        instructionPanel.add(imgPanel);
        instructionPanel.add(returnPanel);

        imgPanel.add(instructionIcon);
        returnPanel.add(returnButton);
        
        this.pack();
        this.setVisible(true);
    } 

    public JButton getReturnBut() {
        return returnButton;
    }
}
