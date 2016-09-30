package com.innopolis.androidtutors.androidtetris;

/**
 * Interface that gives information about a grid
 *
 * Created by Сергей on 30.09.2016.
 */
public interface Grid {
    int getWidth();
    int getHeight();
    CELL_STATE getState(int positionX, int positionY);
}
