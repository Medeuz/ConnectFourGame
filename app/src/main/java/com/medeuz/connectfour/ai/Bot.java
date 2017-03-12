package com.medeuz.connectfour.ai;


import com.medeuz.connectfour.model.Board;
import com.medeuz.connectfour.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bot {

    private Board mBoard;

    private int mColsCount;

    private int mRowsCount;

    public Bot(int rowsCount, int colsCount, Board board) {
        mRowsCount = rowsCount;
        mColsCount = colsCount;
        mBoard = board;
    }

    private List<Solution> getPossibleSolutions() {
        List<Solution> solutions = new ArrayList<>(mColsCount);

        for (int i = 0; i < mColsCount; i++) {
            int row = mBoard.lastAvailableRow(i);
            if (row >= 0 && row < mRowsCount) {
                int countPlayerLeftRight = mBoard.getConnectedCount(Utils.Player.SECOND_PLAYER, -1, 0, row, i)
                        + mBoard.getConnectedCount(Utils.Player.SECOND_PLAYER, 1, 0, row, i);
                int countPlayerBottom = mBoard.getConnectedCount(Utils.Player.SECOND_PLAYER, 0, 1, row, i);
                int countPlayerRightBottom = mBoard.getConnectedCount(Utils.Player.SECOND_PLAYER, 1, 1, row, i);
                int countPlayerLeftBottom = mBoard.getConnectedCount(Utils.Player.SECOND_PLAYER, -1, 1, row, i);

                int countBotLeftRight = mBoard.getConnectedCount(Utils.Player.FIRST_PLAYER, -1, 0, row, i)
                        + mBoard.getConnectedCount(Utils.Player.FIRST_PLAYER, 1, 0, row, i);
                int countBotBottom = mBoard.getConnectedCount(Utils.Player.FIRST_PLAYER, 0, 1, row, i);
                int countBotRightBottom = mBoard.getConnectedCount(Utils.Player.FIRST_PLAYER, 1, 1, row, i);
                int countBotLeftBottom = mBoard.getConnectedCount(Utils.Player.FIRST_PLAYER, -1, 1, row, i);

                int countBot = Math.max(Math.max(Math.max(
                        countBotBottom, countBotLeftRight),
                        countBotLeftBottom),
                        countBotRightBottom);

                int countPlayer = Math.max(Math.max(Math.max(
                        countPlayerBottom, countPlayerLeftRight),
                        countPlayerLeftBottom),
                        countPlayerRightBottom);

                solutions.add(new Solution(countBot, countPlayer, i));
            }
        }

        return solutions;
    }

    public int getSolutionCol() {
        List<Solution> possibleSolutions = getPossibleSolutions();
        if (possibleSolutions.size() > 0) {
            Solution maxCountPlayer = possibleSolutions.get(0),
                    maxCountBot = possibleSolutions.get(0);

            for (int i = 1; i < possibleSolutions.size(); i++) {
                maxCountBot = possibleSolutions.get(i).countBot > maxCountBot.countBot
                        ? possibleSolutions.get(i) : maxCountBot;
                maxCountPlayer = possibleSolutions.get(i).countPlayer > maxCountPlayer.countPlayer
                        ? possibleSolutions.get(i) : maxCountPlayer;
            }

            if (maxCountBot.countBot <= maxCountPlayer.countPlayer)
                return maxCountPlayer.col;
            else
                return maxCountBot.col;
        }

        return -1;
    }

    private class Solution {
        int countBot;
        int countPlayer;
        int col;

        Solution(int countBot, int countPlayer, int col) {
            this.countBot = countBot;
            this.countPlayer = countPlayer;
            this.col = col;
        }
    }

}
