package com.innopolis.androidtutors.androidtetris.grid_logic;

import com.innopolis.androidtutors.androidtetris.geometry.BaseFigure;
import com.innopolis.androidtutors.androidtetris.representation.CELL_STATE;

/**
 * Created by Сергей on 05.10.2016.
 */

public class FigureCheckerImpl implements FigureChecker {
    @Override
    public boolean end(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
        return figurePosition.getY() - figure.getHeight() <= 0;
    }

    @Override
    public boolean outLeft(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
        return figurePosition.getX() <= 0;
    }

    @Override
    public boolean outRight(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
        return (figurePosition.getX() + figure.getWidth()) > grid.getWidth() - 1;
    }

    @Override
    public boolean landed(Grid grid, BaseFigure figure, GameGrid.Point figurePosition) {
        boolean onTheGround = figurePosition.getY() == grid.getHeight() - 1;
        if(onTheGround){
            return onTheGround;
        }
        boolean onTheBuilding = false;
        for (int i = 0; i < figure.getWidth(); i++) {
            CELL_STATE crtState = grid.getState(figurePosition.getX() + i, figurePosition.getY() + 1);
            if(crtState != CELL_STATE.EMPTY && figure.getBlockState(figure.getHeight() - 1, i)){
                onTheBuilding = true;
                break;
            }
        }
        return onTheBuilding;
    }

    @Override
    public int[] canBeErased(Grid grid) {
        return new int[0];
    }
}
