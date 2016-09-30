package com.innopolis.androidtutors.androidtetris;

/**
 * Created by Сергей on 30.09.2016.
 */

public class TestTimer {
    private int tick;
    private TickListener listener;
    private boolean stopped;

    public TestTimer(TickListener listener){
        tick = 0;
        stopped = true;
        this.listener = listener;
    }

    private void doTick(){
        listener.tick();
        tick++;
    }

    public void start(){
        while (tick < 1000 || stopped){
            doTick();
        }
    }

    public void stop(){
        stopped = true;
    }

    public void setTickListener(TickListener listener){
        this.listener = listener;
    }

    public interface TickListener{
        void tick();
    }
}
