package com.company;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.swing.*;

public class Controller
{
    public TextField username;
    public ToggleGroup level;

    public void initialize()
    {

    }

    public void startGameHandler(ActionEvent event)
    {
        SwingUtilities.invokeLater(
                () -> {
                    Game game = new Game("Tower Of Hanoi - Kanishka Hewageegana",username.getText());
                    Game.t = new Tower();
                    Game.f.getContentPane().add(Game.t);
                });
    }

    public void solveHandler(ActionEvent event)
    {
        SwingUtilities.invokeLater(() ->
        {
            try {
                Solution.solve();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
    }

    public void exitButtonAction(ActionEvent event)
    {
        System.out.println("Exit Program.");
        System.exit(0);
    }

    public void highScoreHandler(ActionEvent actionEvent)
    {
        SwingUtilities.invokeLater(() -> {
            HighScores highScores = new HighScores();
            highScores.view();
            HighscoreManager hm = new HighscoreManager();
            hm.addScore("Bart",2240);
            hm.addScore("Marge",300);
            hm.addScore("Maggie",220);
            hm.addScore("Homer",100);
            hm.addScore("Lisa",270);

            System.out.println(username.getText());

            System.out.print(hm.getHighscoreString());
        });
    }
}
