package com.innopolis.androidtutors.androidtetris.gameplay_logic;

import android.os.CountDownTimer;

/**
 * Created by Сергей on 30.09.2016.
 */

public class Ticker implements Tick{

    private final long DEFAULT_COUNT_DOWN_INTERVAL = 1000l;
    private final long DEFAULT_MILLIS_IN_FUTURE = 10000l;

    private CountDownTimer timer;
    private OnTickListener tickListener;

    public Ticker() {
        timer = new CountDownTimer(DEFAULT_MILLIS_IN_FUTURE, DEFAULT_COUNT_DOWN_INTERVAL){
            @Override
            public void onTick(long millisUntilFinished) {
                tickListener.onTick();
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void stop() {
        timer.cancel();
    }

    @Override
    public void setOnTickListener(OnTickListener listener) {
        this.tickListener = listener;
    }
}
