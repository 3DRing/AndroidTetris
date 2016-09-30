package com.innopolis.androidtutors.androidtetris;

import com.innopolis.androidtutors.androidtetris.geometry.BaseFigure;
import com.innopolis.androidtutors.androidtetris.grid_logic.GameGrid;

/**
 * Created by Сергей on 30.09.2016.
 */
public interface FigureGenerator {
    BaseFigure getNextFigure();
    GameGrid.Point getInitialPosition(int width);
}
