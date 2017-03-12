package com.medeuz.connectfour.model;


import com.medeuz.connectfour.utils.Utils;

public class Cell {

    private Utils.Player mPlayer;

    public Cell() {
        mPlayer = Utils.Player.NONE;
    }

    public void setPlayer(Utils.Player player) {
        mPlayer = player;
    }

    public Utils.Player getPlayer() {
        return mPlayer;
    }

}
