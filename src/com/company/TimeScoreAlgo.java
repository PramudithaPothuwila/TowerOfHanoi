package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class TimeScoreAlgo {
    private int k;
    private Timer t;
    private int score=100;

    public void timeOutput()
    {
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                test();
            }
        };
        t = new Timer();
        t.schedule(timerTask,1000,1000);
    }
    private void test()
    {
        k++;
        if(k==20){
            score = score-5;
        }
        System.out.println("K :"+ k + "Score :"+score);
        if(k==120)
        {
            t.cancel();
            System.out.println("STOP!!");
        }
    }
}
