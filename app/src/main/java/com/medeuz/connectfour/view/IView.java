package com.medeuz.connectfour.view;


import com.medeuz.connectfour.model.Cell;

public interface IView {

    void showWinner(Cell.Player player);

    void showTurn(int row, int col, Cell.Player player);

    void turnChange(Cell.Player player);
}
