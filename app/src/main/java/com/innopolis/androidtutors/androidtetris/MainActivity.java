package com.innopolis.androidtutors.androidtetris;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.innopolis.androidtutors.androidtetris.representation.CELL_STATE;
import com.innopolis.androidtutors.androidtetris.representation.NotScrollableGridLayoutManager;
import com.innopolis.androidtutors.androidtetris.representation.SquaredAdapter;
import com.innopolis.androidtutors.androidtetris.representation.SquaredRecyclerView;
import com.innopolis.androidtutors.androidtetris.representation.UIGrid;
import com.innopolis.androidtutors.androidtetris.representation.UIGridImpl;

/**
 * Created by Сергей on 03.10.2016.
 */

public class MainActivity extends Activity {

    private ViewGroup container;
    private UIGrid uiGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (ViewGroup) findViewById(R.id.container);

        uiGrid = new UIGridImpl(this, container);

        Game game = new Game(this,uiGrid);
        game.start();
    }
}
