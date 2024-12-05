package oosd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import oosd.model.Game;
import oosd.model.GameDifficulty;
import oosd.model.ScoreManager;
import oosd.model.WordGroup;
import oosd.view.ColorCodes;
import oosd.view.DifficultyGUI;
import oosd.view.EndGame;
import oosd.view.GameBoardGUI;
import oosd.view.HighScores;
import oosd.view.Instructions;
import oosd.view.MenuGUI;
import oosd.view.WordButton;
import oosd.view.WordGrid;

public class Controller implements ActionListener {

    Game game;
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
    private WordGrid wordGrid;  // Assuming this is part of your model
    private static final int MAX_SELECTION = 4;

    public Controller(MenuGUI menu) {
        
        this.menu = new MenuGUI();
        menu = this.menu;
        menu.getPlayNowBut().addActionListener(this);
        menu.gethighScoresBut().addActionListener(this);
        menu.getHowToBut().addActionListener(this);

        scoreManager = new ScoreManager();

        difficulty = new DifficultyGUI();
        difficulty.setVisible(false);
        difficulty.getEasyBut().addActionListener(this);
        difficulty.getMediumBut().addActionListener(this);
        difficulty.getHardBut().addActionListener(this);
        difficulty.getReturnBut().addActionListener(this);

        endGame = new EndGame();
        endGame.setVisible(false);
        endGame.getReturnBut().addActionListener(this);

        instructions = new Instructions();
        instructions.setVisible(false);
        instructions.getReturnBut().addActionListener(this);
        
        highScores = new HighScores(scoreManager);
        highScores.setVisible(false);
        highScores.getReturnBut().addActionListener(this);
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
            case "Submit":
                handleSubmit();
                break;
            case "RTM_Difficulty":
                handleReturnButtonDifficulty();
                break;
            case "RTM_GameBoard":
                handleReturnButtonGameBoard();
                break;
            case "RTM_HighScores":
                handleReturnButtonHighScores();
                break;
            case "RTM_Instructions":
                handleReturnButtonInstructions();
                break;
            case "RTM_EndGame":
                handleReturnButtonEndGame();
                break;
            default:
                break;
        }
    }

    // Return Buttons
    private void handleReturnButtonDifficulty() {
        this.menu.setVisible(true);
        this.difficulty.setVisible(false);
    }

    private void handleReturnButtonGameBoard() {
        this.menu.setVisible(true);
        this.gameBoard.setVisible(false);
        selectedButtons.clear();
        gameBoard.setScore(0);
        scoreManager.resetScore();
    }

    private void handleReturnButtonHighScores() {
        this.menu.setVisible(true);
        this.highScores.setVisible(false);
    }

    private void handleReturnButtonInstructions() {
        this.menu.setVisible(true);
        this.instructions.setVisible(false);
    }

    private void handleReturnButtonEndGame() {
        this.menu.setVisible(true);
        this.endGame.setVisible(false);
        gameBoard.setScore(0);
        scoreManager.resetScore();
    }

    // menu buttons
    private void handlePlayNowBut() {
        menu.setVisible(false);
        difficulty.setVisible(true);
    }

    private void handleHowToBut() {
        menu.setVisible(false);
        instructions.setVisible(true);
    }

    private void handleHighScores() {
        menu.setVisible(false);
        highScores.setVisible(true);
    
        List<Integer> scores = scoreManager.getHighScores(); // Fetch scores from ScoreManager
        highScores.updateScores(scores); // Update the HighScores UI with the scores
    
        highScores.getReturnBut().addActionListener(this); // Ensure return button is functional
        // Check for duplicate
    }
    
    // difficulty buttons
    private void handleEasy() {
        difficulty.setVisible(false);
        game = new Game(GameDifficulty.EASY);
        gameBoard = new GameBoardGUI(GameDifficulty.EASY, this, scoreManager, endGame);
        gameBoard.setScore(0);
        gameBoard.setVisible(true);

        gameBoard.getSubmitBut().addActionListener(this);
        gameBoard.getReturnBut().addActionListener(this);
    }

    private void handleMedium() {
        difficulty.setVisible(false);
        game = new Game(GameDifficulty.MEDIUM);
        gameBoard = new GameBoardGUI(GameDifficulty.MEDIUM, this, scoreManager, endGame);
        gameBoard.setScore(0);
        gameBoard.setVisible(true);

        gameBoard.getSubmitBut().addActionListener(this);
        gameBoard.getReturnBut().addActionListener(this);
    }

    private void handleHard() {
        difficulty.setVisible(false);
        game = new Game(GameDifficulty.HARD);
        gameBoard = new GameBoardGUI(GameDifficulty.HARD, this, scoreManager, endGame);
        gameBoard.setScore(0);
        gameBoard.setVisible(true);

        gameBoard.getSubmitBut().addActionListener(this);
        gameBoard.getReturnBut().addActionListener(this);
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
            String[] words = new String[4];
            for (int i = 0; i < selectedButtons.size(); i++) {
                words[i] = selectedButtons.get(i).getText();
            }
    
            WordGroup guess = new WordGroup(words, null, null);
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
            game.checkGuess(guess);
    
        } catch (Exception e) {
            messageLabel.setText("Error handling your guess. Please try again.");
            e.printStackTrace();
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

    public Game getGame() {
        return this.game;
    }
}
