package com.upeu.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeMenu extends AppCompatActivity {
    public Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);

        playButton = (Button) findViewById(R.id.playButton);
    }

    public void changeView(View view) {
        Intent i = new Intent(this, GameInit.class);
        startActivity(i);
    }
}