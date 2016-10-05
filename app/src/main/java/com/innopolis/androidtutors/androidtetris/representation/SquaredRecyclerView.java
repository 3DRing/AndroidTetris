package com.innopolis.androidtutors.androidtetris.representation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Сергей on 02.10.2016.
 */
public class SquaredRecyclerView extends RecyclerView {
    private int widthLength;
    private int heightLength;

    public SquaredRecyclerView(Context context, int widthLength, int heightLength) {
        super(context);
        this.widthLength = widthLength;
        this.heightLength = heightLength;
    }

    public SquaredRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquaredRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int width = View.MeasureSpec.getSize(widthSpec);
        int height = View.MeasureSpec.getSize(heightSpec);
        int unit;
        if(widthLength < heightLength){
            unit = width / widthLength;
        }else{
            unit = height / heightLength;
        }
        setMeasuredDimension(widthLength * unit, heightLength * unit);

    }
}