package com.company;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;

import javax.swing.*;

public class Controller
{
    public TextField username;
    public RadioButton novice;
    public RadioButton intermediate;
    public RadioButton expert;
    public Button play_btn;
    public ToggleGroup level;
    // number of Disc
    private int nDisc;




    public void initialize()
    {
        play_btn.setDisable(true);

    }
    // return number of Disc
    private int numberOfDisc()
    {
//        RadioButton selectedRadioButton = (RadioButton) level.getSelectedToggle();
//        String value = selectedRadioButton.getText();
//        System.out.println(value);
        if(this.level.getSelectedToggle().equals(this.expert))
        {
            nDisc=9;
        }
        if(this.level.getSelectedToggle().equals(this.intermediate))
        {
            nDisc=6;
        }
        else{
            nDisc=4;
        }
        return nDisc;
    }

    public void startGameHandler(ActionEvent event) {
        SwingUtilities.invokeLater(
                () -> {
                    Game game = new Game("Tower Of Hanoi - Kanishka Hewageegana", username.getText());
                    Game.t = new Tower(numberOfDisc());//number of Disks
                    Game.f.getContentPane().add(Game.t);
                }
            );
    }
    public void keyReleasedProperty(){
        String userName = username.getText();
        boolean isDisabled = (userName.isEmpty() || userName.trim().isEmpty());
        play_btn.setDisable(isDisabled);
    }

    public void solveHandler(ActionEvent event)
    {
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

