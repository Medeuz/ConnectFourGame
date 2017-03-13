package com.medeuz.connectfour;


import com.medeuz.connectfour.ai.Bot;
import com.medeuz.connectfour.model.Board;

import junit.framework.Assert;

import org.junit.Test;

public class BotUnitTest {

    private static final int ROWS_COUNT = 6;
    private static final int COLS_COUNT = 7;

    @Test
    public void cellModelTest() throws Exception {
        Board board = new Board(ROWS_COUNT, COLS_COUNT);
        Bot bot = new Bot(ROWS_COUNT, COLS_COUNT, board);

        board.setCellOwner(ROWS_COUNT - 1, 0);
        board.setCellOwner(ROWS_COUNT - 2, 0);
        board.setCellOwner(ROWS_COUNT - 3, 0);
        int col = bot.getSolutionCol();

        Assert.assertEquals(col, 0); // Because player need to take only one cell in 0 col to win
    }

}
