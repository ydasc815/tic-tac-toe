package com.example.gametest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // img1 = O || img2 = X

    int activePlayer = 0;

    int player0WinHistory = 0; int player1WinHistory = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // here 2 means unplayable value

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.img1);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.img2);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition : winningPositions) {  // use of 'for-each' loop here

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] ==
                        gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    Toast.makeText(this, "Player " + ++gameState[winningPosition[0]] +
                            " has won the game !", Toast.LENGTH_LONG).show();

                    Button playAgain = findViewById(R.id.button);
                    playAgain.setVisibility(View.VISIBLE);

                    if(gameState[winningPosition[0]] == 0)
                        player0WinHistory++;
                    else
                        player1WinHistory++;

                }

            }

        }

    }

    public void playAgain(View view) {

        Button button = findViewById(R.id.button);

        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        ImageView imageView7 = (ImageView) findViewById(R.id.imageView7);
        ImageView imageView8 = (ImageView) findViewById(R.id.imageView8);
        ImageView imageView9 = (ImageView) findViewById(R.id.imageView9);

        imageView.setImageResource(0);
        imageView2.setImageResource(0);
        imageView3.setImageResource(0);
        imageView4.setImageResource(0);
        imageView5.setImageResource(0);
        imageView6.setImageResource(0);
        imageView7.setImageResource(0);
        imageView8.setImageResource(0);
        imageView9.setImageResource(0);


        button.setVisibility(View.INVISIBLE);

        TextView win = (TextView) findViewById(R.id.win);
        win.setVisibility(View.INVISIBLE);


    }

    public void winHistory(View view){
        TextView win = (TextView) findViewById(R.id.win);
        win.setVisibility(View.VISIBLE);
        win.setText("Player 1 has won " + player0WinHistory +
                " times and Player 2 has won " + player1WinHistory + " times");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
