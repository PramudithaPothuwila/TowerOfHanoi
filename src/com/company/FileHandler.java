package com.company;

import java.io.*;
import java.util.ArrayList;

public class FileHandler
{

    private static FileOutputStream fileOutputStream = null;
    private static ObjectOutputStream objectOutputStream = null;
    private static String HIGHSCORE_FILE = "scores.dat";
    private static ArrayList<Score> scores = new ArrayList<>();


    public static ArrayList<Score> getScores()
    {
        return scores;
    }

    public static void insertNewScore(Score score)
    {
        scores.add(scores.size(), score);
        System.out.println(scores.get(scores.size() - 1).getName());
    }

    public static void writeToFile() throws FileNotFoundException
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

    public static void loadScoreFile()
    {
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            Object object = inputStream.readObject();
            System.out.println("before");
            System.out.println(object);
            System.out.println("After");
            System.out.println(scores);
            for (int i = 0; i < 10; i++)
            {
                System.out.println(object);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        }
        finally
        {
            try
            {
                if (objectOutputStream != null)
                {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }
}

class Score  implements Serializable
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