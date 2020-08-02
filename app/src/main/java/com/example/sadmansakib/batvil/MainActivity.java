package com.example.sadmansakib.batvil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import static java.lang.System.*;

public class MainActivity extends AppCompatActivity {
    int currentPlayer;
    int[] gameState = {5,5,5,5,5,5,5,5,5};
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive = true;
    //Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
    public void takePlace(View view) {
        TextView winnerPrompt = findViewById(R.id.winnerPrompt);
        TextView playersTurn = findViewById(R.id.playersTurn);
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 5 && gameIsActive) {
            counter.setTranslationY(-1000);
            gameState[tappedCounter] = currentPlayer;
            if (currentPlayer == 0) {
                counter.setImageResource(R.drawable.daredevil);
                currentPlayer = 1;
                playersTurn.setText("Batman's turn");
            } else {
                counter.setImageResource(R.drawable.batman);
                currentPlayer = 0;
                playersTurn.setText("Daredevil's turn");
            }
            counter.animate().rotationYBy(360).translationYBy(1000).setDuration(300);
            for(int[] winPos : winPositions) {
                if(gameState[winPos[0]] == gameState[winPos[1]]
                        && gameState[winPos[1]]==gameState[winPos[2]]
                        && gameState[winPos[0]] != 5) {
                    out.println("Winner = " +gameState[winPos[0]]);
                    gameIsActive = false;
                    if(gameState[winPos[0]]==0) {
                        winnerPrompt.setText("Voila! Daredevil has won!");
                    } else {
                        winnerPrompt.setText("Voila! Batman has won!");
                    }
                    LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
                    //playAgainLayout.startAnimation(slideUp);
                    playAgainLayout.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    boolean gameIsOver = true;
                    for(int counterState : gameState) {
                        if(counterState == 5)
                            gameIsOver = false;
                    }
                    if(gameIsOver) {
                        winnerPrompt.setText("Hmm! Looks like a draw!");
                        LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
                        playAgainLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void restartClicked(View view) {
        gameIsActive = true;
        LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);
        for (int x = 0; x < gameState.length; x++) {
            gameState[x] = 5;
        }
        //These massive digits are id's of the childGrids
        for(int x=2131361874; x<2131361883; x++) {
            ImageView temp = findViewById(x);
            temp.setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentPlayer = (int)(Math.random()*2);
        TextView playersTurn = findViewById(R.id.playersTurn);
        if(currentPlayer==0) {
            playersTurn.setText("Daredevil's turn");
        }
        else {
            playersTurn.setText("Batman's turn");
        }
    }
}
