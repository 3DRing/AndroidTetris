package com.innopolis.androidtutors.androidtetris.geometry;

/**
 * Created by Сергей on 05.10.2016.
 */

public class RightCornerFigure extends BaseFigure {
    public RightCornerFigure() {
        super(new boolean[][]{  {false, false, true},
                                {true,  true,  true}});
    }
}
