package com.medeuz.connectfour.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.medeuz.connectfour.R;
import com.medeuz.connectfour.model.Cell;
import com.medeuz.connectfour.presenter.GamePresenterImpl;
import com.medeuz.connectfour.presenter.IGamePresenter;

import butterknife.BindView;

public class GameActivity extends Activity implements IView {

    private static final int DEFAULT_COLS_COUNT = 7;
    private static final int DEFAULT_ROWS_COUNT = 6;

    // interface to interact with presenter
    private IGamePresenter mGamePresenterImpl;

    //ToDo much better to implement custom view without tons of inherited views.
    private ImageView boardCells[][];

    @BindView(R.id.board_view)
    View boardView;

    @BindView(R.id.reset_game_btn)
    Button resetBtn;

    @BindView(R.id.turn_indicator_iv)
    ImageView turnIndicatorIv;

    @BindView(R.id.winner_tv)
    TextView winnerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        prepareBoard();
        mGamePresenterImpl = new GamePresenterImpl(this, DEFAULT_ROWS_COUNT, DEFAULT_COLS_COUNT);

        setListeners();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void showWinner(Cell.Player player) {
        int color = player == Cell.Player.FIRST_PLAYER
                ? getResources().getColor(R.color.first_player_color)
                : getResources().getColor(R.color.second_player_color);

        winnerTv.setTextColor(color);
        winnerTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTurn(int col, int row, Cell.Player player) {
        //ToDo дописать анимацию падения фишки в позицию
        mGamePresenterImpl.endOfTurn();
    }

    @Override
    public void turnChange(Cell.Player player) {
        if (player == Cell.Player.FIRST_PLAYER) {
            turnIndicatorIv.setImageResource(R.drawable.first_player_circle);
        } else {
            turnIndicatorIv.setImageResource(R.drawable.second_player_circle);
        }
    }

    // As alternative we can pass those listener via ButterKnife
    private void setListeners() {
        resetBtn.setOnClickListener((v) -> {
            mGamePresenterImpl.resetGame();
            resetGameView();
        });

        boardView.setOnTouchListener((view, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_UP: {
                    int col = colAtX(event.getX(), DEFAULT_COLS_COUNT);
                    if (col != -1)
                        mGamePresenterImpl.playerAction(col);
                }
            }
            return true;
        });
    }

    private void prepareBoard() {
        boardCells = new ImageView[DEFAULT_ROWS_COUNT][DEFAULT_COLS_COUNT];
        for (int row = 0; row < DEFAULT_ROWS_COUNT; row++) {
            ViewGroup rowVg = (ViewGroup) ((ViewGroup) boardView).getChildAt(row);
            rowVg.setClipChildren(false);
            for (int col = 0; col < DEFAULT_COLS_COUNT; col++) {
                ImageView imageView = (ImageView) rowVg.getChildAt(col);
                imageView.setImageResource(R.color.none_player_color);
                boardCells[row][col] = imageView;
            }
        }
    }

    private int colAtX(float x, int colsCount) {
        float colWidth = boardCells[0][0].getWidth();
        int col = (int) x / (int) colWidth;
        if (col < 0 || col > colsCount)
            return -1;
        return col;
    }

    private void resetGameView() {
        winnerTv.setVisibility(View.GONE);
        turnIndicatorIv.setImageResource(R.color.first_player_color);
        for (int row = 0; row < DEFAULT_ROWS_COUNT; row++) {
            for (int col = 0; col < DEFAULT_COLS_COUNT; col++) {
                boardCells[row][col].setImageResource(R.color.none_player_color);
            }
        }
    }

}
