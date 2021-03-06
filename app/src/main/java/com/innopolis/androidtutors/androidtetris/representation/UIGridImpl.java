package com.innopolis.androidtutors.androidtetris.representation;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Сергей on 02.10.2016.
 */

public class UIGridImpl implements UIGrid{

    private SquaredAdapter adapter;
    private RecyclerView rv;

    private Context context;
    private ViewGroup container;

    public UIGridImpl(Context context, ViewGroup container){
        this.context = context;
        this.container = container;
    }

    @Override
    public void update(CELL_STATE[][] grid) {
        adapter.update(grid);
    }

    @Override
    public void initialize(int widthLength, int heightLength) {
        this.adapter = new SquaredAdapter(context);

        RecyclerView.LayoutParams params =
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.rv = new RecyclerView(context);
        this.rv.setLayoutParams(params);

        NotScrollableGridLayoutManager glm = new NotScrollableGridLayoutManager(container.getContext(),widthLength);
        rv.setLayoutManager(glm);

        rv.setBackgroundColor(Color.LTGRAY);

        container.addView(rv);
        rv.setAdapter(adapter);
    }
}
