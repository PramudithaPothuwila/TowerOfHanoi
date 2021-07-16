package com.company;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HighScoreScreenController
{
    @FXML
    private Label highScoreValue1;
    @FXML
    private Label highScoreValue2;
    @FXML
    private Label highScoreValue3;
    @FXML
    private Label highScoreValue4;
    @FXML
    private Label highScoreValue5;
    @FXML
    private Label highScoreValue6;
    @FXML
    private Label highScoreValue7;
    @FXML
    private Label highScoreValue8;
    @FXML
    private Label highScoreValue9;
    @FXML
    private Label highScoreValue10;
    Label[] labels = {highScoreValue1, highScoreValue2, highScoreValue3, highScoreValue4, highScoreValue5, highScoreValue6, highScoreValue7, highScoreValue8, highScoreValue9, highScoreValue10};


    public void initialize()
    {
        setScores();
    }

    public void setScores()
    {
        FileHandler.loadScoreFile();
        ArrayList<Score> scoreList = FileHandler.getScores();
        String[] names = new String[10];
        int[] scores = new int[10];

        for(int i = 0; i < 10; i++)
        {
            names[i] = scoreList.get(i).getName();
            scores[i] = scoreList.get(i).getScore();
        }
        int n = scores.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (scores[j] > scores[j+1])
                {
                    int tempScore = scores[j];
                    scores[j] = scores[j+1];
                    scores[j+1] = tempScore;
                    String tempName = names[j];
                    names[j] = names[j+1];
                    names[j+1] = tempName;
                }
        for(int i = 0; i < 10; i++)
        {
            labels[i].setText(names[i]+ " - " + scores[i]);
        }

    }
}