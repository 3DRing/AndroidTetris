package com.innopolis.androidtutors.androidtetris.representation;

/**
 * Interface for UI class
 *
 * Created by Сергей on 30.09.2016.
 */
public interface UIGrid {
    /**
     * Updates user interface
     *
     * @param grid 2dim-array of {@link CELL_STATE}s that
     *             should be translated in some user-friendly form
     */
    void update(CELL_STATE[][] grid);
}
