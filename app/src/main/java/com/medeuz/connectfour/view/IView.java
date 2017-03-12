package com.medeuz.connectfour.view;


import com.medeuz.connectfour.utils.Utils;

public interface IView {

    void showWinner(Utils.Player player);

    void showTurn(int row, int col, Utils.Player player);

    void turnChange(Utils.Player player);
}
