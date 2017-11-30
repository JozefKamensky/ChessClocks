package com.example.jozefkamensky.chessclocks;

/**
 * Created by jozef.kamensky on 30.11.2017.
 */

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class TimeModel extends ViewModel {
    public MutableLiveData<TimeData> timeData;

    public void changePlayer(){
        timeData.getValue().changeTimer();
    }

    public void initTimeData(long time){
        timeData = new MutableLiveData<>();
        timeData.getValue().init(time);
    }
}
