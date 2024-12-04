package oosd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import oosd.model.Observer;
import oosd.model.ScoreManager;
import oosd.model.WordDifficulty;
import oosd.model.WordGroup;

public class GameBoardGUI extends JFrame implements Observer {

    private Controller controller;
    private Game game;
    private ScoreManager scoreManager;


    public ArrayList<JButton> selectedButtons = new ArrayList<>(); // To store selected buttons
    private int MAX_SELECTION = 4;
    private JButton returnButton;
    private JButton submit;

    private int score;

    private JPanel result1;
    private JLabel category1;
    private JLabel words1;

    private JPanel result2;
    private JLabel category2;
    private JLabel words2;

    private JPanel result3;
    private JLabel category3;
    private JLabel words3;

    private JPanel result4;
    private JLabel category4;
    private JLabel words4;

    private JLabel scoreLabel;
    


    
    WordGrid wordGrid;
    WordButton[] wordButtons;
    JLabel messageLabel;
    
        private JLabel[] mistakeArray;
        
        public GameBoardGUI(GameDifficulty gameDifficulty, Controller controller,  ScoreManager scoreManager) {
            this.game = controller.getGame();
            this.scoreManager = scoreManager;
            this.game.addObserver(this);

            this.controller = controller; // Set the controller immediately
            controller.setMessageLabel(this.messageLabel);
            
            //game = new Game(gameDifficulty);
            
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

            this.controller.setMessageLabel(this.messageLabel);

            // Create Word Grid
            int width = 130;
            int height = 95;
            int[] x = new int[]{55,195,335,475,55,195,335,475,55,195,335,475,55,195,335,475};
            int[] y = new int[]{25,25,25,25,130,130,130,130,235,235,235,235,340,340,340,340};
    
            WordButton[] buttons = new WordButton[16];
            this.wordButtons = buttons;
    
            for (int i = 0; i < 16; i++) {
                String word = "Word " + i;
                buttons[i] = new WordButton(String.valueOf(word));
                buttons[i].setEnabled(true);
                buttons[i].setBounds(x[i], y[i], width, height);
                buttons[i].setOpaque(true);
                buttons[i].setBorderPainted(false);
                buttons[i].setBackground(ColorCodes.lightGray);
    
                buttons[i].addActionListener(e -> controller.handleButtonClick((WordButton) e.getSource()));
            }
    
            WordGrid gridPanel = makeGrid(buttons);
            gridPanel.setPreferredSize(new Dimension(700, 415));
            gridPanel.setLayout(null);
            gridPanel.setBackground(ColorCodes.white);  
            
            for (int i = 0; i < 16; i++) {
                gridPanel.add(buttons[i]);
            }
    
            this.wordGrid = gridPanel;


            // Pass the initialized wordGrid to the Controller
            controller.setWordGrid(this.wordGrid);
    
            // Create Result Bars
            // Result One
            result1 = new JPanel();
            result1.setBackground(ColorCodes.yellow);
            result1.setBounds(55,25,550,95);
            result1.setLayout(new BoxLayout(result1, BoxLayout.Y_AXIS));
            result1.setVisible(false);
    
            category1 = new JLabel();
            category1.setAlignmentX(Component.CENTER_ALIGNMENT);
            category1.setFont(new Font("Veranda", Font.BOLD, 20));
            
            words1 = new JLabel();
            words1.setAlignmentX(Component.CENTER_ALIGNMENT);
            words1.setFont(new Font("Veranda", Font.PLAIN, 15));
    
            result1.add(Box.createRigidArea(new Dimension(0, 20)));
            result1.add(category1);
            result1.add(Box.createRigidArea(new Dimension(0, 10)));
            result1.add(words1);
    
            // Result Two
            result2 = new JPanel();
            result2.setBackground(ColorCodes.green);
            result2.setBounds(55,130,550,95);
            result2.setLayout(new BoxLayout(result2, BoxLayout.Y_AXIS));
            result2.setVisible(false);
    
            category2 = new JLabel();
            category2.setAlignmentX(Component.CENTER_ALIGNMENT);
            category2.setFont(new Font("Veranda", Font.BOLD, 20));
            
            words2 = new JLabel();
            words2.setAlignmentX(Component.CENTER_ALIGNMENT);
            words2.setFont(new Font("Veranda", Font.PLAIN, 15));
    
            result2.add(Box.createRigidArea(new Dimension(0, 20)));
            result2.add(category2);
            result2.add(Box.createRigidArea(new Dimension(0, 10)));
            result2.add(words2);
    
            // Result Three
            result3 = new JPanel();
            result3.setBackground(ColorCodes.blue);
            result3.setBounds(55,235,550,95);
            result3.setLayout(new BoxLayout(result3, BoxLayout.Y_AXIS));
            result3.setVisible(false);
    
            category3 = new JLabel();
            category3.setAlignmentX(Component.CENTER_ALIGNMENT);
            category3.setFont(new Font("Veranda", Font.BOLD, 20));
            
            words3 = new JLabel();
            words3.setAlignmentX(Component.CENTER_ALIGNMENT);
            words3.setFont(new Font("Veranda", Font.PLAIN, 15));
    
            result3.add(Box.createRigidArea(new Dimension(0, 20)));
            result3.add(category3);
            result3.add(Box.createRigidArea(new Dimension(0, 10)));
            result3.add(words3);
    
            // Result 4
            result4 = new JPanel();
            result4.setBackground(ColorCodes.purple);
            result4.setBounds(55,340,550,95);
            result4.setLayout(new BoxLayout(result4, BoxLayout.Y_AXIS));
            result4.setVisible(false);
    
            category4 = new JLabel();
            category4.setAlignmentX(Component.CENTER_ALIGNMENT);
            category4.setFont(new Font("Veranda", Font.BOLD, 20));
            
            words4 = new JLabel();
            words4.setAlignmentX(Component.CENTER_ALIGNMENT);
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

            scoreLabel = new JLabel("Score: " + scoreManager.getCurrentScore());
            scoreLabel.setFont(new Font("Verdana", Font.BOLD, 15));
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(scoreLabel);


    
            mistakeArray[0] = life1;
            mistakeArray[1] = life2;
            mistakeArray[2] = life3;
            mistakeArray[3] = life4;
    
            controller.setMistakeArray(this.mistakeArray);

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

        submit.addActionListener(e -> controller.handleSubmit());
        
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
    }

