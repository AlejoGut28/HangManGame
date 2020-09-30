package com.upeu.hangman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class GameInit extends AppCompatActivity {
    private String[] words;
    private Random random;
    private String currWord;
    private TextView[] charView;
    private LinearLayout wordLayout;
    private LetterAdapter letterAdapter;
    private GridView gridView;
    private int numCorr;
    private String scored;
    private int numChar;
    private int sizePart = 6;
    private ImageView[] part;
    private int currPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_init);
        words = getResources().getStringArray(R.array.comidas_facil);
        random = new Random();
        wordLayout = findViewById(R.id.words);
        gridView = findViewById(R.id.letters);
        part = new ImageView[sizePart];
        part[0] = findViewById(R.id.head);
        part[1] = findViewById(R.id.twist);
        part[2] = findViewById(R.id.armRight);
        part[3] = findViewById(R.id.armLeft);
        part[4] = findViewById(R.id.legRight);
        part[5] = findViewById(R.id.legLeft);
        playGame();
    }

    public void changeView(View view) {
        Intent i = new Intent(this, HomeMenu.class);
        startActivity(i);
    }

    public void playGame() {
        String newWords = words[random.nextInt(words.length)];

        while (newWords.equals(currWord)) newWords = words[random.nextInt(words.length)];

        currWord = newWords;
        charView = new TextView[currWord.length()];
        for (int i = 0; i < currWord.length(); i++) {
            charView[i] = new TextView(this);
            charView[i].setText("" + currWord.charAt(i));
            charView[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charView[i].setGravity(Gravity.CENTER);
            charView[i].setTextColor(Color.WHITE);
            charView[i].setBackgroundResource(R.drawable.letter_bg);
            wordLayout.addView(charView[i]);
        }
        letterAdapter = new LetterAdapter(this);
        gridView.setAdapter(letterAdapter);
        numCorr = 0;
        scored = "" + 0;
        numChar = 0;
        currPart = 0;
    }

    public void letterPressed(View view) {
        String letter = ((TextView)view).getText().toString();
        char letterChar = letter.charAt(0);
        view.setEnabled(false);
        boolean booleanCorrect = false;
        for (int i = 0; i < currWord.length(); i++) {
            if (currWord.charAt(i) == letterChar) {
                booleanCorrect = true;
                numCorr++;
                scored = "" + 100;
                charView[i].setTextColor(Color.BLACK);
            }
        }
        if (booleanCorrect) {
            if (numCorr == numChar){
                disabledButton();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("You win !!");
                builder.setMessage("Congratulation!! \n\n the answer was \n\n" + currWord);
                builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameInit.this.playGame();
                    }
                });
                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameInit.this.finish();
                    }
                });
                builder.show();
            }
        } else if (currPart < sizePart) {
            part[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            disabledButton();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You lose !!");
            builder.setMessage("Wrong!! \n\n the answer was \n\n" + currWord);
            builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GameInit.this.playGame();
                }
            });
            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GameInit.this.finish();
                }
            });
            builder.show();
        }
    }

    public void disabledButton () {
        for (int i = 0 ; i < gridView.getChildCount(); i++) {
            gridView.getChildAt(i).setEnabled(false);
        }
    }
}