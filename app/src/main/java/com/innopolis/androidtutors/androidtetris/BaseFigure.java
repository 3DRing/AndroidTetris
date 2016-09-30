package com.innopolis.androidtutors.androidtetris;

/**
 * Base class for figures
 *
 * Created by Сергей on 30.09.2016.
 */
public abstract class BaseFigure {

    private boolean[][] blocks;

    public BaseFigure(boolean[][] blocks){
        checkFigureCorrect(blocks);
        this.blocks = blocks;
    }

    /**
     * Should be used in children's constructor
     * to check if given block's array meets requirements: all rows must have the same length
     *
     */
    private void checkFigureCorrect(boolean[][] blocks){
        int prevLength = blocks[0].length;
        for (int i = 1; i < blocks.length; i++) {
            if(blocks[i].length != prevLength){
                throw new IllegalStateException("Figure must consist of rows with the same length. Now: row "
                                                + (i - 1) + " = " + prevLength + ", row " + i + " = " + blocks[i].length);
            }
        }
    }

    public int getWidth(){
        return blocks[0].length;
    }

    public int getHeight(){
        return blocks.length;
    }

    /**
     * @param localWidthPosition position in an array's row
     * @param localHeightPosition position in an array's column
     * @return {@link CELL_STATE} of requested block in array
     */
    public boolean getBlockState(int localHeightPosition, int localWidthPosition){
        if(localWidthPosition < 0 || localWidthPosition > getWidth() - 1){
            throw new IllegalArgumentException("localWidthPosition must be less than width of figure (and not less than 0). Now width = "
                                                + getWidth() + ", localWidthPosition = " + localWidthPosition);
        }
        if(localHeightPosition < 0 || localHeightPosition > getHeight() - 1){
            throw new IllegalArgumentException("localHeightPosition must be less than height of figure (and not less than 0). Now height = "
                    + getHeight() + ", localHeightPosition = " + localHeightPosition);
        }
        return blocks[localHeightPosition][localWidthPosition];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                sb.append(blocks[i][j] ? "#" : "_");
            }
            if(i < blocks.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
