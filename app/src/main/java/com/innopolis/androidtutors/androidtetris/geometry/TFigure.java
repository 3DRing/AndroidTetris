package com.innopolis.androidtutors.androidtetris.geometry;

/**
 * Created by Сергей on 30.09.2016.
 */

public class TFigure extends BaseFigure {
    public TFigure() {
        super(new boolean[][]{  {false, true, false},
                                {false, true, false},
                                {true,  true, true}});
    }
}
