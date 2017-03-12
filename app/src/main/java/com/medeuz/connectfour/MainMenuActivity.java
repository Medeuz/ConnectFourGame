package com.medeuz.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.medeuz.connectfour.utils.Utils;
import com.medeuz.connectfour.view.GameActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends AppCompatActivity {

    @BindView(R.id.one_player_btn)
    Button onePlayerBtn;
    @BindView(R.id.two_player_btn)
    Button twoPlayersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        onePlayerBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(Utils.EXTRA_IS_ONE_PLAYER, true);
            startActivity(intent);
        });

        twoPlayersBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(Utils.EXTRA_IS_ONE_PLAYER, false);
            startActivity(intent);
        });
    }
}
