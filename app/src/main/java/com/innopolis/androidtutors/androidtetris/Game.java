package com.innopolis.androidtutors.androidtetris;

/**
 * Created by Сергей on 30.09.2016.
 */

public class Game {

    private TestTimer timer;
    private UIGrid grid;

    private FigureGenerator generator;
    private CELL_STATE[] crtState;

    private FigureChecker checker;

    public Game(UIGrid grid){
        this.grid = grid;
        this.timer = new TestTimer();
        timer.setTickListener(new TestTimer.TickListener() {
            @Override
            public void tick() {

            }
        });
    }

    private void checkReady(){
        if(this.grid == null){
            throw new IllegalStateException("Game hasn't been initialized, grid is null");
        }
    }

    public void play(){
        checkReady();
    }
}
