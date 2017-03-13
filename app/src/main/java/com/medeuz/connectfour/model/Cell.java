package com.medeuz.connectfour.model;


import com.medeuz.connectfour.utils.Utils;

public class Cell {

    private Utils.Player mPlayer;

    public Cell() {
        mPlayer = Utils.Player.NONE;
    }

    /***
     * Setting owner of the cell
     * @param player NONE for free cell, see Utils.Player class
     */
    public void setPlayer(Utils.Player player) {
        mPlayer = player;
    }

    /***
     * Getter for current owner of cell
     * @return Player enum (NONE, FIRST_PLAYER, SECOND_PLAYER) {@link Utils.Player}
     */
    public Utils.Player getPlayer() {
        return mPlayer;
    }

}
