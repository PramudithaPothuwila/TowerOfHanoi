package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.javadoc.internal.doclets.toolkit.Resources;

import javax.swing.*;
import java.util.Objects;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_menu.fxml")));
        stage.setTitle("Kanishka Hewageegana");
        stage.setScene(new Scene(root, 600, 390));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}
