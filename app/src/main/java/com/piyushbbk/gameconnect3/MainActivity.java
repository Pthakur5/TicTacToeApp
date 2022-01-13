package com.piyushbbk.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int flag=0;
    int []gameState={2,2,2,2,2,2,2,2,2};
    int[][]winningPostitions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    public  static  int count =0;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(count== 9){
            gameActive=false;
        }

        if (gameState[tappedCounter] == 2 && gameActive) {

            count++;
            gameState[tappedCounter] = flag;
            counter.setTranslationY(-1500);
            if (flag == 0) {
                counter.setImageResource(R.drawable.letter1);
                counter.animate().rotation(3600).translationYBy(1500).setDuration(200);
                flag = 1;
            } else {
                counter.setImageResource(R.drawable.lettercircle);
                counter.animate().rotation(3600).translationYBy(1500).setDuration(200);
                flag = 0;
            }
            int f=0;
            for (int[] winningpostion : winningPostitions) {
                if (gameState[winningpostion[0]] == gameState[winningpostion[1]] && gameState[winningpostion[1]] == gameState[winningpostion[2]] && gameState[winningpostion[0]] != 2) {
                    gameActive = false;
                     f=1;
                    String message = "";
                    if (flag == 1)
                        message = "Player 1";
                    else
                        message = "Player 2";

                    Button button = (Button) findViewById(R.id.button);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(message + " has won!!");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);

                }
            }
            if (count == 9 && f == 0) {
                Button button = (Button) findViewById(R.id.button);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText( "Match Drawn!!");
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
        }
        }
    }

    public void playAgain(View view){
        count=0;
        Button button= (Button)findViewById(R.id.button);
        TextView textView= (TextView)findViewById(R.id.textView);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        flag=0;
        for(int i=0;i< gameState.length;i++) {
            gameState[i] = 2;
        }
         gameActive=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}