package com.innopolis.androidtutors.androidtetris;

/**
 * Interface that is responsible for checking conditions for {@link BaseFigure}
 *
 * Created by Сергей on 30.09.2016.
 */
public interface FigureChecker {
    /**
     *
     * Checks the end of the game
     *
     * @param grid {@link Grid}, contains full grid and current states for each cell
     * @param figure {@link BaseFigure} that should be checked
     * @param figurePosition Position of left bottom (!) corner of a figure
     * @return true when there is no cell for next coming figure, false otherwise
     */
    boolean end(Grid grid, BaseFigure figure, GameGrid.Point figurePosition);

    /**
     * Checks if moving left is available
     *
     * @param grid {@link Grid}, contains full grid and current states for each cell
     * @param figure {@link BaseFigure} that should be checked
     * @param figurePosition Position of left bottom (!) corner of a figure
     * @return true if figure, trying to move left, will be out of grid, false otherwise
     */
    boolean outLeft(Grid grid, BaseFigure figure, GameGrid.Point figurePosition);

    /**
     * Checks if moving right is available
     *
     * @param grid {@link Grid}, contains full grid and current states for each cell
     * @param figure {@link BaseFigure} that should be checked
     * @param figurePosition Position of left bottom (!) corner of a figure
     * @return true if figure, trying to move right, will be out of grid, false otherwise
     */
    boolean outRight(Grid grid, BaseFigure figure, GameGrid.Point figurePosition);

    /**
     * Checks if figure achieve grid's bottom or top of the 'building'
     *
     * @param grid {@link Grid}, contains full grid and current states for each cell
     * @param figure {@link BaseFigure} that should be checked
     * @param figurePosition Position of left bottom (!) corner of a figure
     * @return true if figure won't be able to move down on the next move
     *          because of the 'building' or bottom of the gird. False otherwise
     */
    boolean landed(Grid grid, BaseFigure figure, GameGrid.Point figurePosition);

    /**
     * Checks the 'building', if some floors can be erased
     *
     * @param grid {@link Grid}, contains full grid and current states for each cell
     * @return 0-length array if nothing to erase, or array of numbers of grid's rows that can be erased
     */
    int[] canBeErased(Grid grid);
}
