package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.*;
import java.io.*;

public class HighScores extends JPanel {
    public void view() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("High Scores");
        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(640, 360);
        jFrame.setVisible(true);
    }
}
class Score  implements Serializable {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getNaam() {
        return name;
    }

    public Score(String naam, int score) {
        this.score = score;
        this.name = naam;
    }
}
class ScoreComparator implements Comparator<Score> {
    public int compare(Score score1, Score score2) {

        int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
    }
}
class HighscoreManager {
    // An arraylist of the type "score" we will use to work with the scores inside the class
    private ArrayList<Score> scores;

    // The name of the file where the highscores will be saved
    private static final String HIGHSCORE_FILE = "scores.dat";

    //Initialising an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighscoreManager() {
        //initialising the scores-arraylist
        scores = new ArrayList<Score>();
    }
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
    public String getHighscoreString() {
        String highscoreString = "";
        int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getNaam() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
}
