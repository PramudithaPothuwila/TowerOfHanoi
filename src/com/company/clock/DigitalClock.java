package com.company.clock;

import javafx.geometry.VPos;
import javafx.scene.Group;
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
    private Text[] digits = new Text[2];
    private Group[] digitsGroup = new Group[2];
    

    public DigitalClock()
    {
        configureDigits();
        configureHbox();
        getChildren().add(hBox);
    }

    private void configureHbox() {
        hBox.getChildren().addAll(digitsGroup);
        hBox.setSpacing(2);
    }

    private void configureDigits() {
        for(int i = 0; i< digits.length;i++)
        {
            digits[i] = new Text("0");
            digits[i].setFont(FONT);
            digits[i].setTextOrigin(VPos.TOP);
            digits[i].setLayoutY(-10);
            Rectangle bg = null;
            if(i==0)
            {
                bg = createBackground(Color.ANTIQUEWHITE, Color.BLACK);
                digits[i].setFill(Color.BLACK);
            }
            if(i==1)
            {
                bg = createBackground(Color.RED, Color.BLACK);
                digits[i].setFill(Color.WHITE);
            }
            digitsGroup[i] = new Group(bg,digits[i]);
        }
    }

    private Rectangle createBackground(Color fill, Color stroke) {
        Rectangle bg = new Rectangle(boxWidth*scale, boxHeight*scale, fill );
        bg.setStroke(stroke);
        bg.setStrokeWidth(3);
        bg.setEffect(new Lighting());
        return bg;
    }

    public void refreshDigits(String number){
        for(int i=0; i<digits.length; i++)
        {
            digits[i].setText(number.substring(i,i+1));
        }
    }
}
