package oosd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import oosd.model.GameDifficulty;
import oosd.model.ScoreManager;
import oosd.view.ColorCodes;
import oosd.view.DifficultyGUI;
import oosd.view.EndGame;
import oosd.view.GameBoardGUI;
import oosd.view.HighScores;
import oosd.view.Instructions;
import oosd.view.MenuGUI;
import oosd.view.WordButton;
import oosd.view.WordGrid;
import oosd.view.WordGroup;

public class Controller implements ActionListener {

    private GameBoardGUI gameBoard;
    private MenuGUI menu;
    private HighScores highScores;
    private EndGame endGame;
    private DifficultyGUI difficulty;
    private Instructions instructions;
    private ScoreManager scoreManager;


    private List<JButton> selectedButtons = new ArrayList<>();
    private JLabel[] mistakeArray;
    private JLabel messageLabel; // Needs to be passed in or initialized
    private WordGrid wordGrid;   // Assuming this is part of your model
    private static final int MAX_SELECTION = 4;

    public Controller(MenuGUI menu) {
        
        this.menu = new MenuGUI();
        this.gameBoard = new GameBoardGUI(GameDifficulty.MEDIUM, this); 
        this.highScores = new HighScores();
        this.scoreManager = new ScoreManager();
        //this.endGame = new EndGame();
        this.difficulty = new DifficultyGUI();
        this.instructions = new Instructions();

        this.gameBoard.setVisible(false);
        this.highScores.setVisible(false);
        //this.endGame.setVisible(false);
        this.difficulty.setVisible(false);
        this.instructions.setVisible(false);
        
        this.menu.getPlayNowBut().addActionListener(this);
        this.menu.gethighScoresBut().addActionListener(this);
        this.menu.getHowToBut().addActionListener(this);

        this.gameBoard.getSubmitBut().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("Button clicked: " + e.getActionCommand());  
        
        switch (e.getActionCommand()) {
            case "Play Now":
                handlePlayNowBut();
                break;
            case "High Scores":
                handleHighScores();
                break;
            case "How To Play":
                handleHowToBut();
                break;
            case "Easy":
                handleEasy();
                break;
            case "Medium":
                handleMedium();
                break;
            case "Hard":
                handleHard();
                break;
            case "Return to Menu":
                handleReturnButton();
                break;
            case "Submit":
                handleSubmit();
                break;
            default:
                break;
        }
    }

    // return buttons
    private void handleReturnButton() {
        this.menu.setVisible(true);
        this.difficulty.setVisible(false);
        this.gameBoard.setVisible(false);
        //this.endGame.setVisible(false);
        this.instructions.setVisible(false);
        this.highScores.setVisible(false);   
    }

    // menu buttons
    private void handlePlayNowBut() {
        menu.setVisible(false);
        this.difficulty.setVisible(true);

        this.difficulty.getEasyBut().addActionListener(this);
        this.difficulty.getMediumBut().addActionListener(this);
        this.difficulty.getHardBut().addActionListener(this);
        this.difficulty.getReturnBut().addActionListener(this);
    }

    private void handleHowToBut() {
        menu.setVisible(false);
        this.instructions.setVisible(true);
        this.instructions.getReturnBut().addActionListener(this);
    }

    private void handleHighScores() {
        menu.setVisible(false);
        highScores.setVisible(true);
    
        List<Integer> scores = scoreManager.getHighScores(); // Fetch scores from ScoreManager
        highScores.updateScores(scores); // Update the HighScores UI with the scores
    
        highScores.getReturnBut().addActionListener(this); // Ensure return button is functional
    }
    

    // difficulty buttons
    private void handleEasy() {
        difficulty.setVisible(false);
        GameBoardGUI gameBoard = new GameBoardGUI(GameDifficulty.EASY, this);
        this.gameBoard = gameBoard;
        this.gameBoard.setVisible(true);

        this.gameBoard.getSubmitBut().addActionListener(this);
        this.gameBoard.getReturnBut().addActionListener(this);
    }

    private void handleMedium() {
        difficulty.setVisible(false);
        GameBoardGUI gameBoard = new GameBoardGUI(GameDifficulty.MEDIUM, this);
        this.gameBoard = gameBoard;
        this.gameBoard.setVisible(true);

        this.gameBoard.getSubmitBut().addActionListener(this);
        this.gameBoard.getReturnBut().addActionListener(this);
    }

    private void handleHard() {
        difficulty.setVisible(false);
        this.gameBoard.setVisible(true);
        GameBoardGUI gameBoard = new GameBoardGUI(GameDifficulty.HARD, this);
        this.gameBoard = gameBoard;
        this.gameBoard.setVisible(true);

        this.gameBoard.getSubmitBut().addActionListener(this);
        this.gameBoard.getReturnBut().addActionListener(this);
    }

    // Game Board Buttons
    public void handleButtonClick(WordButton button) {
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
            WordButton[] words = new WordButton[selectedButtons.size()];
            for (int i = 0; i < selectedButtons.size(); i++) {
                JButton button = selectedButtons.get(i);
                words[i] = new WordButton(button.getText());
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

    public void checkGuess(WordGroup inputWordGroup) {
        int points = 0;
        WordGroup[] wordGroups = wordGrid.getWordGroups();
        WordButton[] inputWordList = inputWordGroup.getWordList();

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
            WordButton[] gridWordList = wordGroups[i].getWordList();
            matchCount = 0;

            for (int j = 0; j < gridWordList.length; j++) {
                WordButton gridWord = gridWordList[j];
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
                            points = calculatePoints(wordGroups[i]); // Calculate points for this group
                            scoreManager.addPoints(points);
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
                } else {
                    checkLoss(wordGrid.getLives() - 1);
                }
                break;
            case 4:
                wordGrid.decrementGroupsRemaining();
                disableWordGroup(inputWordGroup);
                if (wordGrid.getGroupsRemaining() == 0) {
                    messageLabel.setText("You win! Final Score: " + scoreManager.getCurrentScore());
                    scoreManager.saveScore(); // Save the score to file
                } else {
                    messageLabel.setText("Correct! Points: " + points);

                }
                break;
        }
    }
        

    private void checkLoss(int lives) {
        WordGroup[] wordGroups = wordGrid.getWordGroups();
        System.out.println("Remaining lives: " + lives);
        
        if (lives == 0) {
            messageLabel.setText("You lose! Final Score: " + scoreManager.getCurrentScore());
            for (int i = 0; i < wordGroups.length; i++) {
                disableWordGroup(wordGroups[i]);
            }
    
            // Save the final score when the player loses
            scoreManager.saveScore();
        } else {
            messageLabel.setText("Incorrect, try again.");
            wordGrid.decrementLives();
            mistakeArray[lives].setVisible(false);
        }
    }
    

    private void disableWordGroup(WordGroup wordGroup) {
        WordButton[] wordList = wordGroup.getWordList();

        for (int i = 0; i < wordList.length; i++) {
            wordList[i].setEnabled(false);
        }
    }

    private int calculatePoints(WordGroup wordGroup) {
        switch (wordGroup.getWordDifficulty()) {
            case YELLOW: return 5;
            case GREEN: return 10;
            case BLUE: return 15;
            case PURPLE: return 20;
            default: return 0;
        }
    }
    

    public void setMistakeArray(JLabel[] mistakeArray) {
        this.mistakeArray = mistakeArray;
    }

    public void setMessageLabel(JLabel messageLabel) {
        this.messageLabel = messageLabel;
    }

    public void setWordGrid(WordGrid wordGrid) {
        this.wordGrid = wordGrid;
    }


}
