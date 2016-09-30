package com.innopolis.androidtutors.androidtetris;

import java.util.Arrays;

/**
 * Created by Сергей on 30.09.2016.
 */

public class GameGrid {

    private CELL_STATE[][] state;
    private FigureChecker checker;
    private BaseFigure crtFigure;
    private Point crtFigurePosition;

    public GameGrid(int height, int width){
        initializeCellStates(height, width);
        initializeDefaultChecker();

        crtFigure = null;
    }

    private void initializeDefaultChecker() {
        checker = new FigureChecker() {
            @Override
            public boolean end(GameGrid grid, BaseFigure figure, Point figurePosition) {
                return false;
            }

            @Override
            public boolean outLeft(GameGrid grid, BaseFigure figure, Point figurePosition) {
                return false;
            }

            @Override
            public boolean outRight(GameGrid grid, BaseFigure figure, Point figurePosition) {
                return false;
            }

            @Override
            public boolean landed(GameGrid grid, BaseFigure figure, Point figurePosition) {
                return false;
            }
        };
    }

    private void initializeCellStates(int height, int width){
        state = new CELL_STATE[height][width];

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                state[i][j] = CELL_STATE.EMPTY;
            }
        }
    }

    public void setChecker(FigureChecker checker){
        this.checker = checker;
    }

    public void addFigure(BaseFigure figure, Point figurePosition){
        if(figure == null){
            throw new IllegalArgumentException("figure must be not null");
        }
        if(figurePosition == null){
            throw new IllegalArgumentException("figurePosition must be not null");
        }
        this.crtFigure = figure;
        this.crtFigurePosition = figurePosition;
    }

    public void moveFigureLeft() throws OutGridException {
        if(checker.outLeft(this,crtFigure,crtFigurePosition)){
            throw new OutGridException("Can't move figure left any further");
        }
        crtFigurePosition.setX(crtFigurePosition.getX() - 1);
    }

    public void moveFigureRight() throws OutGridException {
        if(checker.outRight(this, crtFigure, crtFigurePosition)){
            throw new OutGridException("Can't move figure right any further");
        }
        crtFigurePosition.setX(crtFigurePosition.getX() + 1);
    }

    public boolean moveDown(){
        if(checker.landed(this, crtFigure, crtFigurePosition)){
            mergeFigureToBuilding();
            crtFigure = null;
            return false;
        }
        crtFigurePosition.setY(crtFigurePosition.getY() + 1);
        return true;
    }

    public Point getGridPositionByFigurePosition(int figurePositionX, int figurePositionY){
        if(crtFigure == null){
            throw new IllegalStateException("Can't get grid position by figure because figure is null");
        }

        // kinda check correctness of arguments (throws IllegalArgumentException)
        crtFigure.getBlockState(figurePositionY, figurePositionX);

        int offsetX = figurePositionX;

        int offsetY = crtFigure.getHeight() - figurePositionY; // offset to the left bottom block of the Figure

        return new Point(crtFigurePosition.getX() + offsetX, crtFigurePosition.getY() - offsetY);
    }

    private CELL_STATE[][] fullCopyCellState(CELL_STATE[][] state){
        CELL_STATE[][] newArray = new CELL_STATE[state.length][];
        for (int i = 0; i < state.length; i++) {
            newArray[i] = new CELL_STATE[state[i].length];
            for (int j = 0; j < state[i].length; j++) {
                newArray[i][j] = state[i][j];
            }
        }
        return newArray;
    }

    public CELL_STATE[][] getStatesAndFigure(){
        CELL_STATE[][] result = fullCopyCellState(state);
        mergeFigureToState(result, CELL_STATE.BLOCK);       // TODO possible to change CELL_STATE only for figure
        return result;
    }

    private void mergeFigureToState(CELL_STATE[][] state, CELL_STATE stateForBlock) {
        for (int i = 0; i < crtFigure.getHeight(); i++) {
            for (int j = 0; j < crtFigure.getWidth(); j++) {
                Point globalBlockPosition = getGridPositionByFigurePosition(j, i);
                boolean blockState = crtFigure.getBlockState(i, j);
                state[globalBlockPosition.getY()][globalBlockPosition.getX()] = blockState ? stateForBlock : CELL_STATE.EMPTY;
            }
        }
    }

    private void mergeFigureToBuilding(){
        mergeFigureToState(state, CELL_STATE.BLOCK);
    }

    public class Point{
        private int x;
        private int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void setX(int x){
            this.x = x;
        }

        public void setY(int y){
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
