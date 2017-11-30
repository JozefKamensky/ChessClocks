package com.example.jozefkamensky.chessclocks;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Locale;

/**
 * Created by jozef.kamensky on 30.11.2017.
 */

public class TimeData {

    private long timePlayer1;
    private long timePlayer2;

    private boolean turnPlayer1;

    private CountDownTimer timer;

    public TimeData(long time){
        init(time);
    }

    public void init(long time){
        timePlayer1 = timePlayer2 = time;
        timer = null;
        turnPlayer1 = true;
    }

    public void changeTimer(){
        if(turnPlayer1){
            if (timer != null) timer.cancel();
            timer = timerForPlayer1();
            timer.start();
        }
        else {
            if (timer != null) timer.cancel();
            timer = timerForPlayer2();
            timer.start();
        }
        turnPlayer1 = !turnPlayer1;
    }

    public boolean isTurnPlayer1(){
        return turnPlayer1;
    }

    public String getTimePlayer1(){
        return getTimeAsString(timePlayer1);
    }

    public String getTimePlayer2(){
        return getTimeAsString(timePlayer2);
    }

    private CountDownTimer timerForPlayer1() {
        Log.d("TIMER", "New timer for Player1!");
        return new CountDownTimer(timePlayer1, 100) {
            @Override
            public void onTick(long l) {
                timePlayer1 -= 100;
            }

            @Override
            public void onFinish() {
            }
        };
    }

    private CountDownTimer timerForPlayer2() {
        Log.d("TIMER", "New timer for Player2!");
        return new CountDownTimer(timePlayer2, 100) {
            @Override
            public void onTick(long l) {
                timePlayer2 -= 100;
            }

            @Override
            public void onFinish() {
            }
        };
    }

    private String getTimeAsString(long time){
        long minutes = time / 60000;
        long seconds = (time - minutes * 60000) / 1000;

        return String.format(Locale.US,"%02d", minutes) + " : " + String.format(Locale.US, "%02d", seconds);
    }
}
