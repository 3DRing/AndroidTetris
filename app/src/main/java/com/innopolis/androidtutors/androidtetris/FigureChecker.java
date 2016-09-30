package com.innopolis.androidtutors.androidtetris;

/**
 * Created by Сергей on 30.09.2016.
 */
public interface FigureChecker {
    boolean end(GameGrid grid, BaseFigure figure, GameGrid.Point figurePosition);
    boolean outLeft(GameGrid grid, BaseFigure figure, GameGrid.Point figurePosition);
    boolean outRight(GameGrid grid, BaseFigure figure, GameGrid.Point figurePosition);
    boolean landed(GameGrid grid, BaseFigure figure, GameGrid.Point figurePosition);
}
