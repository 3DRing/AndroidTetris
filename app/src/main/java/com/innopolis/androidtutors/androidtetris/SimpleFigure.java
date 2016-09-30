package com.innopolis.androidtutors.androidtetris;

/**
 * Simple implementation of {@link BaseFigure}
 * Just one block
 *
 * Created by Сергей on 30.09.2016.
 */

public class SimpleFigure extends BaseFigure{
    public SimpleFigure(){
        super(new boolean[][]{{true}});
    }
}
