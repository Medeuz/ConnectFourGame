package com.medeuz.connectfour.presenter;


import com.medeuz.connectfour.ai.Bot;
import com.medeuz.connectfour.model.Board;
import com.medeuz.connectfour.view.IView;

public class GamePresenterImpl implements IGamePresenter {

    private Board mBoard;
    private IView mView;
    private Bot mBot;

    public GamePresenterImpl(IView view, int rows, int cols, boolean isSinglePlayer) {
        mBoard = new Board(rows, cols);
        mView = view;
        if (isSinglePlayer) {
            mBot = new Bot(rows, cols, mBoard);
        }
    }

    private void makeTurn(int col) {
        if (mBoard.isHasWinner()) return;

        int row = mBoard.lastAvailableRow(col);

        if (row == -1) return;

        mBoard.setCellOwner(row, col);
        mView.showTurn(row, col, mBoard.getCurrentPlayer());
    }

    @Override
    public void resetGame() {
        mBoard.reset();
    }

    @Override
    public void endOfTurn() {
        if (!mBoard.isWinTurn()) {
            mBoard.toggleTurn();
            mView.turnChange(mBoard.getCurrentPlayer());
        } else {
            mView.showWinner(mBoard.getCurrentPlayer());
        }
    }

    @Override
    public void playerAction(int col) {
        makeTurn(col);
        if (mBot != null) {
            makeTurn(mBot.getSolutionCol());
        }
    }

}
