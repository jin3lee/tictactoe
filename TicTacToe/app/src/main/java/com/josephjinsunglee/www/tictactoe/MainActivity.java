package com.josephjinsunglee.www.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity
{
    private static final int MAX_BUTTONS = 3;
    private Button[][] buttons;
    private Button button_reset;
    private TextView textView_indicator;

    static STATE state = STATE.X_TURN;

    private enum STATE
    {
        X_TURN, O_TURN, GAME_OVER
    }

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
        }
    }

    private void startGame()
    {
        // clears buttons text
        resetGame();
    }

    //
    private boolean isGameOver(int row, int column)
    {
        System.out.println("row "+isRowWin(row));
        System.out.println("column "+isColumnWin(column));
        System.out.println("corner "+isCornerWin(row, column));
        return isRowWin(row) || isColumnWin(column) || isCornerWin(row, column);
    }

    // returns true if game is won vertically
    private boolean isRowWin(int row)
    {
        boolean isWin = buttons[row][0].getText().toString()
                .equalsIgnoreCase(buttons[row][1].getText().toString())
                &&
                buttons[row][1].getText().toString()
                .equalsIgnoreCase(buttons[row][2].getText().toString());
        if(isWin)
        {
            for(int i = 0; i < MAX_BUTTONS; i++)
                buttons[row][i].setBackgroundColor(Color.RED);
        }
        return isWin;
    }

    // returns true if game is won vertically
    private boolean isColumnWin(int column)
    {
        boolean isColumnWin = true;
        for(int i = 0; i < MAX_BUTTONS-1; i++)
        {
            if(!buttons[i][column].getText().toString()
                    .equalsIgnoreCase(buttons[i+1][column].getText().toString()))
            {
                System.out.println("lolololololol");
                isColumnWin = false;
            }
        }

        if(isColumnWin)
        {
            System.out.println("powpowpowpowpowpow");
            for(int i = 0; i < MAX_BUTTONS; i++)
                buttons[i][column].setBackgroundColor(Color.RED);
        }
        return isColumnWin;
    }

    // returns true the game is won diagonally
    private boolean isCornerWin(int row, int column)
    {
        // check if the arguments passed in is a corner button
        if(!isCornerButton(row, column))
        {
            return false;
        }

        boolean isCornerWin = false;

        // check if diagonally won
        if(row == 0 && column == 0 || row == 2 && column == 2)
        {
            isCornerWin = (buttons[0][0].getText().toString()
                    .equalsIgnoreCase(buttons[1][1].getText().toString()))
                    && (buttons[1][1].getText().toString()
                    .equalsIgnoreCase(buttons[2][2].getText().toString()));

            if(isCornerWin)
            {

                System.out.println("hit isWin in isCorner");
                buttons[0][0].setBackgroundColor(Color.RED);
                buttons[1][1].setBackgroundColor(Color.RED);
                buttons[2][2].setBackgroundColor(Color.RED);
            }
        }
        else
        {
            isCornerWin = (buttons[0][2].getText().toString()
                    .equalsIgnoreCase(buttons[1][1].getText().toString()))
                    && (buttons[1][1].getText().toString()
                    .equalsIgnoreCase(buttons[2][0].getText().toString()));

            if(isCornerWin)
            {
                buttons[0][2].setBackgroundColor(Color.RED);
                buttons[1][1].setBackgroundColor(Color.RED);
                buttons[2][0].setBackgroundColor(Color.RED);
            }
        }

        return isCornerWin;
    }

    // returns true if button coordinate is a corner button
    private boolean isCornerButton(int row, int column)
    {
        if((row == 0 || row == 2)
                && (column == 0 || column == 2))
        {
            return true;
        }
        return false;
    }

    // returns true if no moves are available
    private boolean isButtonsFull()
    {
        for(int row = 0; row < MAX_BUTTONS; row++)
        {
            for(int column = 0; column < MAX_BUTTONS; column++)
            {
                if(buttons[row][column].getText().toString().equals(""))
                {
                    return false;
                }
            }
        }
        return true;
    }

    // leashes xml objects
    private void initializeViews()
    {
        textView_indicator = (TextView)findViewById(R.id.textView_turn);
        buttons = new Button[MAX_BUTTONS][MAX_BUTTONS];
        buttons[0][0] = (Button) findViewById(R.id.button1);
        buttons[0][1] = (Button) findViewById(R.id.button2);
        buttons[0][2] = (Button) findViewById(R.id.button3);
        buttons[1][0] = (Button) findViewById(R.id.button4);
        buttons[1][1] = (Button) findViewById(R.id.button5);
        buttons[1][2] = (Button) findViewById(R.id.button6);
        buttons[2][0] = (Button) findViewById(R.id.button7);
        buttons[2][1] = (Button) findViewById(R.id.button8);
        buttons[2][2] = (Button) findViewById(R.id.button9);
        button_reset = (Button) findViewById(R.id.button_reset);
    }

    // resets the game
    private void resetGame() {
        for (int row = 0; row < MAX_BUTTONS; row++)
            for (int column = 0; column < MAX_BUTTONS; column++)
                buttons[row][column].setText("");

        for(int i = 0; i < MAX_BUTTONS; i++)
            for(int j = 0; j < MAX_BUTTONS; j++)
                buttons[i][j].setBackgroundColor(Color.WHITE);

        textView_indicator.setText("X's Turn");
        state = STATE.X_TURN;
    }

    // sets the appropriate game state flow logic in buttons' listeners
    private void setListeners()
    {
        for(int row = 0; row < MAX_BUTTONS; row++)
            for(int column = 0; column < MAX_BUTTONS; column++)
                buttons[row][column].setOnClickListener(onClickListener);

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                for(int i = 0; i < MAX_BUTTONS; i++)
                {
                    for(int j = 0; j < MAX_BUTTONS; j++)
                    {
                        buttons[i][j].setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Button button = (Button)v;
            String tag_coordinate = button.getTag().toString();
            int row_clicked = tag_coordinate.charAt(0)-48;
            int column_clicked = tag_coordinate.charAt(1)-48;

            // does nothing if user presses a button with X or O already marked
            if(!buttons[row_clicked][column_clicked].getText().toString().equalsIgnoreCase(""))
            {
                return;
            }

            // game state flow
            if(state == STATE.X_TURN)
            {
                buttons[row_clicked][column_clicked].setText("X");
                if(isGameOver(row_clicked, column_clicked))
                {
                    //CASE: X won the game
                    textView_indicator.setText("X's Won!!!");
                    state = STATE.GAME_OVER;
                }
                else if(isButtonsFull())
                {
                    //CASE: Game ended in Tie
                    textView_indicator.setText("TIE!");
                    state = STATE.GAME_OVER;
                }
                else
                {
                    textView_indicator.setText("O's Turn");
                    state = STATE.O_TURN;
                }
            }
            else if(state == STATE.O_TURN)
            {
                buttons[row_clicked][column_clicked].setText("O");
                if(isGameOver(row_clicked, column_clicked))
                {
                    //CASE: X won the game
                    textView_indicator.setText("O's Won!!!");
                    state = STATE.GAME_OVER;
                }
                else if(isButtonsFull())
                {
                    //CASE: Game ended in Tie
                    textView_indicator.setText("GAME FINISHED IN TIE!");
                    state = STATE.GAME_OVER;
                }
                else
                {
                    textView_indicator.setText("X's Turn");
                    state = STATE.X_TURN;
                }
            }
            else
            {
                // Game Over, do nothing
            }
        }
    };
}
