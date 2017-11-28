package com.example.jozefkamensky.chessclocks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int timePlayer1;
    private int timePlayer2;

    private boolean turnPlayer1;

    private TextView textWhite;
    private ImageView imageWhite;

    private TextView textBlack;
    private ImageView imageBlack;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textWhite = (TextView) findViewById(R.id.whiteText);
        imageWhite = (ImageView) findViewById(R.id.whiteHalf);

        textBlack = (TextView) findViewById(R.id.blackText);
        imageBlack = (ImageView) findViewById(R.id.blackHalf);

        turnPlayer1 = true;
        timePlayer1 = timePlayer2 = 5 * 60 * 1000;
        timer = null;

        imageBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BLACK", "onClick: click on black half!");
                turnPlayer1 = true;
                textWhite.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                textBlack.setPaintFlags(0);
                imageBlack.setClickable(false);
                imageWhite.setClickable(true);
                changeTimer();
            }
        });

        imageWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("WHITE", "onClick: click on white half!");
                turnPlayer1 = false;
                textBlack.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                textWhite.setPaintFlags(0);
                imageWhite.setClickable(false);
                imageBlack.setClickable(true);
                changeTimer();
            }
        });

        init();
        //showTimePicker();
    }

    /*private void showTimePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle(R.string.selectTimeTitle)
                .setItems(R.array.timeValues, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        Dialog d = builder.create();
        d.show();
    }*/

    private void changeTimer(){
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
    }

    private CountDownTimer timerForPlayer1() {
        Log.d("TIMER", "New timer for Player1!");
       return new CountDownTimer(timePlayer1, 100) {
            @Override
            public void onTick(long l) {
                timePlayer1 -= 100;
                updateTimeView();
            }

            @Override
            public void onFinish() {
                endGame();
            }
        };
    }

    private CountDownTimer timerForPlayer2() {
        Log.d("TIMER", "New timer for Player2!");
        return new CountDownTimer(timePlayer2, 100) {
            @Override
            public void onTick(long l) {
                timePlayer2 -= 100;
                updateTimeView();
            }

            @Override
            public void onFinish() {
                endGame();
            }
        };
    }

    private void updateTimeView(){
        if (turnPlayer1){
            textWhite.setText(getTimeAsString(timePlayer1));
        } else{
            textBlack.setText(getTimeAsString(timePlayer2));
        }
    }

    private String getTimeAsString(long time){
        long minutes = time / 60000;
        long seconds = (time - minutes * 60000) / 1000;

        return String.format(Locale.US,"%02d", minutes) + " : " + String.format(Locale.US, "%02d", seconds);
    }

    private void endGame(){
        timer = null;
        imageWhite.setClickable(false);
        imageBlack.setClickable(false);
    }

    private void init(){
        updateTimeView();
        turnPlayer1 = false;
        updateTimeView();
        turnPlayer1 = true;
        imageBlack.setClickable(true);
        imageWhite.setClickable(false);
    }
}
