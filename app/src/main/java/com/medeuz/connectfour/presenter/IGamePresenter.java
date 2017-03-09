package com.medeuz.connectfour.presenter;


public interface IGamePresenter {

    void resetGame();

    void endOfTurn();

    void playerAction(int col);

}
