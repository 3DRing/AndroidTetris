package com.innopolis.androidtutors.androidtetris.gameplay_logic;

import com.innopolis.androidtutors.androidtetris.geometry.BaseFigure;
import com.innopolis.androidtutors.androidtetris.geometry.BlockFigure;
import com.innopolis.androidtutors.androidtetris.geometry.LeftCornerFigure;
import com.innopolis.androidtutors.androidtetris.geometry.LineFigure;
import com.innopolis.androidtutors.androidtetris.geometry.RightCornerFigure;
import com.innopolis.androidtutors.androidtetris.geometry.SimpleFigure;
import com.innopolis.androidtutors.androidtetris.geometry.TFigure;
import com.innopolis.androidtutors.androidtetris.grid_logic.GameGrid;

import java.util.Random;

/**
 * Created by Сергей on 05.10.2016.
 */

public class RandomFigureGenerator implements FigureGenerator {

    private Random rand;

    public RandomFigureGenerator(){
        rand = new Random();
    }

    @Override
    public BaseFigure getNextFigure() {
        BaseFigure crtFigure;
        int crtValue = rand.nextInt() % 5;
        switch (crtValue){
            case 0:
                crtFigure = new BlockFigure();
                break;
            case 1:
                crtFigure = new TFigure();
                break;
            case 2:
                crtFigure = new LeftCornerFigure();
                break;
            case 3:
                crtFigure = new RightCornerFigure();
                break;
            case 4:
                crtFigure = new LineFigure();
                break;
            default:
                crtFigure = new SimpleFigure();
                break;
        }
        return crtFigure;
    }

    @Override
    public GameGrid.Point getInitialPosition(int width) {
        return new GameGrid.Point(width / 2, 0);
    }
}
