package com.innopolis.androidtutors.androidtetris;

import com.innopolis.androidtutors.androidtetris.grid_logic.FigureChecker;
import com.innopolis.androidtutors.androidtetris.grid_logic.GameGrid;
import com.innopolis.androidtutors.androidtetris.grid_logic.OutGridException;
import com.innopolis.androidtutors.androidtetris.representation.UIGrid;

/**
 * Created by Сергей on 30.09.2016.
 */

public class Game {

    private int DEFAULT_HEIGHT = 10;
    private int DEFAULT_WIDTH = 10;

    private TestTimer timer;
    private UIGrid uiGrid;

    private FigureGenerator generator;
    private GameGrid grid;

    private boolean playing;

    private EndListener endListener;

    private enum MOVE_DIRECTION {NONE, LEFT, RIGHT}
    private MOVE_DIRECTION direction;

    public Game(UIGrid uiGrid, FigureGenerator generator){
        initializeGrid(null);
        initializeUIGrid(uiGrid);
        initializeFigureGenerator(generator);
        initializeTimer();

        playing = false;
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
    }

    private void initializeFigureGenerator(FigureGenerator generator){
        this.generator = generator;
    }

    private void initializeTimer(){
        this.timer = new TestTimer(new TestTimer.TickListener() {
            @Override
            public void tick() {
                // main logic of moving figures
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
        timer.start();
    }

    public void stop(){
        timer.stop();
        playing = false;
    }

    public void moveLeft(){
        direction = MOVE_DIRECTION.LEFT;
    }

    public void moveRight(){
        direction = MOVE_DIRECTION.RIGHT;
    }
}
