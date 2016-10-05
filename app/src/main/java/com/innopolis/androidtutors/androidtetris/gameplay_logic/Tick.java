package com.innopolis.androidtutors.androidtetris.gameplay_logic;

/**
 * Created by Сергей on 04.10.2016.
 */

public interface Tick {
    void start();
    void stop();
    void setOnTickListener(OnTickListener listener);
}
