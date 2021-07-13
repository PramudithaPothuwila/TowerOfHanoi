package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Objects;

public class Main extends Application {
    public TextField username;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_menu.fxml")));
        stage.setTitle("Kanishka Hewageegana");
        stage.setScene(new Scene(root, 600, 390));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void startGameHandler(ActionEvent event) {
        SwingUtilities.invokeLater(
                () -> {
                    Game game = new Game("Tower Of Hanoi - Kanishka Hewageegana");
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

    public void submit(ActionEvent event) {
    }
}
