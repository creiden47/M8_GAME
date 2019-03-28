package com.example.franciscogarzil.m8_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CharacterPage extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_page);
        new StatusInit(this);
        // Buttons
        Button _newGame = findViewById(R.id.btnNewGame);
        Button _library = findViewById(R.id.btnLibrary);
        // On Click Listeners:
        _newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start new game:
            }
        });
        _library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the library:
                Intent myIntent = new Intent(CharacterPage.this, storeFragment.class);
                CharacterPage.this.startActivity(myIntent);
            }
        });
    }

}
