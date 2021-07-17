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
        highScoreValue1.setText(names[0]+ " - " + scores[0]);
        highScoreValue2.setText(names[1]+ " - " + scores[1]);
        highScoreValue3.setText(names[2]+ " - " + scores[2]);
        highScoreValue4.setText(names[3]+ " - " + scores[3]);
        highScoreValue5.setText(names[4]+ " - " + scores[4]);
        highScoreValue6.setText(names[5]+ " - " + scores[5]);
        highScoreValue7.setText(names[6]+ " - " + scores[6]);
        highScoreValue8.setText(names[7]+ " - " + scores[7]);
        highScoreValue9.setText(names[8]+ " - " + scores[8]);
        highScoreValue10.setText(names[9]+ " - " + scores[9]);

    }
}