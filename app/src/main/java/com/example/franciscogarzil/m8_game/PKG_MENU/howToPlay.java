package com.example.franciscogarzil.m8_game.PKG_MENU;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.franciscogarzil.m8_game.R;

public class howToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play);
    }
    @Override
    public void onBackPressed () {

    }

    public void goBack(View view) {
        Intent myIntent = new Intent(howToPlay.this, CharacterPage.class);
        howToPlay.this.startActivity(myIntent);
    }

}
