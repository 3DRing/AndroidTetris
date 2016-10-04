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
    private SquaredRecyclerView rv;

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
    public void initialize(int width) {
        this.adapter = new SquaredAdapter(context);

        RecyclerView.LayoutParams params =
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.rv = new SquaredRecyclerView(context);
        this.rv.setLayoutParams(params);

        NotScrollableGridLayoutManager glm = new NotScrollableGridLayoutManager(container.getContext(),width);
        rv.setLayoutManager(glm);

        rv.setBackgroundColor(Color.CYAN);

        container.addView(rv);
        rv.setAdapter(adapter);
    }
}
