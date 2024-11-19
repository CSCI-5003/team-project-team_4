package oosd.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import oosd.controller.Controller;
import oosd.model.Game;
import oosd.model.GameDifficulty;
import oosd.model.WordDifficulty;

public class GameBoardGUI extends JFrame {

    private Controller controller;

    public ArrayList<JButton> selectedButtons = new ArrayList<>(); // To store selected buttons
    private int MAX_SELECTION = 4;
    private JButton returnButton;
    private JButton submit;
    Game game;
    WordGrid wordGrid;
    JLabel messageLabel;
    JLabel[] mistakeArray;

    public void setController(Controller controller) { // Setter for the Controller
        this.controller = controller;
        System.out.println("Controller has been set: " + controller);

    }

    public GameBoardGUI(GameDifficulty gameDifficulty) {
        
        game = new Game(gameDifficulty);
        
        // Create mainFrame
        this.setTitle("Connections");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 800));
        this.setResizable(false);

        // Create mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 800));
        mainPanel.setBackground(ColorCodes.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 20));
        
        // Create Title & Heading
        JPanel headingPanel = new JPanel();
        headingPanel.setPreferredSize(new Dimension(700, 130));
        headingPanel.setBackground(ColorCodes.white);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Connections");
        title.setBorder(BorderFactory.createEmptyBorder(25, 0,0,0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Verdana", Font.BOLD, 35));

        JLabel instructions = new JLabel("Create Groups of Four!");
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setFont(new Font("Verdana", Font.BOLD, 20));
        instructions.setForeground(ColorCodes.purple);

        JLabel messageLabel = new JLabel();
        this.messageLabel = messageLabel;
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(15,0,5,0));
        messageLabel.setForeground(ColorCodes.darkGray);
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
            buttons[i].setBackground(ColorCodes.lightGray);

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
        gridPanel.setBackground(ColorCodes.white);  
        
        for (int i = 0; i < 16; i++) {
            gridPanel.add(buttons[i]);
        }

        this.wordGrid = gridPanel;

        // Create Result Bars
        // Result One
        JPanel result1 = new JPanel();
        result1.setBackground(ColorCodes.yellow);
        result1.setBounds(55,25,550,95);
        result1.setLayout(new BoxLayout(result1, BoxLayout.Y_AXIS));
        result1.setVisible(false);

        JLabel category1 = new JLabel();
        category1.setAlignmentX(Component.CENTER_ALIGNMENT);
        category1.setText("FOOD-RELATED JUMBLES");
        category1.setFont(new Font("Veranda", Font.BOLD, 20));
        
        JLabel words1 = new JLabel();
        words1.setAlignmentX(Component.CENTER_ALIGNMENT);
        words1.setText("HASH, SALAD, SCRAMBLE, STEW");
        words1.setFont(new Font("Veranda", Font.PLAIN, 15));

        result1.add(Box.createRigidArea(new Dimension(0, 20)));
        result1.add(category1);
        result1.add(Box.createRigidArea(new Dimension(0, 10)));
        result1.add(words1);

        // Result Two
        JPanel result2 = new JPanel();
        result2.setBackground(ColorCodes.green);
        result2.setBounds(55,130,550,95);
        result2.setLayout(new BoxLayout(result2, BoxLayout.Y_AXIS));
        result2.setVisible(false);

        JLabel category2 = new JLabel();
        category2.setAlignmentX(Component.CENTER_ALIGNMENT);
        category2.setText("PUBLIC STANDING");
        category2.setFont(new Font("Veranda", Font.BOLD, 20));
        
        JLabel words2 = new JLabel();
        words2.setAlignmentX(Component.CENTER_ALIGNMENT);
        words2.setText("CHARACTER, IMAGE, NAME, REPUTATION");
        words2.setFont(new Font("Veranda", Font.PLAIN, 15));

        result2.add(Box.createRigidArea(new Dimension(0, 20)));
        result2.add(category2);
        result2.add(Box.createRigidArea(new Dimension(0, 10)));
        result2.add(words2);

        // Result Three
        JPanel result3 = new JPanel();
        result3.setBackground(ColorCodes.blue);
        result3.setBounds(55,235,550,95);
        result3.setLayout(new BoxLayout(result3, BoxLayout.Y_AXIS));
        result3.setVisible(false);

        JLabel category3 = new JLabel();
        category3.setAlignmentX(Component.CENTER_ALIGNMENT);
        category3.setText("INFO ON A MUSEUM PLACARD");
        category3.setFont(new Font("Veranda", Font.BOLD, 20));
        
        JLabel words3 = new JLabel();
        words3.setAlignmentX(Component.CENTER_ALIGNMENT);
        words3.setText("ARTIST, MEDIUM, TITLE, YEAR");
        words3.setFont(new Font("Veranda", Font.PLAIN, 15));

        result3.add(Box.createRigidArea(new Dimension(0, 20)));
        result3.add(category3);
        result3.add(Box.createRigidArea(new Dimension(0, 10)));
        result3.add(words3);

        // Result 4
        JPanel result4 = new JPanel();
        result4.setBackground(ColorCodes.purple);
        result4.setBounds(55,340,550,95);
        result4.setLayout(new BoxLayout(result4, BoxLayout.Y_AXIS));
        result4.setVisible(false);

        JLabel category4 = new JLabel();
        category4.setAlignmentX(Component.CENTER_ALIGNMENT);
        category4.setText("ANAGRAMS OF FAMOUS PAINTERS");
        category4.setFont(new Font("Veranda", Font.BOLD, 20));
        
        JLabel words4 = new JLabel();
        words4.setAlignmentX(Component.CENTER_ALIGNMENT);
        words4.setText("DIAL, EGADS, MONTE, YOGA");
        words4.setFont(new Font("Veranda", Font.PLAIN, 15));

        result4.add(Box.createRigidArea(new Dimension(0, 20)));
        result4.add(category4);
        result4.add(Box.createRigidArea(new Dimension(0, 10)));
        result4.add(words4);

    
        // Create Mistake Tracker
        JPanel mistakePanel = new JPanel();
        mistakePanel.setLayout(new BoxLayout(mistakePanel, BoxLayout.X_AXIS));
        mistakePanel.setPreferredSize(new Dimension(700, 40));
        mistakePanel.setAlignmentY(CENTER_ALIGNMENT);
        mistakePanel.setAlignmentX(CENTER_ALIGNMENT);
        mistakePanel.setBackground(ColorCodes.white);
        
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

        life1.setForeground(ColorCodes.darkGray);
        life2.setForeground(ColorCodes.darkGray);
        life3.setForeground(ColorCodes.darkGray);
        life4.setForeground(ColorCodes.darkGray);

        // Create Submit & Return Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(700, 120));
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.setBackground(ColorCodes.white);
        
        submit = new JButton("Submit Guess");
        submit.setFont(new Font("Verdana", Font.PLAIN, 15));
        submit.setPreferredSize(new Dimension(20,50));
        submit.setForeground(ColorCodes.white);
        submit.setBackground(ColorCodes.darkGray);
        submit.setOpaque(true);
        submit.setBorderPainted(false);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        
        returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        returnButton.setBackground(ColorCodes.purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 5));
        returnButton.setForeground(ColorCodes.white);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Fill Panels
        this.add(mainPanel);
        mainPanel.add(headingPanel, JLayeredPane.DEFAULT_LAYER);
        mainPanel.add(gridPanel, JLayeredPane.DEFAULT_LAYER);
        mainPanel.add(mistakePanel, JLayeredPane.DEFAULT_LAYER);
        mainPanel.add(buttonPanel, JLayeredPane.DEFAULT_LAYER);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        headingPanel.add(title);
        headingPanel.add(instructions);
        headingPanel.add(messageLabel);

        gridPanel.add(result1, JLayeredPane.MODAL_LAYER);
        gridPanel.add(result2, JLayeredPane.MODAL_LAYER);
        gridPanel.add(result3, JLayeredPane.MODAL_LAYER);
        gridPanel.add(result4, JLayeredPane.MODAL_LAYER);

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

    public void handleButtonClick(Word button) {
        if (selectedButtons.contains(button)) {
            // Unselect the word
            button.setBackground(ColorCodes.lightGray);
            button.setForeground(ColorCodes.black);
            selectedButtons.remove(button);
        } else {
            if (selectedButtons.size() < MAX_SELECTION) {
                // Select the word
                button.setBackground(ColorCodes.darkGray);
                button.setForeground(ColorCodes.white);
                selectedButtons.add(button);
            } else {
                messageLabel.setText("You can only select up to 4 words.");
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
        System.out.println(lives);
        
        if (lives == 0) { //for deliverable 3, add word group reveal
            messageLabel.setText("You lose.");
            for (int i = 0; i < wordGroups.length; i++) {
                disableWordGroup(wordGroups[i]);
            }
        } else {
            messageLabel.setText("Incorrect, try again.");
            wordGrid.decrementLives();
        }

        mistakeArray[lives].setVisible(false);
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
                        System.out.println("Increased bestMatchCount to " + bestMatchCount + " at row " + j + " column " + i);
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
                checkLoss(wordGrid.getLives() - 1);
                break;
            case 3:
                // wordGrid.decrementLives();
                if (wordGrid.getLives() != 0) {
                    messageLabel.setText("One word off.");
                    wordGrid.decrementLives();
                    mistakeArray[wordGrid.getLives()].setVisible(false);

                } else {
                    checkLoss(wordGrid.getLives() - 1);
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

    public JButton getSubmitBut() {
        return submit;
    }

    public JButton getReturnBut() {
        return returnButton;
    }

    public void handleSubmit() {
        // Check if no words are selected
        if (selectedButtons.isEmpty()) {
            messageLabel.setText("Please select words before submitting.");
            System.out.println("Submit clicked without selecting any words.");
            return;
        }
    
        // Check if fewer or more than 4 words are selected
        if (selectedButtons.size() != 4) {
            messageLabel.setText("You must select exactly 4 words.");
            System.out.println("Submit clicked with " + selectedButtons.size() + " words selected.");
            return;
        }
    
        try {
            // Create a new WordGroup for the guess
            Word[] words = new Word[selectedButtons.size()];
            for (int i = 0; i < selectedButtons.size(); i++) {
                JButton button = selectedButtons.get(i);
                words[i] = new Word(button.getText());
            }
    
            WordGroup guess = new WordGroup(words, null);
            System.out.println("Submitting guess: " + guess);
    
            // Clear selection and reset button colors
            for (JButton button : selectedButtons) {
                button.setBackground(ColorCodes.lightGray);
                button.setForeground(ColorCodes.black);
            }
            selectedButtons.clear();
    
            // Check for duplicate guesses
            if (wordGrid.getAlreadyGuessed().contains(guess)) {
                messageLabel.setText("You've already made this guess!");
                System.out.println("Duplicate guess detected: " + guess);
                return; // Stop further processing if it's a duplicate
            }
    
            // Add the guess to already guessed groups
            wordGrid.addGuess(guess);
            System.out.println("Already guessed groups: " + wordGrid.getAlreadyGuessed());
    
            // Check the guess for correctness
            checkGuess(guess);
    
        } catch (Exception e) {
            messageLabel.setText("Error handling your guess. Please try again.");
            e.printStackTrace();
        }
    }
}    