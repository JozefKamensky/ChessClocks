package com.example.jozefkamensky.chessclocks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textWhite;
    private ImageView imageWhite;

    private TextView textBlack;
    private ImageView imageBlack;

    private final long initialTime = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textWhite = (TextView) findViewById(R.id.whiteText);
        imageWhite = (ImageView) findViewById(R.id.whiteHalf);

        textBlack = (TextView) findViewById(R.id.blackText);
        imageBlack = (ImageView) findViewById(R.id.blackHalf);

//        turnPlayer1 = true;
//        timePlayer1 = timePlayer2 = 5 * 60 * 1000;
//        timer = null;

        final TimeModel tModel = ViewModelProviders.of(this).get(TimeModel.class);
        tModel.timeData.observe(this, new Observer<TimeData>() {
            @Override
            public void onChanged(@Nullable TimeData timeData) {
                if (timeData.isTurnPlayer1()){
                    textWhite.setText(timeData.getTimePlayer1());
                } else{
                    textBlack.setText(timeData.getTimePlayer2());
                }
            }
        });

        imageBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tModel.changePlayer();
            }
        });

        imageWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tModel.changePlayer();
            }
        });

        init(tModel);
        //showTimePicker();
    }

    private void init(TimeModel t){
        t.initTimeData(initialTime);
        imageBlack.setClickable(true);
        imageWhite.setClickable(false);
    }
}
