package oosd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
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
    private EndGame endGame;
    private ScoreManager scoreManager;
    private WordGrid wordGrid;

    public ArrayList<JButton> selectedButtons = new ArrayList<>(); // To store selected buttons
    public ArrayList<JButton> disabledButtons = new ArrayList<>(); // To store disabled buttons
    public ArrayList<Integer> occupiedButtons = new ArrayList<>();
    public ArrayList<String> categoryArray = new ArrayList<>();
    public ArrayList<String> wordArray = new ArrayList<>();
    
    private WordButton[] wordButtons;
    private WordButton[] buttons;
    private JLabel[] mistakeArray;

    private int MAX_SELECTION;
    private int score;
    private int width;
    private int height;
    private int[] x;
    private int[] y;

    private JButton returnButton;
    private JButton submit;

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
    private JLabel messageLabel;

    public GameBoardGUI(GameDifficulty gameDifficulty, Controller controller, ScoreManager scoreManager, EndGame endGame) {
        width = 130;
        height = 95;
        x = new int[]{55,195,335,475,55,195,335,475,55,195,335,475,55,195,335,475};
        y = new int[]{25,25,25,25,130,130,130,130,235,235,235,235,340,340,340,340};

        MAX_SELECTION = 4;
        score = 0;

        this.game = controller.getGame();
        this.game.addObserver(this);
        this.endGame = endGame;
        this.scoreManager = scoreManager;

        this.controller = controller; // Set the controller immediately
        controller.setMessageLabel(this.messageLabel);
                        
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
        
    //#region <Create Title & Heading>
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
        messageLabel.setText("");

        this.controller.setMessageLabel(this.messageLabel);
    //#endregion

    //#region <Create Word Grid>
        buttons = new WordButton[16];
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
            occupiedButtons.add(i);
        }

        WordGrid gridPanel = makeGrid(buttons);
        gridPanel.setPreferredSize(new Dimension(700, 415));
        gridPanel.setLayout(null);
        gridPanel.setBackground(ColorCodes.white);  

        this.wordGrid = gridPanel;

        // Pass the initialized wordGrid to the Controller
        controller.setWordGrid(this.wordGrid);
    //#endregion

    //#region <Create Result Bars>
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
    //#endregion
    
    //#region <Create Mistake Tracker>
        JPanel mistakePanel = new JPanel();
        mistakePanel.setLayout(new BoxLayout(mistakePanel, BoxLayout.X_AXIS));
        mistakePanel.setPreferredSize(new Dimension(700, 30));
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

        controller.setMistakeArray(this.mistakeArray);

        life1.setFont(new Font("Verdana", Font.PLAIN, 42));
        life2.setFont(new Font("Verdana", Font.PLAIN, 42));
        life3.setFont(new Font("Verdana", Font.PLAIN, 42));
        life4.setFont(new Font("Verdana", Font.PLAIN, 42));

        life1.setForeground(ColorCodes.darkGray);
        life2.setForeground(ColorCodes.darkGray);
        life3.setForeground(ColorCodes.darkGray);
        life4.setForeground(ColorCodes.darkGray);
    //#endregion

    //#region <Create Submit & Return Buttons>
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(700, 120));
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.setBackground(ColorCodes.white);
        
        submit = new JButton("Submit Guess");
        submit.setFont(new Font("Verdana", Font.PLAIN, 15));
        submit.setPreferredSize(new Dimension(20,40));
        submit.setForeground(ColorCodes.white);
        submit.setBackground(ColorCodes.darkGray);
        submit.setOpaque(true);
        submit.setBorderPainted(false);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        submit.addActionListener(e -> controller.handleSubmit());
        
        returnButton = new JButton("Return to Menu");
        returnButton.setFont(new Font("Veranda", Font.PLAIN, 15));
        submit.setPreferredSize(new Dimension(15,30));
        returnButton.setBackground(ColorCodes.purple);
        returnButton.setOpaque(true);
        returnButton.setBorder(BorderFactory.createLineBorder(ColorCodes.purple, 5));
        returnButton.setForeground(ColorCodes.white);
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setActionCommand("RTM_GameBoard");

        scoreLabel = new JLabel("Score: " + scoreManager.getCurrentScore());
        scoreLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    //#endregion

    //#region <Fill Panels>
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
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(returnButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(scoreLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        this.pack();
        this.setVisible(true);
    //#endregion
    }

    private String getIndividualWord( List<String[]> colorList, int i, int randomIntInRange) {
        String[] groupStringArray = colorList.get(randomIntInRange);
        String individualString = groupStringArray[i + 4];
        return individualString;
    }

    private static void adjustFontSize(WordButton button) {
        System.out.println("Changing text size of the word: "+ button.getText());
        String text = button.getText();
        int buttonWidth = 130;
        int buttonHeight = 95;

        // Get the button size (if already defined by layout manager)
        if (button.getPreferredSize() != null) {
            buttonWidth = button.getPreferredSize().width;
            buttonHeight = button.getPreferredSize().height;
        }

        // Set initial font size and calculate
        Font font = button.getFont();
        int fontSize = font.getSize();

        while (fontSize > 1) {
            FontMetrics metrics = button.getFontMetrics(new Font(font.getName(), font.getStyle(), fontSize));
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();

            // Check if text fits within the button dimensions
            if (textWidth <= buttonWidth - 10 && textHeight <= buttonHeight - 10) {
                break;
            }

            // Reduce font size if text does not fit
            fontSize--;
        }

        // Apply the adjusted font size
        button.setFont(new Font(font.getName(), font.getStyle(), fontSize));
    }

    private WordGroup randomizeWords(String[] words, WordDifficulty wordDifficulty, int groupNumber) {
        //Random random = new Random();
        //HashMap<String, List<String[]>> dictionary = game.getWordDictionary();
        // String category = "not initialized";
    
        //List<String[]> wordList = null;
        //int indexInRange = 0;
        /*String difficultyColor = "";
    
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
    
        String[] words = new String[4];*/

        Random random = new Random();
        WordButton[] wordButtons = this.wordButtons;
        
        HashMap<String, List<String[]>> dictionary = game.getWordDictionary();
        
        int indexInRange = 0;
        String individualString = "not initialized";
        Color categoryColor = ColorCodes.lightGray;
        String category = "not initialized";

        List<String[]> yellowList = dictionary.get("Yellow");
        List<String[]> greenList = dictionary.get("Green");
        List<String[]> blueList = dictionary.get("Blue");
        List<String[]> purpleList = dictionary.get("Purple");
        List<String[]> wordList = null;

        int yellowIntInRange = random.nextInt(yellowList.size());
        int greenIntInRange = random.nextInt(greenList.size());
        int blueIntInRange = random.nextInt(blueList.size());
        int purpleIntInRange = random.nextInt(purpleList.size());


        for (int i = 0; i < 4; i++) {
            switch (wordDifficulty) {
                case WordDifficulty.YELLOW:
                    individualString = getIndividualWord(yellowList, i, yellowIntInRange);
                    System.out.println("Yellow Group: " + individualString);
                    categoryColor = ColorCodes.yellow;
                    words[i] = individualString;
                    category = yellowList.get(yellowIntInRange)[3];
                    wordList = dictionary.get("Yellow");
                    indexInRange = random.nextInt(wordList.size());
                    break;
                case WordDifficulty.GREEN:
                    individualString = getIndividualWord(greenList, i, greenIntInRange);
                    System.out.println("Green Group: " + individualString);
                    categoryColor = ColorCodes.green;
                    words[i] = individualString;
                    category = greenList.get(greenIntInRange)[3];
                    wordList = dictionary.get("Green");
                    indexInRange = random.nextInt(wordList.size());
                    break;
                case WordDifficulty.BLUE:
                    individualString = getIndividualWord(blueList, i, blueIntInRange);
                    System.out.println("Blue Group: " + individualString);
                    categoryColor = ColorCodes.blue;
                    words[i] = individualString;
                    category = blueList.get(blueIntInRange)[3];
                    wordList = dictionary.get("Blue");
                    indexInRange = random.nextInt(wordList.size());
                    break;
                case WordDifficulty.PURPLE:
                    individualString = getIndividualWord(purpleList, i, purpleIntInRange);
                    System.out.println("Purple Group: " + individualString);
                    categoryColor = ColorCodes.purple;
                    words[i] = individualString;
                    category = purpleList.get(purpleIntInRange)[3];
                    wordList = dictionary.get("Purple");
                    indexInRange = random.nextInt(wordList.size());
                    break;
            }
            wordButtons[i + (4 * groupNumber)].updateText(individualString); //this should update view
        }
        WordGroup wordGroupRandom = new WordGroup(words, wordDifficulty, category);
        String wordsString = String.join("", words);
    //merge mess - fix me
        //String categoryName = wordList.get(indexInRange)[3];
        //System.out.println("Difficulty: " + categoryColor + ", Category: " + categoryName);
        //System.out.println("Words: " + Arrays.toString(words));
    
        return wordGroupRandom;
    }
    
    private WordGrid makeGrid(WordButton[] wordArray) {
        WordGroup[] wordGroups = new WordGroup[4];
        List<String> allWords = new ArrayList<>();
        int wordCount = 0;
    
        for (int i = 0; i < 4; i++) {
            WordDifficulty wordDifficulty = WordDifficulty.YELLOW;
            //TODO: why^
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
            String[] wordArraySubset = new String[4];
            for (int j = 0; j < 4; j++) {
                wordArraySubset[j] = wordArray[wordCount].getText();
                wordCount++;
            }
            WordGroup wordGroup = randomizeWords(wordArraySubset, wordDifficulty, i);
            wordGroups[i] = wordGroup;
        }
    
        Collections.shuffle(allWords);
    
        for (int i = 0; i < allWords.size(); i++) {
            wordArray[i].updateText(allWords.get(i));
            if (i == 15) {
                wordArray[i].updateText("SUPER-DUPER LONG WORD");
            }
            adjustFontSize(wordArray[i]);
        }
    
        WordGrid wordGrid = new WordGrid(wordGroups, wordArray);
        game.setWordGroups(wordGroups);

        endGame.setGroupOneAnswers(getColor(wordGroups[0]), wordGroups[0].getCategory(), String.join("", wordGroups[0].getWordList()));
        endGame.setGroupTwoAnswers(getColor(wordGroups[1]), wordGroups[1].getCategory(), String.join("", wordGroups[1].getWordList()));
        endGame.setGroupThreeAnswers(getColor(wordGroups[2]), wordGroups[2].getCategory(), String.join("", wordGroups[2].getWordList()));
        endGame.setGroupFourAnswers(getColor(wordGroups[3]), wordGroups[3].getCategory(), String.join("", wordGroups[3].getWordList()));

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
                    wordButtons[j].setText(null);
                    disabledButtons.add(wordButtons[j]);
                }
            }
        }
    }

    public void moveWords(WordGroup wordGroup, int groupsRemaining) {
        String[] correctGuesses = wordGroup.getWordList();
        WordButton[] wordButtons = this.wordGrid.getWordButtons();
        int[] toSwap = new int[4];

        switch (groupsRemaining) {
            case 4:
                for (int i = 0; i < 4; i++) {
                    toSwap[i] = findWord(wordButtons, correctGuesses[i]);
                }
                int[] row1 = {0, 1, 2, 3};

                for (int i = 0; i < 4; i++) {
                    wordButtons[toSwap[i]].setBounds(x[row1[i]], y[row1[i]], width, height);
                    wordButtons[row1[i]].setBounds(x[toSwap[i]], y[toSwap[i]], width, height);

                    int tempx = x[row1[i]];
                    x[row1[i]] = x[toSwap[i]];
                    x[toSwap[i]] = tempx;

                    int tempy = y[row1[i]];
                    y[row1[i]] = y[toSwap[i]];
                    y[toSwap[i]] = tempy;

                    WordButton tempBut = wordButtons[row1[i]];
                    wordButtons[row1[i]] = wordButtons[toSwap[i]];
                    wordButtons[toSwap[i]] = tempBut;
                }
                break;

            case 3:
                for (int i = 0; i < 4; i++) {
                    toSwap[i] = findWord(wordButtons, correctGuesses[i]);
                }
                int[] row2 = {4, 5, 6, 7};

                for (int i = 0; i < 4; i++) {
                    wordButtons[toSwap[i]].setBounds(x[row2[i]], y[row2[i]], width, height);
                    wordButtons[row2[i]].setBounds(x[toSwap[i]], y[toSwap[i]], width, height);

                    int tempx = x[row2[i]];
                    x[row2[i]] = x[toSwap[i]];
                    x[toSwap[i]] = tempx;

                    int tempy = y[row2[i]];
                    y[row2[i]] = y[toSwap[i]];
                    y[toSwap[i]] = tempy;

                    WordButton tempBut = wordButtons[row2[i]];
                    wordButtons[row2[i]] = wordButtons[toSwap[i]];
                    wordButtons[toSwap[i]] = tempBut;
                }

                break;
            case 2:
                for (int i = 0; i < 4; i++) {
                    toSwap[i] = findWord(wordButtons, correctGuesses[i]);
                }
                int[] row3 = {8, 9, 10, 11};

                for (int i = 0; i < 4; i++) {
                    wordButtons[toSwap[i]].setBounds(x[row3[i]], y[row3[i]], width, height);
                    wordButtons[row3[i]].setBounds(x[toSwap[i]], y[toSwap[i]], width, height);

                    int tempx = x[row3[i]];
                    x[row3[i]] = x[toSwap[i]];
                    x[toSwap[i]] = tempx;

                    int tempy = y[row3[i]];
                    y[row3[i]] = y[toSwap[i]];
                    y[toSwap[i]] = tempy;

                    WordButton tempBut = wordButtons[row3[i]];
                    wordButtons[row3[i]] = wordButtons[toSwap[i]];
                    wordButtons[toSwap[i]] = tempBut;
                }
                
                break;
            case 1:
                for (int i = 0; i < 4; i++) {
                    toSwap[i] = findWord(wordButtons, correctGuesses[i]);
                }
                int[] row4 = {12, 13, 14, 15};

                for (int i = 0; i < 4; i++) {
                    wordButtons[toSwap[i]].setBounds(x[row4[i]], y[row4[i]], width, height);
                    wordButtons[row4[i]].setBounds(x[toSwap[i]], y[toSwap[i]], width, height);
                    
                    int tempx = x[row4[i]];
                    x[row4[i]] = x[toSwap[i]];
                    x[toSwap[i]] = tempx;

                    int tempy = y[row4[i]];
                    y[row4[i]] = y[toSwap[i]];
                    y[toSwap[i]] = tempy;

                    WordButton tempBut = wordButtons[row4[i]];
                    wordButtons[row4[i]] = wordButtons[toSwap[i]];
                    wordButtons[toSwap[i]] = tempBut;
                }

                break;
            default:
                break;
        }
    }
    
    private int findWord(WordButton[] wordButtons, String findMe) {
        for (int i = 0; i < 16; i++) {
            String word = wordButtons[i].getText();
            if (word.equals(findMe)) {
                return i;
            }
        }
        return -1;
    }

    private void checkWinLose(int lives, int groupsRemaining) {

        WordButton[] wordButtons = wordGrid.getWordButtons();

        if (groupsRemaining == 0) {
            showEndGame(true);
            scoreLabel.setText("Score: " + scoreManager.getCurrentScore());
            scoreManager.saveScore();
            return;
        }
        System.out.println("Lives " + lives);
        
        if (lives == 0) {
            messageLabel.setText("You Lost!");
            for (int i = 0; i < wordButtons.length; i++) {
                wordButtons[i].setEnabled(false);
                scoreLabel.setText("Score: " + scoreManager.getCurrentScore());
                scoreManager.saveScore();
            }
            showEndGame(false);
        } else {
            scoreLabel.setText("Score: " + scoreManager.getCurrentScore()); // Update score label
        }
        System.out.println("groups remaining: " + groupsRemaining);
        System.out.println("lives left: " + lives);
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
        System.out.println("matchCount on view is: " + matchCount);
        checkWinLose(game.getLives(), game.getGroupsRemaining());
        int lives = game.getLives();
        switch(matchCount) {
            case 0:
                logIncorrectGuess(lives, "Incorrect, try again.");
                break;
            case 1:
                logIncorrectGuess(lives, "Incorrect, try again.");
                break;
            case 2:
                logIncorrectGuess(lives, "Incorrect, try again.");
                break;
            case 3:
                if (game.getLives() != 0) {
                    logIncorrectGuess(lives, "One word off!");
                }
                break;
            case 4:
                disableWordGroup(correctWords); //pass the words to get disabled
                moveWords(correctWords, game.getGroupsRemaining());
                String wordsCorrect = String.join(" ", correctWords.getWordList());
                System.out.println("case 4 words correct: " + wordsCorrect);
                
                String difficulty = correctWords.getWordDifficulty().toString();
                System.out.println("group difficulty: " + difficulty);

                String correctWordString = String.join(" ", correctWords.getWordList());

                String category = correctWords.getCategory();

                Color color = getColor(correctWords);

                int points = getPoints(correctWords);

                if (game.getGroupsRemaining() == 1) {
                    messageLabel.setText("You win!");
                    scoreManager.addPoints(points);
                    scoreManager.saveScore(); // Save the final score
                    setAnswerBar4(color, category, correctWordString);
                    result4.setVisible(true);
                    game.decrementGroupsRemaining();

                } else if (game.getGroupsRemaining() == 2){
                    messageLabel.setText("Correct!");
                    scoreManager.addPoints(points);
                    game.decrementGroupsRemaining();
                    setAnswerBar3(color, category, correctWordString);
                    result3.setVisible(true);

                } else if (game.getGroupsRemaining() == 3){
                    messageLabel.setText("Correct!");
                    scoreManager.addPoints(points);
                    game.decrementGroupsRemaining();
                    setAnswerBar2(color, category, correctWordString);
                    result2.setVisible(true);

                } else if (game.getGroupsRemaining() == 4){
                    messageLabel.setText("Correct!");
                    scoreManager.addPoints(points);
                    game.decrementGroupsRemaining();
                    setAnswerBar1(color, category, correctWordString);
                    result1.setVisible(true);

                }
                System.out.println("groups remaining = " + game.getGroupsRemaining());
                break;
        }
        checkWinLose(game.getLives(), game.getGroupsRemaining());
    }

    public static void printButtonListWithPositions(ArrayList<String> buttonList) {
        for (int i = 0; i < buttonList.size(); i++) {
            String button = buttonList.get(i);
            System.out.println("Position " + i + ": " + button); 
        }
    }

    public ArrayList<JButton> getButton(String word) {

        ArrayList<JButton> buttonID = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            String buttonText = buttons[i].getText();
            if (buttonText == word) {
                buttonID.add(i, buttons[i]);
            } 
        }

        return buttonID;
    }

    private void showEndGame(boolean win) {
        if (win) {
            endGame.setWinLossMsg("Congratulations!");
        } else {
            endGame.setWinLossMsg("Better Luck Next Time!");
        }
        
        endGame.setFinalScore(scoreManager.getCurrentScore());
        this.setVisible(false);
        endGame.setVisible(true);
    }

    private Color getColor(WordGroup wordGroup) {
        switch (wordGroup.getWordDifficulty()) {
            case YELLOW:
                return ColorCodes.yellow;
            case GREEN:
                return ColorCodes.green;
            case BLUE:
                return ColorCodes.blue;
            case PURPLE:
                return ColorCodes.purple;
            default:
                return ColorCodes.lightGray;
        }
    }

    private int getPoints(WordGroup wordGroup) {
        switch (wordGroup.getWordDifficulty()) {
            case YELLOW:
                return 5;
            case GREEN:
                return 10;
            case BLUE:
                return 15;
            case PURPLE:
                return 20;
            default:
                return 0;
        }
    }

    private void logIncorrectGuess(int lives, String message) {
        game.decrementLives();
        scoreManager.addPoints(-5);
        mistakeArray[lives-1].setVisible(false);
        messageLabel.setText(message);
    }

    public void setScore(int newScore) {
        score = newScore;
    }

}