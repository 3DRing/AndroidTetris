package com.innopolis.androidtutors.androidtetris;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.innopolis.androidtutors.androidtetris.gameplay_logic.EndListener;
import com.innopolis.androidtutors.androidtetris.gameplay_logic.GameResult;
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
    private Game game;

    private Button leftBtn;
    private Button rightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        container = (ViewGroup) findViewById(R.id.container);

        uiGrid = new UIGridImpl(this, container);

        game = new Game(this,uiGrid);
        game.setEndListener(new EndListener() {
            @Override
            public void end(GameResult result) {
                game.stop();
                Toast.makeText(MainActivity.this, "End", Toast.LENGTH_SHORT).show();
            }
        });

        leftBtn = (Button) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.moveLeft();
            }
        });
        rightBtn = (Button) findViewById(R.id.rightBtn);
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.moveRight();
            }
        });

        game.start();
    }
}
