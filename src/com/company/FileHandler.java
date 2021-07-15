package com.company;

import java.io.*;
import java.util.ArrayList;

public class FileHandler
{

    private static FileOutputStream fileOutputStream = null;
    private static ObjectOutputStream objectOutputStream = null;
    private String HIGHSCORE_FILE = "scores.dat";
    private static ArrayList<Score> scores;


    public static ArrayList<Score> getScores()
    {
        return scores;
    }

    public static void insertNewScore(Score score)
    {
        scores.add(score);
    }

    public static void writeDatFile(String fileName) throws Exception
    {
        try
        {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (int i = 0; i < scores.size(); i++)
            {
                objectOutputStream.writeObject(scores.get(i));
                objectOutputStream.flush();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (objectOutputStream != null)
            {
                objectOutputStream.close();
            }
            if (fileOutputStream != null)
            {
                fileOutputStream.close();
            }
        }
    }

    public void loadScoreFile()
    {
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
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