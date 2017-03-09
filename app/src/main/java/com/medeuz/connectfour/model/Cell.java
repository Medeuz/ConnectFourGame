package com.medeuz.connectfour.model;


public class Cell {

    public enum Player {
        FIRST_PLAYER, SECOND_PLAYER, NONE
    }

    private Player mPlayer;

    public Cell() {
        mPlayer = Player.NONE;
    }

    public void setPlayer(Player player) {
        mPlayer = player;
    }

    public Player getPlayer() {
        return mPlayer;
    }

}
