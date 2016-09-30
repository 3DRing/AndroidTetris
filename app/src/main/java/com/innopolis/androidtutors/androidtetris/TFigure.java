package com.innopolis.androidtutors.androidtetris;

/**
 * Created by Сергей on 30.09.2016.
 */

public class TFigure extends BaseFigure {
    public TFigure() {
        super(new boolean[][]{  {true, true, true},
                                {false,true,false},
                                {false,true,false}});
    }
}
