package com.company;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;


public class HighScoreScreenController
{
    public Label highScoreValue1 = new Label();
    public Label highScoreValue2 = new Label();
    public Label highScoreValue3 = new Label();
    public Label highScoreValue4 = new Label();
    public Label highScoreValue5 = new Label();
    public Label highScoreValue6 = new Label();
    public Label highScoreValue7 = new Label();
    public Label highScoreValue8 = new Label();
    public Label highScoreValue9 = new Label();
    public Label highScoreValue10 = new Label();
    Label[] labels = {highScoreValue1, highScoreValue2, highScoreValue3, highScoreValue4, highScoreValue5, highScoreValue6, highScoreValue7, highScoreValue8, highScoreValue9, highScoreValue10};


    public void initialize()
    {
        setScores();
    }

    public void setScores()
    {
        List<Score> scoreList = ScoreArray.getScoreList();
        String[] names = new String[10];
        int[] scores = new int[10];

        for(int i = 0; i < 10; i++)
        {
            names[i] = scoreList.get(i).getName();
            scores[i] = scoreList.get(i).getScore();
            String temp = names[i]+ " - " + scores[i];
            labels[i].setText(temp);
        }
    }
}