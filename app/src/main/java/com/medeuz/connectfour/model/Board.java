package com.medeuz.connectfour.model;


import com.medeuz.connectfour.utils.Utils;

public class Board {

    private int mColsCount;

    private int mRowsCount;

    private Cell[][] mCells;

    private boolean firstTurn;

    private boolean hasWinner;

    public Board(int rowsCount, int colsCount) {
        mColsCount = colsCount;
        mRowsCount = rowsCount;
        mCells = new Cell[mRowsCount][mColsCount];
        reset();
    }

    /***
     * Reset board to initial state
     */
    public void reset() {
        hasWinner = false;
        firstTurn = true;
        for (int row = 0; row < mRowsCount; row++) {
            for (int col = 0; col < mColsCount; col++) {
                mCells[row][col] = new Cell();
            }
        }
    }

    /***
     *  Getter for current win state of board
     * @return boolean of hasWinner flag
     */
    public boolean isHasWinner() {
        return hasWinner;
    }

    public Utils.Player getCurrentPlayer() {
        if (firstTurn)
            return Utils.Player.FIRST_PLAYER;
        else
            return Utils.Player.SECOND_PLAYER;
    }

    public void toggleTurn() {
        firstTurn = !firstTurn;
    }

    public void setCellOwner(int row, int col) {
        mCells[row][col].setPlayer(getCurrentPlayer());
    }

    /***
     *  Process board to check is current player won and sets hasWinner flag to true
     * @return boolean which shows is winning turn
     */
    public boolean isWinTurn() {
        for (int col = 0; col < mColsCount; col++) {
            if (isFourConnected(getCurrentPlayer(), 0, 1, 0, col, 0)
                    || isFourConnected(getCurrentPlayer(), 1, 1, 0, col, 0)
                    || isFourConnected(getCurrentPlayer(), -1, 1, 0, col, 0)) {
                hasWinner = true;
                return true;
            }
        }

        for (int row = 0; row < mRowsCount; row++) {
            if (isFourConnected(getCurrentPlayer(), 1, 0, row, 0, 0)
                    || isFourConnected(getCurrentPlayer(), 1, 1, row, 0, 0)
                    || isFourConnected(getCurrentPlayer(), -1, 1, row, mColsCount - 1, 0)) {
                hasWinner = true;
                return true;
            }
        }

        return false;
    }

    /***
     * Getter for last available row in passed col
     * @param col - in what col we should get last available row
     * @return last available row or -1 if no rows left in passed col
     */
    public int lastAvailableRow(int col) {
        for (int row = mRowsCount - 1; row >= 0; row--) {
            if (mCells[row][col].getPlayer() == Utils.Player.NONE) {
                return row;
            }
        }
        return -1;
    }

    /***
     * Recursive function which check is passed Cell connected with other 3 cells
     * @param player current player which cell we checking
     * @param x parameter for recursion to move col in different directions
     * @param y parameter for recursion to move row in different directions
     * @param row of cell
     * @param col of cell
     * @param count recursive accumulator of connected cells
     * @return boolean is 4 cells connected
     */
    private boolean isFourConnected(Utils.Player player, int x, int y, int row, int col, int count) {
        if (count >= 4) {
            return true;
        }

        if (col < 0 || col >= mColsCount || row < 0 || row >= mRowsCount) {
            return false;
        }

        Cell cell = mCells[row][col];
        if (cell.getPlayer() == player) {
            return isFourConnected(player, x, y, row + y, col + x, count + 1);
        } else {
            return isFourConnected(player, x, y, row + y, col + x, 0);
        }
    }

    //Todo move it to Bot module by changing a little functionality of Board model.
    /***
     * Recursive function which calculate count of connected cells needs only for AI Bot.
     * @param player current player which cell we checking
     * @param x parameter for recursion to move col in different directions
     * @param y parameter for recursion to move row in different directions
     * @param row of cell
     * @param col of cell
     * @return integer of connected cells
     */
    public int getConnectedCount(Utils.Player player, int x, int y, int row, int col) {

        if (col < 0 || col >= mColsCount || row < 0 || row >= mRowsCount) {
            return 0;
        }
        if (x >= 4)
            return 0;

        Cell cell = mCells[row][col];
        if (cell.getPlayer() == player) {
            return 1 + getConnectedCount(player, x, y, row + y, col + x);
        } else if (cell.getPlayer() == Utils.Player.NONE){
            return getConnectedCount(player, x, y, row + y, col + x);
        } else {
            return 0;
        }

    }

}
