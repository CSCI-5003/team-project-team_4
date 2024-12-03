package oosd.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {

    private int currentScore;
    private final String highScoresFile = "highscores.txt";
    private final List<Integer> highScores = new ArrayList<>();

    public ScoreManager() {
        loadHighScores();
        currentScore = 0; // Initialize score
    }

    // Update the score based on group type
    public void addPoints(int points) {
        currentScore += points;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    // Save the current score to the high scores list
    public void saveScore() {
        highScores.add(currentScore);
        Collections.sort(highScores, Collections.reverseOrder()); // Sort in descending order
        if (highScores.size() > 10) {
            highScores.remove(highScores.size() - 1); // Keep only top 10 scores
        }
        saveHighScoresToFile();
    }

    // Load high scores from file
    private void loadHighScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(highScoresFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                highScores.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("No previous scores found: " + e.getMessage());
        }
    }

    // Save high scores to file
    private void saveHighScoresToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(highScoresFile))) {
            for (int score : highScores) {
                pw.println(score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getHighScores() {
        return highScores;
    }
}

