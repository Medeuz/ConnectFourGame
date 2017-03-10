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

    //ToDo debug this function!
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

    public int lastAvailableRow(int col) {
        for (int row = mRowsCount - 1; row >= 0; row--) {
            if (mCells[row][col].getPlayer() == Cell.Player.NONE) {
                return row;
            }
        }
        return -1;
    }

    private boolean isFourConnected(Cell.Player player, int x, int y, int row, int col, int count) {
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

}
