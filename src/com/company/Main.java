package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Main  extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("highScoreScreen.fxml")));
        stage.setTitle("Kanishka Hewageegana");
        stage.setScene(new Scene(root, 600, 390));
        stage.show();
    }

    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    public static void createDatFile()
    {
        for(int i = 0; i < 10;i++)
        {
            String name = "Test"+i;
            Score score = new Score(name, i);
            FileHandler.insertNewScore(score);
        }
    }
}