    private String getIndividualWord( List<String[]> colorList, int i, int randomIntInRange) {
        String[] groupStringArray = colorList.get(randomIntInRange);
        String individualString = groupStringArray[i + 4];
        return individualString;
    }

    private String[] randomizeWords(WordDifficulty wordDifficulty) {
        Random random = new Random();
        HashMap<String, List<String[]>> dictionary = game.getWordDictionary();
    
        List<String[]> wordList = null;
        int indexInRange = 0;
        String difficultyColor = "";
    
        switch (wordDifficulty) {
            case YELLOW:
                wordList = dictionary.get("Yellow");
                indexInRange = random.nextInt(wordList.size());
                difficultyColor = "Yellow";
                break;
            case GREEN:
                wordList = dictionary.get("Green");
                indexInRange = random.nextInt(wordList.size());
                difficultyColor = "Green";
                break;
            case BLUE:
                wordList = dictionary.get("Blue");
                indexInRange = random.nextInt(wordList.size());
                difficultyColor = "Blue";
                break;
            case PURPLE:
                wordList = dictionary.get("Purple");
                indexInRange = random.nextInt(wordList.size());
                difficultyColor = "Purple";
                break;
        }
    
        String[] words = new String[4];
    
        for (int i = 0; i < 4; i++) {
            String individualString = getIndividualWord(wordList, i, indexInRange);
            words[i] = individualString;
        }
    
        String categoryName = wordList.get(indexInRange)[3];
        System.out.println("Difficulty: " + difficultyColor + ", Category: " + categoryName);
        System.out.println("Words: " + Arrays.toString(words));
    
        return words;
    }
    
    

