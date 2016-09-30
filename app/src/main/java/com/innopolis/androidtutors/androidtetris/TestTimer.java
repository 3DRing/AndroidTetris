package com.innopolis.androidtutors.androidtetris;

/**
 * Created by Сергей on 30.09.2016.
 */

public class TestTimer {
    private int tick;
    private TickListener listener;

    public TestTimer(){
        tick = 0;
    }

    private void doTick(){
        tick++;
    }

    public void start(){
        while (tick < 100){
            doTick();
        }
    }

    public void setTickListener(TickListener listener){
        this.listener = listener;
    }

    public interface TickListener{
        void tick();
    }
}
