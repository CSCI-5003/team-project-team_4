package oosd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oosd.model.Game;
import oosd.model.GameDifficulty;
import oosd.model.WordDifficulty;
import oosd.model.WordGroup;

//import oosd.model.Game;

// - FR3: when buttons are clicked, they are highlighted (change color). Only four buttons can be highlighted. Add the selected buttons to an ArrayList. 
//When un-selected, the color goes back to lightGray and word is removed from ArrayList

public class GameBoardGUI extends JFrame {

    private Color purple = new Color(187, 129, 197);
    private Color lightGray = new Color(239, 239, 230);
    private Color darkGray = new Color(90, 89, 78);
    private ArrayList<JButton> selectedButtons = new ArrayList<>(); // To store selected buttons
    private int MAX_SELECTION = 4;
    Game game;
      
    public GameBoardGUI(ActionListener backActionListener, GameDifficulty gameDifficulty) {
        game = new Game(gameDifficulty);
        // Create mainFrame
        this.setTitle("Connections");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 800));

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


        
        int width = 130;
        int height = 95;
        int[] x = new int[]{55,195,335,475,55,195,335,475,55,195,335,475,55,195,335,475};
        int[] y = new int[]{25,25,25,25,130,130,130,130,235,235,235,235,340,340,340,340};

        Word[] buttons = new Word[16];

        for (int i = 0; i < 16; i++) {
            String word = "Word " + i;
            buttons[i] = new Word(String.valueOf(word));
            buttons[i].setEnabled(true);
            buttons[i].setBounds(x[i], y[i], width, height);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            buttons[i].setBackground(lightGray);

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick((Word) e.getSource());
                }
            });

            

        }

        WordGrid gridPanel = makeGrid(buttons);
        gridPanel.setPreferredSize(new Dimension(700, 450));
        gridPanel.setLayout(null);
        gridPanel.setBackground(Color.WHITE);
        
        

        for (int i = 0; i < 16; i++) {
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
        returnButton.addActionListener(backActionListener);

        // Fill Panels
        this.add(mainPanel);
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

        this.pack();
        this.setVisible(true);
    }

    private void handleButtonClick(Word button) {
        if (selectedButtons.contains(button)) {
            button.setBackground(lightGray);
            button.setForeground(Color.BLACK);
            selectedButtons.remove(button);
        } else {
            if (selectedButtons.size() < MAX_SELECTION) {
                button.setBackground(darkGray);
                button.setForeground(Color.WHITE);
                selectedButtons.add(button);
            }
        }
    }

    private String getIndividualWord( List<String[]> colorList, int i, int randomIntInRange) {
        String[] groupStringArray = colorList.get(randomIntInRange);
        String individualString = groupStringArray[i + 4];
        return individualString;
    }

    private void randomizeWords(Word[] words, WordDifficulty wordDifficulty) {
        Random random = new Random();
        HashMap<String, List<String[]>> dictionary = this.game.getWordDictionary();
        //System.out.println(words[0].getText());
        List<String[]> yellowList = dictionary.get("Yellow");
        List<String[]> greenList = dictionary.get("Green");
        List<String[]> blueList = dictionary.get("Blue");
        List<String[]> purpleList = dictionary.get("Purple");

        int yellowIntInRange = random.nextInt(yellowList.size());
        int greenIntInRange = random.nextInt(greenList.size());
        int blueIntInRange = random.nextInt(blueList.size());
        int purpleIntInRange = random.nextInt(purpleList.size());
        for (int i = 0; i < 4; i++) {
            //System.out.println("setting text in getWords");
            
            String individualString = "not initialized";
            switch (wordDifficulty) {
                case WordDifficulty.YELLOW:
                    individualString = getIndividualWord(yellowList, i, yellowIntInRange);
                    System.out.println("Yellow Group: " + individualString);
                    break;
                case WordDifficulty.GREEN:
                    individualString = getIndividualWord(greenList, i, greenIntInRange);
                    System.out.println("Green Group: " + individualString);
                    break;
                case WordDifficulty.BLUE:
                    individualString = getIndividualWord(blueList, i, blueIntInRange);
                    System.out.println("Blue Group: " + individualString);
                    break;
                case WordDifficulty.PURPLE:
                    individualString = getIndividualWord(purpleList, i, purpleIntInRange);
                    System.out.println("Purple Group: " + individualString);
                    break;
            }
            words[i].updateText(individualString);
            //System.out.println(words[i].getText());
        }
    }

    private WordGrid makeGrid(Word[] wordArray) {
        WordGroup[] wordGroups = new WordGroup[4];
        int wordCount = 0;
        for (int i = 0; i < 4; i++) {
            WordDifficulty wordDifficulty = WordDifficulty.YELLOW;
            switch (this.game.getGameDifficulty()) {
                case EASY:
                    switch (i) {
                        case 0:
                            wordDifficulty = WordDifficulty.YELLOW;
                            break;
                        case 1:
                        case 2:
                            wordDifficulty = WordDifficulty.GREEN;
                            break;
                        case 3:
                            wordDifficulty = WordDifficulty.BLUE;
                            break;
                    }
                    break;
                case MEDIUM:
                    switch (i) {
                        case 0:
                            wordDifficulty = WordDifficulty.YELLOW;
                            break;
                        case 1:
                            wordDifficulty = WordDifficulty.GREEN;
                            break;
                        case 2:
                            wordDifficulty = WordDifficulty.BLUE;
                            break;
                        case 3:
                            wordDifficulty = WordDifficulty.PURPLE;
                            break;
                    }
                    break;
                case HARD:
                    switch (i) {
                        case 0:
                            wordDifficulty = WordDifficulty.GREEN;
                            break;
                        case 1:
                        case 2:
                            wordDifficulty = WordDifficulty.BLUE;
                            break;
                        case 3:
                            wordDifficulty = WordDifficulty.PURPLE;
                            break;
                    }
                    break;
            }
            Word[] wordArraySubset = new Word[4];
            for (int j = 0; j < 4; j++) {
                wordArraySubset[j] = wordArray[wordCount];
                //System.out.println(wordArray[wordCount].getText());
                wordCount++;
            }
            randomizeWords(wordArraySubset, wordDifficulty);
            WordGroup wordGroup = new WordGroup(wordArraySubset, wordDifficulty);
            wordGroups[i] = wordGroup;
        }
        WordGrid wordGrid = new WordGrid(wordGroups);
        //System.out.println("returning the wordGrid that was made");
        return wordGrid;
    }

    private void drawDots(Graphics2D g2d, int x, int y) {
            g2d.fillOval(x, y, 30, 30);
    }
}

