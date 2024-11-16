package oosd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
//import oosd.model.WordGroup;

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
    WordGrid wordGrid;
    JLabel messageLabel;
    JLabel[] mistakeArray;
      
    public GameBoardGUI(ActionListener backActionListener, GameDifficulty gameDifficulty) {
        game = new Game(gameDifficulty);
        // Create mainFrame
        this.setTitle("Connections");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 800));
        this.setResizable(false);

        // Create mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 800));
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(purple, 20));
        
        // Create Title & Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setPreferredSize(new Dimension(700, 130));
        headingPanel.setBackground(Color.WHITE);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(25, 0,0,0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        JLabel instructions = new JLabel("Create Groups of Four!");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Verdana", Font.BOLD, 20));
        instructions.setForeground(purple);

        JLabel messageLabel = new JLabel();
        this.messageLabel = messageLabel;
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(15,0,5,0));
        messageLabel.setForeground(darkGray);
        messageLabel.setText("Message to User");

        // Create Word Grid
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
        gridPanel.setPreferredSize(new Dimension(700, 415));
        gridPanel.setLayout(null);
        gridPanel.setBackground(Color.WHITE);  
        

        for (int i = 0; i < 16; i++) {
            gridPanel.add(buttons[i]);
        }

        this.wordGrid = gridPanel;

        // Create Mistake Tracker
        JPanel mistakePanel = new JPanel();
        mistakePanel.setLayout(new BoxLayout(mistakePanel, BoxLayout.X_AXIS));
        mistakePanel.setPreferredSize(new Dimension(700, 40));
        mistakePanel.setAlignmentY(CENTER_ALIGNMENT);
        mistakePanel.setAlignmentX(CENTER_ALIGNMENT);
        mistakePanel.setBackground(Color.WHITE);
        
        JLabel mistakes = new JLabel("Mistakes Remaining: ");
        mistakes.setFont(new Font("Verdana", Font.PLAIN, 15));

        JLabel[] mistakeArray = new JLabel[4];
        this.mistakeArray = mistakeArray;
  
        JLabel life1 = new JLabel(" ⏺");
        JLabel life2 = new JLabel(" ⏺");
        JLabel life3 = new JLabel(" ⏺");
        JLabel life4 = new JLabel(" ⏺");

        mistakeArray[0] = life1;
        mistakeArray[1] = life2;
        mistakeArray[2] = life3;
        mistakeArray[3] = life4;

        life1.setFont(new Font("Verdana", Font.PLAIN, 42));
        life2.setFont(new Font("Verdana", Font.PLAIN, 42));
        life3.setFont(new Font("Verdana", Font.PLAIN, 42));
        life4.setFont(new Font("Verdana", Font.PLAIN, 42));

        life1.setForeground(darkGray);
        life2.setForeground(darkGray);
        life3.setForeground(darkGray);
        life4.setForeground(darkGray);

        // Create Submit Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(700, 120));
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.setBackground(Color.WHITE);
        
        JButton submit = new JButton("Submit Guess");
        submit.setFont(new Font("Verdana", Font.PLAIN, 15));
        submit.setPreferredSize(new Dimension(20,50));
        submit.setForeground(Color.WHITE);
        submit.setBackground(darkGray);
        submit.setOpaque(true);
        submit.setBorderPainted(false);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(purple, 5));
        returnButton.setForeground(Color.white);
        returnButton.addActionListener(backActionListener);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Fill Panels
        this.add(mainPanel);
        mainPanel.add(headingPanel);
        mainPanel.add(gridPanel);
        mainPanel.add(mistakePanel);
        mainPanel.add(buttonPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        headingPanel.add(title);
        headingPanel.add(instructions);
        headingPanel.add(messageLabel);

        mistakePanel.add(mistakes);
        mistakePanel.add(life1);
        mistakePanel.add(life2);
        mistakePanel.add(life3);
        mistakePanel.add(life4);

        buttonPanel.add(submit);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(returnButton);

        this.pack();
        this.setVisible(true);

        /*//for testing, delete later
        checkGuess();
        checkGuess();
        checkGuess();
        checkGuess();*/
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

    private void disableWordGroup(WordGroup wordGroup) {
        Word[] wordList = wordGroup.getWordList();

        for (int i = 0; i < wordList.length; i++) {
            wordList[i].setEnabled(false);
        }
    }

    private void checkLoss(int lives) {
        WordGroup[] wordGroups = wordGrid.getWordGroups();
        mistakeArray[lives].setVisible(false);

        if (lives == 0) { //for deliverable 3, add word group reveal
            messageLabel.setText("You lose.");
            for (int i = 0; i < wordGroups.length; i++) {
                disableWordGroup(wordGroups[i]);
            }
        } else {
            messageLabel.setText("Incorrect, try again.");
        }
    }

    public void checkGuess(WordGroup inputWordGroup) {
        WordGroup[] wordGroups = wordGrid.getWordGroups();
        Word[] inputWordList = inputWordGroup.getWordList();

        //Word[] inputWordList = new Word[4]; //for testing only
        //for (int i = 0; i < 4; i++) { //this for loop is for testing only
            //Word word = new Word(wordGroups[i].getWordList()[i].getText()); //Test case 1/4 correct
            /*Word word = new Word(wordGroups[0].getWordList()[i].getText()); //Test case 3/4 correct
            if (i == 3) {
                word.updateText(wordGroups[1].getWordList()[i].getText());
            }*/
            /*Word word = new Word(wordGroups[0].getWordList()[i].getText()); //Test case 4/4 correct. 
            Seemingly doesn't disable all buttons, 
            but in practice the same guess won't ble able to be made multiple times*/
            //inputWordList[i] = word;
        //}
        //WordGroup inputWordGroup = new WordGroup(inputWordList, WordDifficulty.BLUE); //for testing only

        int matchCount = 0;
        int bestMatchCount = 0;
        
        for (int i = 0; i < wordGroups.length; i++) {
            Word[] gridWordList = wordGroups[i].getWordList();
            matchCount = 0;

            for (int j = 0; j < gridWordList.length; j++) {
                Word gridWord = gridWordList[j];
                //System.out.println(gridWord.getText());
                
                for (int k = 0; k < inputWordList.length; k++) {
                    if (inputWordList[k].getText().equals(gridWord.getText())) {
                        System.out.println("Determined that " + inputWordList[k].getText() + " is equal to " + gridWord.getText());
                        matchCount++;
                        
                        if (matchCount > bestMatchCount) {
                            bestMatchCount = matchCount;
                        }
                        System.out.println("Increased bestMatchCount to " + bestMatchCount + " at row " + j + " colunm " + i);
                        if (matchCount == 4) {
                            inputWordGroup = wordGroups[i];
                        }
                    } else {
                        //System.out.println(inputWordList[k].getText() + " is not equal to " + gridWord.getText());
                    }
                }
            }
        }
        System.out.println(bestMatchCount);

        switch(bestMatchCount) {
            case 0:
            case 1:
            case 2:
                wordGrid.decrementLives();
                checkLoss(wordGrid.getLives());
                break;
            case 3:
                wordGrid.decrementLives();
                if (wordGrid.getLives() != 0) {
                    messageLabel.setText("One word off.");
                } else {
                    checkLoss(wordGrid.getLives());
                }
                break;
            case 4:
                wordGrid.decrementGroupsRemaining();
                disableWordGroup(inputWordGroup);
                if (wordGrid.getGroupsRemaining() == 0) {
                    messageLabel.setText("You win!");
                } else {
                    messageLabel.setText("Correct!");
                }
                break;
        }
    }
}

