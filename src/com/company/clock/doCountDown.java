package com.company.clock;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.text.DecimalFormat;

public class doCountDown extends Application {

    private VBox vBox = new VBox();
    private Label label = new Label("Clock & Score");
    private DigitalClock clock = new DigitalClock();
    private boolean running = false;

    @Override
    public void start(Stage stage) throws Exception {
        vBox.getChildren().addAll(label,clock);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            public void handle(WindowEvent arg0)
            {
                running = false;
                stage.close();
            }
        });
        timeScore t= new timeScore();
        t.runClock();
    }
    public class timeScore{
        private void runClock() {
            running = true;
            new Thread(){
                public void run()
                {
                    long last =System.nanoTime();
                    double delta=0;
                    double ns = 1000000000.0/1;
                    int count = 0;
                    int score=100;
                    while (running) {
                        long now = System.nanoTime();
                        delta +=(now -last)/ns;

                        last = now;
                        while (delta >= 1)
                        {
                            DecimalFormat df = new DecimalFormat("00");
                            count =(count+1)%60;
                            if(count%10==0){score=score-20;}
                            System.out.println("pulse.."+df.format(count) +" Score : "+ score);
                            clock.refreshDigits(df.format(count));
                            clock.refreshScore("Score : "+score);
//                        clock.refreshScore(df.format(score));
                            delta--;
                            //if(count==20){return;}
                            if(score==0){return;}
                        }
                    }
                }

            }.start();
        }
    }




    public static void main(String [] args)
    {
        launch(args);
    }
}
