package com.innopolis.androidtutors.androidtetris;

import android.content.Context;
import android.widget.Chronometer;

import com.innopolis.androidtutors.androidtetris.gameplay_logic.EndListener;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.FigureGenerator;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.GameResult;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.OnTickListener;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.RandomFigureGenerator;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.SimpleFigureGenerator;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.Tick;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.Ticker;
import com.innopolis.androidtutors.androidtetris.grid_logic.FigureChecker;
import com.innopolis.androidtutors.androidtetris.grid_logic.FigureCheckerImpl;
import com.innopolis.androidtutors.androidtetris.grid_logic.GameGrid;
import com.innopolis.androidtutors.androidtetris.grid_logic.OutGridException;
import com.innopolis.androidtutors.androidtetris.representation.UIGrid;

/**
 * The main class responsible for the gameplay
 *
 * Created by Сергей on 30.09.2016.
 */

public class Game {

    private Context context;

    private int DEFAULT_HEIGHT = 10;
    private int DEFAULT_WIDTH = 10;

    private Tick tick;
    private UIGrid uiGrid;

    private FigureGenerator generator;
    private GameGrid grid;

    private boolean playing;

    private EndListener endListener;

    private enum MOVE_DIRECTION {NONE, LEFT, RIGHT}
    private MOVE_DIRECTION direction;

    public Game(Context context, UIGrid uiGrid){
        this.context = context;
        initializeGrid(new FigureCheckerImpl());
        initializeUIGrid(uiGrid);
        initializeFigureGenerator(new RandomFigureGenerator());
        initializeTick();

        playing = false;
    }

    public void setFigureGenerator(FigureGenerator generator){
        this.generator = generator;
    }

    public void setEndListener(EndListener listener){
        this.endListener = listener;
    }

    /**
     * @param customChecker can be null
     */
    private void initializeGrid(FigureChecker customChecker){
        this.grid = new GameGrid(DEFAULT_HEIGHT, DEFAULT_WIDTH);
        if(customChecker != null) {
            grid.setChecker(customChecker);
        }
    }

    private void initializeUIGrid(UIGrid uiGrid){
        this.uiGrid = uiGrid;
        this.uiGrid.initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    private void initializeFigureGenerator(FigureGenerator generator){
        this.setFigureGenerator(generator);
    }

    private void initializeTick(){
        this.tick = new Ticker();
        this.tick.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick() {
                if(!grid.isFigureExist()){
                    grid.addFigure(generator.getNextFigure(), generator.getInitialPosition(grid.getWidth()));
                }else {
                    switch (direction){
                        case LEFT:
                            try {
                                grid.moveFigureLeft();
                            } catch (OutGridException e) {
                                // just do nothing
                            }
                            break;
                        case RIGHT:
                            try {
                                grid.moveFigureRight();
                            } catch (OutGridException e) {
                                // just do nothing
                            }
                            break;
                        default:
                            // just do nothing
                            break;
                    }
                    if(!grid.moveDown()){
                        if(grid.isEnded()){
                            endListener.end(new GameResult()); // TODO game results
                        }
                    }
                }
                direction = MOVE_DIRECTION.NONE;

                uiGrid.update(grid.getStatesAndFigure());
            }
        });

    }

    private void checkReady(){
        if(this.grid == null){
            throw new IllegalStateException("Game hasn't been initialized, grid is null");
        }
    }

    public void start(){
        checkReady();
        playing = true;

        tick.start();
    }

    public void stop(){
        tick.stop();
        playing = false;
    }

    public void moveLeft(){
        direction = MOVE_DIRECTION.LEFT;
    }

    public void moveRight(){
        direction = MOVE_DIRECTION.RIGHT;
    }
}
