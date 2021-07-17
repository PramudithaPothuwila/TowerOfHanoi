package com.company;

import com.company.clock.doCountDown;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Controller {
    public TextField username;
    public RadioButton novice;
    public RadioButton intermediate;
    public RadioButton expert;
    public Button play_btn;
    public ToggleGroup level;
    // number of Disc
    private int nDisc;


    public void initialize() {
        play_btn.setDisable(true);

    }

    // return number of Disc
    //Toggle Group and assign how many disk need to update according to level
    private int numberOfDisc() {
        RadioButton selectedRadioButton = (RadioButton) level.getSelectedToggle();
        String value = selectedRadioButton.getText();
        System.out.println(value);
        if(novice.isSelected())
        {
           return nDisc = 4;
        }
        if(intermediate.isSelected())
        {
            return nDisc = 5;
        }
        if(expert.isSelected())
        {
            return nDisc = 7;
        }else{
            return nDisc=0;
        }

    }

<<<<<<< Updated upstream
    public void startGameHandler(ActionEvent event) {
=======

    public void startGameHandler(ActionEvent event)
    {
        //All the time and Score algorithms are develop in TimeScoreAlgo class and Clock direc
        //Didn't connect because new game develop in swing and clock develop in javaFx  Better to not to connect
>>>>>>> Stashed changes
        SwingUtilities.invokeLater(
                () -> {
                    Game game = new Game("Tower Of Hanoi - Kanishka Hewageegana", username.getText());
                    game.t = new Tower(numberOfDisc());//number of Disks
                    //Test File handling part sending values to high Score
                    Score s1=new Score(username.getText(),20);
                    ScoreArray s= new ScoreArray();
                    s.addScore(s1);
                    game.f.getContentPane().add(game.t);
                    doCountDown d = new doCountDown();

                }
        );
    }
    //Use For stop enable new game button until user input their username
    public void keyReleasedProperty() {
        String userName = username.getText();
        boolean isDisabled = (userName.isEmpty() || userName.trim().isEmpty());
        play_btn.setDisable(isDisabled);
    }

    public void solveHandler(ActionEvent event) {
        SwingUtilities.invokeLater(() ->
        {
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

<<<<<<< Updated upstream
    public void highScoreHandler(ActionEvent actionEvent) {
        SwingUtilities.invokeLater(() -> {
            HighScores highScores = new HighScores();
            highScores.view();
            HighscoreManager hm = new HighscoreManager();
            hm.addScore("Bart", 2240);
            hm.addScore("Marge", 300);
            hm.addScore("Maggie", 220);
            hm.addScore("Homer", 100);
            hm.addScore("Lisa", 270);

            System.out.println(username.getText());

            System.out.print(hm.getHighscoreString());
        });

=======
    public void highScoreHandler(ActionEvent actionEvent)
    {
//        HighScoreScreenController t = new HighScoreScreenController();
//        t.setScores();
        // Connect with other page
        try {
            Parent highScorePage = FXMLLoader.load(getClass().getResource("highScoreScreen.fxml"));
            Scene scene = new Scene(highScorePage);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            //Stage appStage= (Stage)((Node)actionEvent).getSource().getWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        SwingUtilities.invokeLater(() -> {
//            HighScores highScores = new HighScores();
//            highScores.view();
//            HighscoreManager hm = new HighscoreManager();
//            hm.addScore("Bart", 2240);
//            hm.addScore("Marge", 300);
//            hm.addScore("Maggie", 220);
//            hm.addScore("Homer", 100);
//            hm.addScore("Lisa", 270);
//
//            System.out.println(username.getText());
//
//            System.out.print(hm.getHighscoreString());
//        });
>>>>>>> Stashed changes
    }
}


