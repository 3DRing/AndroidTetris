package com.innopolis.androidtutors.androidtetris.grid_logic;

import com.innopolis.androidtutors.androidtetris.representation.CELL_STATE;

/**
 * Interface that gives information about a grid
 *
 * Created by Сергей on 30.09.2016.
 */
public interface Grid {
    int getWidth();
    int getHeight();
    CELL_STATE getState(int positionX, int positionY);
    CELL_STATE getState(GameGrid.Point point);
}
