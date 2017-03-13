package com.medeuz.connectfour;


import com.medeuz.connectfour.ai.Bot;
import com.medeuz.connectfour.model.Board;

import junit.framework.Assert;

import org.junit.Test;

public class BotUnitTest {

    @Test
    public void cellModelTest() throws Exception {
        final int rowsCount = 6;
        final int colsCount = 7;

        Board board = new Board(rowsCount, colsCount);
        Bot bot = new Bot(rowsCount, colsCount, board);

        board.setCellOwner(rowsCount - 1, 0);
        board.setCellOwner(rowsCount - 2, 0);
        board.setCellOwner(rowsCount - 3, 0);
        int col = bot.getSolutionCol();

        Assert.assertEquals(col, 0); // Because player need to take only one cell in 0 col to win
    }

}
