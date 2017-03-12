package com.medeuz.connectfour.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.medeuz.connectfour.R;
import com.medeuz.connectfour.presenter.GamePresenterImpl;
import com.medeuz.connectfour.presenter.IGamePresenter;
import com.medeuz.connectfour.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        ButterKnife.bind(this);

        prepareBoard();
        boolean isOnePlayer = getIntent().getBooleanExtra(Utils.EXTRA_IS_ONE_PLAYER, false);
        mGamePresenterImpl
                = new GamePresenterImpl(this, DEFAULT_ROWS_COUNT, DEFAULT_COLS_COUNT, isOnePlayer);

        setListeners();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void showWinner(Utils.Player player) {
        int color = player == Utils.Player.FIRST_PLAYER
                ? getResources().getColor(R.color.first_player_color)
                : getResources().getColor(R.color.second_player_color);

        winnerTv.setTextColor(color);
        winnerTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTurn(int row, int col, Utils.Player player) {

        float move = -(boardCells[row][col].getHeight() * row
                + boardCells[row][col].getHeight() + 15);
        boardCells[row][col].setY(move);

        if (player == Utils.Player.FIRST_PLAYER) {
            boardCells[row][col].setImageResource(R.drawable.first_player_circle);
        } else {
            boardCells[row][col].setImageResource(R.drawable.second_player_circle);
        }

        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, Math.abs(move));
        anim.setDuration(850);
        anim.setFillAfter(true);

        boardCells[row][col].startAnimation(anim);

        mGamePresenterImpl.endOfTurn();
    }

    @Override
    public void turnChange(Utils.Player player) {
        if (player == Utils.Player.FIRST_PLAYER) {
            turnIndicatorIv.setImageResource(R.drawable.first_player_circle);
        } else {
            turnIndicatorIv.setImageResource(R.drawable.second_player_circle);
        }
    }

    // As alternative we can pass those listeners via ButterKnife
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
        turnIndicatorIv.setImageResource(R.drawable.first_player_circle);
        boardCells = new ImageView[DEFAULT_ROWS_COUNT][DEFAULT_COLS_COUNT];
        for (int row = 0; row < DEFAULT_ROWS_COUNT; row++) {
            ViewGroup rowVg = (ViewGroup) ((ViewGroup) boardView).getChildAt(row);
            rowVg.setClipChildren(false);
            for (int col = 0; col < DEFAULT_COLS_COUNT; col++) {
                ImageView imageView = (ImageView) rowVg.getChildAt(col);
                imageView.setImageResource(R.drawable.none_player_circle);
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
        turnIndicatorIv.setImageResource(R.drawable.first_player_circle);
        for (int row = 0; row < DEFAULT_ROWS_COUNT; row++) {
            for (int col = 0; col < DEFAULT_COLS_COUNT; col++) {
                boardCells[row][col].setImageResource(R.drawable.none_player_circle);
            }
        }
    }

}
