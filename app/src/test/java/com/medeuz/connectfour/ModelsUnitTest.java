package com.medeuz.connectfour;


import com.medeuz.connectfour.model.Board;
import com.medeuz.connectfour.model.Cell;
import com.medeuz.connectfour.utils.Utils;


import org.junit.Assert;
import org.junit.Test;

public class ModelsUnitTest {

    @Test
    public void cellModelTest() throws Exception {
        Cell cell = new Cell();
        Assert.assertEquals(cell.getPlayer(), Utils.Player.NONE);

        cell.setPlayer(Utils.Player.FIRST_PLAYER);
        Assert.assertEquals(cell.getPlayer(), Utils.Player.FIRST_PLAYER);
    }

    @Test
    public void boardModelTest() throws Exception {
        final int countForWin = 4;
        final int rowsCount = 6;
        final int colsCount = 7;

        Board board = new Board(rowsCount, colsCount);

        Assert.assertEquals(board.getCurrentPlayer(), Utils.Player.FIRST_PLAYER);

        board.toggleTurn();
        Assert.assertEquals(board.getCurrentPlayer(), Utils.Player.SECOND_PLAYER);

        int lastAvailableRow = board.lastAvailableRow(0);
        Assert.assertEquals(lastAvailableRow, rowsCount - 1);

        Assert.assertFalse(board.isHasWinner());
        for (int i = 0; i < countForWin; i++) {
            board.setCellOwner(lastAvailableRow, i);
            lastAvailableRow = board.lastAvailableRow(i + 1);
        }

        Assert.assertTrue(board.isWinTurn());
        Assert.assertTrue(board.isHasWinner());

        board.reset();
        Assert.assertFalse(board.isHasWinner());
        Assert.assertFalse(board.isHasWinner());
        Assert.assertEquals(board.getCurrentPlayer(), Utils.Player.FIRST_PLAYER);
    }

}
