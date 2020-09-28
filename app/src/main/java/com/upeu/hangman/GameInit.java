package com.upeu.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GameInit extends AppCompatActivity {
    public ImageButton back_button;
    private String[] eatItem;
    private Random random;
    private String unrepeatWord;
    private TextView[] downLine;
    private LinearLayout wordC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_init);

        eatItem = getResources().getStringArray(R.array.comidas_facil);
        wordC = findViewById(R.id.wordCenter);

        back_button = findViewById(R.id.homeButton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(v);
            }
        });
    }

    public void changeView(View view) {
        Intent i = new Intent(this, HomeMenu.class);
        startActivity(i);
    }

    private void lineDynamic() {
        String word;
        word = eatItem[random.nextInt(eatItem.length)];

        while (word.equals(unrepeatWord)) {
            word = eatItem[random.nextInt(eatItem.length)];
        }

        downLine = new TextView[unrepeatWord.length()];

        for(int i = 0; i < unrepeatWord.length(); i++) {
            downLine[i] = new TextView(this);
            downLine[i].setText(unrepeatWord.charAt(i));
            downLine[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            downLine[i].setGravity(Gravity.CENTER);
            downLine[i].setTextColor(Color.BLACK);
            downLine[i].setBackgroundColor(Color.BLACK);
            wordC.addView(downLine[i]);
        }
    }
}