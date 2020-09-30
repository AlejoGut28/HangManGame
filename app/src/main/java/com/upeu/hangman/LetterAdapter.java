package com.upeu.hangman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class LetterAdapter extends BaseAdapter {
    private String[] letters;
    private LayoutInflater letterInf;

    public LetterAdapter(   Context context) {
        letters = new String[26];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = "" + (char)(i+'A');
        }
        letterInf = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btnletter;
        if ( view == null) {
            btnletter = (Button) letterInf.inflate(R.layout.letter, viewGroup, false);
        }else {
            btnletter = (Button)view;
        }
        btnletter.setText(letters[i]);
        return btnletter;
    }
}
