package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import javax.swing.*;

public class Controller
{
    public TextField username;

    public void startGameHandler(ActionEvent event) {
        SwingUtilities.invokeLater(
                () -> {
                    Game game = new Game("Tower Of Hanoi - Kanishka Hewageegana",username.getText());
                    Game.t = new Tower();
                    Game.f.getContentPane().add(Game.t);
                });
    }

    public void solveHandler(ActionEvent event) {
        SwingUtilities.invokeLater(() -> {
            try {
                Solution.solve();
            } catch (InterruptedException e) {
                e.printStackTrace(); // Traces the error
            }
        });
    }

    public void exitButtonAction(ActionEvent event) {
        System.out.println("Exit Program.");
        System.exit(0);
    }

    public void highScoreHandler(ActionEvent actionEvent) {
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

    public void noviceButtonAction(ActionEvent event) {
        System.out.println("Novice CheckBox Button is pressed");
    }

    public void intermediateButtonAction(ActionEvent event) {
        System.out.println("Intermediate CheckBox Button is pressed");
    }

    public void expertButtonAction(ActionEvent event) {
        System.out.println("Expert CheckBox Button is pressed");
    }

    public void submit(ActionEvent event)
    {
    }

}
