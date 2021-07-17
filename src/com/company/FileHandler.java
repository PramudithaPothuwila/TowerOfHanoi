package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler
{
    private static final String HIGHSCORE_FILE = "scores.dat";
    public static ScoreArray scores =  new ScoreArray();

    public static void writeToFile()
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(HIGHSCORE_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(scores);
            out.close();
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Score> getScoresFromFile()
    {
        List<Score> temp = new ArrayList<>();
        readFile();
        for(int i = 0; i < 10; i++)
        {
            temp.add(scores.getScore(i));
        }
        return temp;
    }

    public static void readFile()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(HIGHSCORE_FILE);
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            scores = (ScoreArray) in.readObject();
            in.close();
            fileInputStream.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

class ScoreArray implements Serializable
{
    @Serial
    private static final long serialVersionUID = 132165412L;
    private static List<Score> scoreList = new ArrayList<>();


    public boolean isEmpty()
    {
        return scoreList.isEmpty();
    }
    public int size()
    {
        return scoreList.size();
    }

    public static void scoreFiller()
    {
        Score filler = new Score("User", 0);
        if(scoreList.isEmpty())
        {
            for(int i = 0; i < 10;i++)
            {
                {
                    scoreList.add(i,filler);
                }
            }
        }
        else if(scoreList.size() < 10)
        {
            for(int i = scoreList.size() - 1; i < 10; i++)
            {
                scoreList.set(i, filler);
            }
        }
        try
        {
            scoreList  = FileHandler.getScoresFromFile();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addScore(Score score)
    {
        if(scoreList.size() == 10 && scoreList.get(9).getScore() < score.getScore())
        {
            scoreList.add(9, score);
            sorter();
            FileHandler.writeToFile();
        }
        else
        {
            scoreList.add(scoreList.size() - 1, score);
            sorter();
            FileHandler.writeToFile();
        }
    }

    private void sorter()
    {
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
            Score temp = new Score(names[i], scores[i]);
            addScore(i, temp);
        }
    }
    public void addScore(int index,Score score)
    {
        scoreList.add(index, score);
        FileHandler.writeToFile();
    }

    public static List<Score> getScoreList()
    {
        return scoreList;
    }

    public Score getScore(int index)
    {
        return scoreList.get(index);
    }
}

class Score
{
    private int score;
    private String name;

    public int getScore()
    {
        return score;
    }

    public String getName()
    {
        return name;
    }

    public Score(String name, int score)
    {
        this.score = score;
        this.name = name;
    }
}