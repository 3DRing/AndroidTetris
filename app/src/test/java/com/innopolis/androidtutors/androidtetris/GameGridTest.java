package com.innopolis.androidtutors.androidtetris;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 30.09.2016.
 */

public class GameGridTest {

    @Test(expected = IllegalArgumentException.class)
    public void initializationExceptionTest() {
        GameGrid grid = new GameGrid(0, -1);
    }

    @Test
    public void initializationTest() {
        GameGrid grid = new GameGrid(20, 10);
        CELL_STATE[][] states = grid.getStatesAndFigure();
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                assertTrue(states[i][j] == CELL_STATE.EMPTY);
            }
        }
    }

    @Test
    public void addSimpleFigureTest() {
        int height = 20;    // can be modified
        int width = 14;     // can be modified
        GameGrid grid = new GameGrid(height, width);

        CELL_STATE stateForFigure = grid.getCellStateUsedForFigure();

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                grid.addFigure(new SimpleFigure(), new GameGrid.Point(x, y));
                CELL_STATE[][] states = grid.getStatesAndFigure();
                for (int i = 0; i < grid.getHeight(); i++) {
                    for (int j = 0; j < grid.getWidth(); j++) {
                        if (i == y && j == x) {
                            assertTrue(states[i][j] == stateForFigure);
                        } else {
                            assertTrue(states[i][j] == CELL_STATE.EMPTY);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void tFigureToStringTest(){
        GameGrid grid = new GameGrid(4,4);
        grid.addFigure(new TFigure(), new GameGrid.Point(1,2));
        assertTrue(grid.toString().equals("_###\n__#_\n__#_\n____"));
    }

    private void moveFigure(GameGrid grid, int initialX, int initialY, int horizontalOffset, int h) {
        CELL_STATE stateForFigure = grid.getCellStateUsedForFigure();
        CELL_STATE[][] states = grid.getStatesAndFigure();
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (i == initialY + h) {
                    if (j == initialX + horizontalOffset + 1) {
                        assertTrue(states[i][j] == stateForFigure);
                        continue;
                    }
                }
                if (i == initialY + h - 1) {
                    if (j == initialX + horizontalOffset + 1) {
                        assertTrue(states[i][j] == stateForFigure);
                        continue;
                    }
                }
                if (i == initialY + h - 2) {
                    if (j == initialX + horizontalOffset || j == initialX + horizontalOffset + 1 || j == initialX + horizontalOffset + 2) {
                        assertTrue(states[i][j] == stateForFigure);
                        continue;
                    }
                }
                assertTrue(states[i][j] == CELL_STATE.EMPTY);
            }
        }
    }

    @Test
    public void moveTFigureTest() {
        int height = 30;    // can be modified
        int width = 10;     // can be modified
        GameGrid grid = new GameGrid(height, width);
        BaseFigure figure = new TFigure();
        int initialX = 0;
        int initialY = figure.getHeight() - 1;
        GameGrid.Point position = new GameGrid.Point(initialX, initialY);

        grid.addFigure(figure, position);
        for (int h = 0; h < height - initialY; h++) {
            int horizontalOffset;
            for (horizontalOffset = 0; horizontalOffset < width - figure.getWidth(); horizontalOffset++) {
                moveFigure(grid, initialX, initialY, horizontalOffset, h);
                try {
                    grid.moveFigureRight();
                } catch (OutGridException e) {
                    fail();
                }
            }
            for (; horizontalOffset > 0; horizontalOffset--) {
                moveFigure(grid, initialX, initialY, horizontalOffset, h);
                try {
                    grid.moveFigureLeft();
                } catch (OutGridException e) {
                    fail();
                }
            }
            grid.moveDown();
        }
    }

    @Test
    public void newCheckAndLandingTest(){
        GameGrid grid = new GameGrid(4,4);
        grid.setChecker(new FigureChecker() {
            @Override
            public boolean end(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
                return false;
            }

            @Override
            public boolean outLeft(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
                return false;
            }

            @Override
            public boolean outRight(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
                return false;
            }

            @Override
            public boolean landed(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
                return true;
            }

            @Override
            public int[] canBeErased(Grid grid) {
                return new int[0];
            }
        });
        grid.addFigure(new TFigure(), new GameGrid.Point(0,2));

        assertFalse(grid.moveDown());
        assertTrue(grid.toString().equals("###_\n_#__\n_#__\n____"));
    }
}
