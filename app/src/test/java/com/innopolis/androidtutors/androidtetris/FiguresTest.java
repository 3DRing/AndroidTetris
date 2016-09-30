package com.innopolis.androidtutors.androidtetris;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link BaseFigure}
 * and all its' implementations
 *
 * Created by Сергей on 30.09.2016.
 */

public class FiguresTest {

    @Test
    public void baseFigureStatesTest(){
        BaseFigure baseFigure = new BaseFigure(new boolean[][]{ {true, false, false},
                                                                {true, true, true}}){
        };

        assertTrue(baseFigure.toString().equals("#__\n###"));
        assertTrue(baseFigure.getWidth() == 3);
        assertTrue(baseFigure.getHeight() == 2);

        boolean[][] blockIsHere = { {true, false, false},
                                    {true, true,  true}};

        for (int i = 0; i < baseFigure.getHeight(); i++) {
            for (int j = 0; j < baseFigure.getWidth(); j++) {
                assertTrue(baseFigure.getBlockState(i, j) == blockIsHere[i][j]);
            }
        }
    }

    @Test (expected = IllegalStateException.class)
    public void baseFigureInitializationExceptionTest(){
        BaseFigure baseFigure = new BaseFigure(new boolean[][]{ {true, false, false},
                                                                    {false, false}}){
        };
    }

    @Test (expected = IllegalArgumentException.class)
    public void baseFigureGetBlockExceptionByHeightLess0Test(){
        BaseFigure baseFigure = new BaseFigure(new boolean[][]{ {true, false, false},
                {true, true, true}}){
        };
        boolean state = baseFigure.getBlockState(-1, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void baseFigureGetBlockExceptionByHeightTest(){
        BaseFigure baseFigure = new BaseFigure(new boolean[][]{ {true, false, false},
                {true, true, true}}){
        };
        boolean state = baseFigure.getBlockState(2, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void baseFigureGetBlockExceptionByWidthLess0Test(){
        BaseFigure baseFigure = new BaseFigure(new boolean[][]{ {true, false, false},
                {true, true, true}}){
        };
        boolean state = baseFigure.getBlockState(0, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void baseFigureGetBlockExceptionByWidthTest(){
        BaseFigure baseFigure = new BaseFigure(new boolean[][]{ {true, false, false},
                {true, true, true}}){
        };
        boolean state = baseFigure.getBlockState(0, 3);
    }

    @Test
    public void simpleFigureStatesTest(){
        BaseFigure simpleFigure = new SimpleFigure();

        assertTrue(simpleFigure.getHeight() == 1);
        assertTrue(simpleFigure.getWidth() == 1);
        assertTrue(simpleFigure.getBlockState(0,0));
        assertTrue(simpleFigure.toString().equals("#"));
    }

    @Test
    public void tFigureStateTest(){
        BaseFigure simpleFigure = new TFigure();

        assertTrue(simpleFigure.getHeight() == 3);
        assertTrue(simpleFigure.getWidth() == 3);
        assertTrue(simpleFigure.getBlockState(0,0));
        assertTrue(simpleFigure.getBlockState(0,1));
        assertTrue(simpleFigure.getBlockState(0,2));
        assertFalse(simpleFigure.getBlockState(1,0));
        assertTrue(simpleFigure.getBlockState(1,1));
        assertFalse(simpleFigure.getBlockState(1,2));
        assertFalse(simpleFigure.getBlockState(2,0));
        assertTrue(simpleFigure.getBlockState(2,1));
        assertFalse(simpleFigure.getBlockState(2,2));
        assertTrue(simpleFigure.toString().equals("###\n_#_\n_#_"));
    }
}
