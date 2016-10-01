package com.innopolis.androidtutors.androidtetris.gameplay_logic;

import com.innopolis.androidtutors.androidtetris.geometry.BaseFigure;
import com.innopolis.androidtutors.androidtetris.geometry.SimpleFigure;
import com.innopolis.androidtutors.androidtetris.grid_logic.GameGrid;

/**
 * Created by Сергей on 02.10.2016.
 */

public class SimpleFigureGenerator implements FigureGenerator {
    @Override
    public BaseFigure getNextFigure() {
        return new SimpleFigure();
    }

    @Override
    public GameGrid.Point getInitialPosition(int width) {
        return new GameGrid.Point(width / 2, 0);
    }
}
