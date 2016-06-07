package com.josephjinsunglee.www.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity
{
    Button gameButton, instructionButton;
    RelativeLayout relativeLayout_game, relativeLayout_instruction;
    boolean isGameTabActive;
    RelativeLayout gameFragment_container, instructionFragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    private void initializeViews()
    {
        // leash views
        gameButton = (Button)findViewById(R.id.button_game);
        instructionButton = (Button)findViewById(R.id.button_instruction);
        relativeLayout_game = (RelativeLayout)findViewById(R.id.relativeLayout_game);
        relativeLayout_instruction = (RelativeLayout)findViewById(R.id.relativeLayout_instruction);
        gameFragment_container = (RelativeLayout)findViewById(R.id.gameFragmentContainer);
        instructionFragment_container = (RelativeLayout)findViewById(R.id.instructionFragmentContainer);

        // initialize tab color to orange
        relativeLayout_game.setBackgroundColor(Color.parseColor("#FF8800"));
        isGameTabActive = true;
        instructionFragment_container.setVisibility(View.GONE); //hide instructionFragment


        // when users click the instruction button the gameFragment appears & other fragment hides
        gameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!isGameTabActive)
                {
                    relativeLayout_game.setBackgroundColor(Color.parseColor("#FF8800"));
                    relativeLayout_instruction.setBackgroundColor(Color.TRANSPARENT);
                    isGameTabActive = !isGameTabActive;
                    instructionFragment_container.setVisibility(View.GONE);
                    gameFragment_container.setVisibility(View.VISIBLE);
                }
            }
        });

        // when users click the instruction button the instructionFragment appears & gameFragment hides
        instructionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isGameTabActive)
                {
                    relativeLayout_game.setBackgroundColor(Color.TRANSPARENT);
                    relativeLayout_instruction.setBackgroundColor(Color.parseColor("#FF8800"));
                    isGameTabActive = !isGameTabActive;
                    instructionFragment_container.setVisibility(View.VISIBLE);
                    gameFragment_container.setVisibility(View.GONE);
                }
            }
        });
    }
}
