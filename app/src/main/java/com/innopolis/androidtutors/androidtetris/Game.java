package com.innopolis.androidtutors.androidtetris;

import com.innopolis.androidtutors.androidtetris.grid_mechanics.FigureChecker;
import com.innopolis.androidtutors.androidtetris.grid_mechanics.GameGrid;
import com.innopolis.androidtutors.androidtetris.representation.CELL_STATE;
import com.innopolis.androidtutors.androidtetris.representation.UIGrid;

/**
 * Created by Сергей on 30.09.2016.
 */

public class Game {

    private TestTimer timer;
    private UIGrid uiGrid;

    private FigureGenerator generator;
    private GameGrid grid;

    public Game(UIGrid uiGrid, FigureGenerator generator){
        this.uiGrid = uiGrid;
        this.generator = generator;
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
