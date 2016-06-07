package com.josephjinsunglee.www.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity
{
    Button gameButton, settingButton;
    RelativeLayout relativeLayout_game, relativeLayout_settings;
    boolean isGameTabActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.frameLayout_content) != null)
        {
            if (savedInstanceState != null) {
                return;
            }

            GameFragment gameFragment = new GameFragment();
            gameFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout_content, gameFragment);
            transaction.commit();

            initializeViews();
        }
    }

    private void initializeViews()
    {
        // leash views
        gameButton = (Button)findViewById(R.id.button_game);
        settingButton = (Button)findViewById(R.id.button_setting);
        relativeLayout_game = (RelativeLayout)findViewById(R.id.relativeLayout_game);
        relativeLayout_settings = (RelativeLayout)findViewById(R.id.relativeLayout_settings);

        // initialize tab color to orange
        relativeLayout_game.setBackgroundColor(Color.parseColor("#FF8800"));

        // set button listeners
        gameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                relativeLayout_game.setBackgroundColor(Color.parseColor("#FF8800"));
                relativeLayout_settings.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                relativeLayout_game.setBackgroundColor(Color.TRANSPARENT);
                relativeLayout_settings.setBackgroundColor(Color.parseColor("#FF8800"));
            }
        });
    }
}
