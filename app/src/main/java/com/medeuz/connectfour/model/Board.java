package com.medeuz.connectfour.model;


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

    public void reset() {
        hasWinner = false;
        firstTurn = true;
        for (int row = 0; row < mRowsCount; row++) {
            for (int col = 0; col < mColsCount; col++) {
                mCells[row][col] = new Cell();
            }
        }
    }

    public boolean isHasWinner() {
        return hasWinner;
    }

    public Cell.Player getCurrentPlayer() {
        if (firstTurn)
            return Cell.Player.FIRST_PLAYER;
        else
            return Cell.Player.SECOND_PLAYER;
    }

    public void toggleTurn() {
        firstTurn = !firstTurn;
    }

    public void setCellOwner(int row, int col) {
        mCells[row][col].setPlayer(getCurrentPlayer());
    }

    public boolean isWinTurn() {
        return false;
    }

    public int lastAvailableRow(int col) {
        for (int row = mRowsCount - 1; row >= 0; row--) {
            if (mCells[row][col].getPlayer() == Cell.Player.NONE) {
                return row;
            }
        }
        return -1;
    }

}
