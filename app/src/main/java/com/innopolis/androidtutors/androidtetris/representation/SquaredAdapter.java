package com.innopolis.androidtutors.androidtetris.representation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.innopolis.androidtutors.androidtetris.grid_logic.GameGrid;

/**
 * Created by Сергей on 02.10.2016.
 */
public class SquaredAdapter extends RecyclerView.Adapter<SquaredAdapter.SquareViewHolder> {

    private Context context;
    private CELL_STATE[][] dataSet;

    public SquaredAdapter(Context context) {
        this.context = context;
        dataSet = null;
    }

    @Override
    public SquareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new Button(context);

        SquareViewHolder holder = new SquareViewHolder(view);
        return holder;
    }

    private GameGrid.Point positionToPoint(int position) {
        return new GameGrid.Point(position % dataSet[0].length ,position / dataSet[0].length);
    }

    @Override
    public void onBindViewHolder(SquareViewHolder holder, int position) {
        GameGrid.Point point = positionToPoint(position);
        CELL_STATE crtState = dataSet[point.getY()][point.getX()];
        switch (crtState){
            case EMPTY:
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                break;
            case BLOCK:
                holder.itemView.setBackgroundColor(Color.BLUE);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(dataSet == null){
            return 0;
        }else {
            return dataSet.length * dataSet[0].length;
        }
    }

    public void update(CELL_STATE[][] grid) {
        if(dataSet == null) {
            this.dataSet = grid;
        }else{
            for (int i = 0; i < dataSet.length; i++) {
                for (int j = 0; j < dataSet[i].length; j++) {
                    dataSet[i][j] = grid[i][j];
                }
            }
        }

        this.notifyDataSetChanged();
    }

    public class SquareViewHolder extends RecyclerView.ViewHolder {

        public SquareViewHolder(View itemView) {
            super(itemView);
        }
    }

}
