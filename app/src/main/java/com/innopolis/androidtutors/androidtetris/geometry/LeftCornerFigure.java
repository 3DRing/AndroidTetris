package com.innopolis.androidtutors.androidtetris.geometry;

/**
 * Created by Сергей on 05.10.2016.
 */

public class LeftCornerFigure extends BaseFigure {
    public LeftCornerFigure() {
        super(new boolean[][]{  {true, false, false},
                                {true, true,  true}});
    }
}