    private WordGrid makeGrid(WordButton[] wordArray) {
        WordGroup[] wordGroups = new WordGroup[4];
        List<String> allWords = new ArrayList<>();
    
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
    
            String[] wordArraySubset = randomizeWords(wordDifficulty);
    
            allWords.addAll(Arrays.asList(wordArraySubset));
    
            WordGroup wordGroup = new WordGroup(wordArraySubset, wordDifficulty);
            wordGroups[i] = wordGroup;
        }
    
        Collections.shuffle(allWords);
    
        for (int i = 0; i < allWords.size(); i++) {
            wordArray[i].updateText(allWords.get(i));
        }
    
        WordGrid wordGrid = new WordGrid(wordGroups, wordArray);
        game.setWordGroups(wordGroups);
    
        return wordGrid;
    }
    



    private void disableWordGroup(WordGroup wordGroup) {
        String[] wordStrings = wordGroup.getWordList();
        WordButton[] wordButtons = this.wordGrid.getWordButtons();

        for (int i = 0; i < wordStrings.length; i++) {
            String disableWord = wordStrings[i];

            for (int j = 0; j < wordButtons.length; j++) {
                if (disableWord.equals(wordButtons[j].getText())) {
                    wordButtons[j].setEnabled(false);
                }
            }
        }
    }

    private void checkLoss(int lives) {
        if (lives == 0) {
            scoreManager.addPoints(-5);
            scoreLabel.setText("Score: " + scoreManager.getCurrentScore());
            messageLabel.setText("You lose.");
            for (WordButton button : wordGrid.getWordButtons()) {
                button.setEnabled(false);
            }
            scoreManager.saveScore();
        } else {
            messageLabel.setText("Incorrect, try again.");

            scoreManager.addPoints(-5);
            scoreLabel.setText("Score: " + scoreManager.getCurrentScore()); // Update score label
            game.decrementLives();
        }
        mistakeArray[lives].setVisible(false);
    }
    

    public JButton getSubmitBut() {
        return submit;
    }

    public JButton getReturnBut() {
        return returnButton;
    }

    public int getScore() {
        return score;
    }

    public void setAnswerBar1(Color background, String category, String words) {
        result1.setBackground(background);
        category1.setText(category);
        words1.setText(words);
    }

    public void setAnswerBar2(Color background, String category, String words) {
        result2.setBackground(background);
        category2.setText(category);
        words2.setText(words);
    }

    public void setAnswerBar3(Color background, String category, String words) {
        result3.setBackground(background);
        category3.setText(category);
        words3.setText(words);
    }

    public void setAnswerBar4(Color background, String category, String words) {
        result4.setBackground(background);
        category4.setText(category);
        words4.setText(words);
    }

    @Override
    public void update(int matchCount, WordGroup correctWords) {
        //System.out.println("matchCount on view is: " + matchCount);
        switch(matchCount) {
            case 0:
            case 1:
            case 2:
                checkLoss(game.getLives() - 1);
                break;
            case 3:
                // wordGrid.decrementLives();
                if (game.getLives() != 0) {
                    messageLabel.setText("One word off.");
                    //wordGrid.decrementLives();
                    break;
                } else {
                    checkLoss(game.getLives() - 1);
                }
                break;
            case 4:
                disableWordGroup(correctWords); // Disable the guessed group
                String difficulty = correctWords.getWordDifficulty().toString(); // Assuming `correctWords` has difficulty info
                scoreManager.addPointsByDifficulty(difficulty); // Add points based on difficulty
                scoreLabel.setText("Score: " + scoreManager.getCurrentScore()); 
                if (game.getGroupsRemaining() == 1) {
                    messageLabel.setText("You win!");
                    scoreManager.saveScore(); // Save the final score
                } else {
                    messageLabel.setText("Correct!");
                    game.decrementGroupsRemaining();
                }
                break;
        }
    }
}