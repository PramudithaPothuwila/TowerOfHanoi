package com.company.clock;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.text.Font;

public class DigitalClock extends Parent{
    private final int boxHeight = 10;
    private final int boxWidth = boxHeight*5/8;
    private  final int scale =5;
    private  Font FONT = new Font(10*scale);
    private HBox hBox = new HBox();
    private Text[] digits = new Text[3];
    private Group[] digitsGroup = new Group[3];
    private Label timeLabel = new Label();
    

    public DigitalClock()
    {
        configureDigits();
        configureHbox();
        getChildren().add(hBox);
    }

    private void configureHbox() {
        hBox.getChildren().add(timeLabel);
        timeLabel.setText("Time:");
        timeLabel.setLayoutY(-12);
        timeLabel.setLayoutX(10);
        timeLabel.setFont(FONT);
        hBox.getChildren().addAll(digitsGroup);


        hBox.setSpacing(5);
    }

    private void configureDigits() {
        for(int i = 0; i< digits.length;i++)
        {
            digits[i] = new Text("0");
            digits[i].setFont(FONT);
            digits[i].setTextOrigin(VPos.TOP);
            digits[i].setLayoutY(-10);
            digits[i].setLayoutX(2);
            Rectangle bg = null;

            if(i==0)
            {
                bg = createBackground(Color.ORANGE, Color.BLACK);
                digits[i].setFill(Color.BLACK);
            }
            if(i==1)
            {
                bg = createBackground(Color.BLACK, Color.BLACK);
                digits[i].setFill(Color.WHITE);
            }
            if(i==2)
            {
                bg = createScoreBackground(Color.DARKBLUE, Color.BLACK);
                digits[i].setFill(Color.WHITE);
            }
            digitsGroup[i] = new Group(bg,digits[i]);
        }
    }

    private Rectangle createBackground(Color fill, Color stroke) {
        Rectangle bg = new Rectangle(boxWidth*scale, boxHeight*scale, fill );
        bg.setStroke(stroke);
        bg.setStrokeWidth(1);
        //bg.setEffect(new Lighting());
        return bg;
    }
    private Rectangle createScoreBackground(Color fill,Color stroke)
    {
        Rectangle bg = new Rectangle(boxWidth*scale*8.2, boxHeight*scale, fill );
        bg.setStroke(stroke);
        bg.setStrokeWidth(1);
        //bg.setEffect(new Lighting());
        return bg;
    }

    public void refreshDigits(String number){
        for(int i=0; i<2; i++)
        {
            digits[i].setText(number.substring(i,i+1));
        }
    }
    public void refreshScore(String sNumber){
        digits[2].setText(sNumber);

    }
}
