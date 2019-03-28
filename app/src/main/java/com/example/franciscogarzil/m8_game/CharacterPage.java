package com.example.franciscogarzil.m8_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.franciscogarzil.m8_game.MainMenu.currentCharacter;

public class CharacterPage extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_page);

        // Text Views
        // Welcome message
        TextView welcomeMsg = findViewById(R.id.welcome_msg);
        // Get Placeholder
        String welcome_placeholder = getResources().getString(R.string.welcome) + " " + currentCharacter.get_name();
        // Set Texts
        welcomeMsg.setText(welcome_placeholder);
        // Character Status
        // Text Views
        TextView _dmgStatus = findViewById(R.id.txtStatusDmg);
        TextView _speedStatus = findViewById(R.id.txtStatusSpeed);
        TextView _vitalityStatus = findViewById(R.id.txtStatusVitality);
        TextView _masteryPoints = findViewById(R.id.masteryPoints);
        // Get Placeholder
        String _mpPlaceholder = getResources().getString(R.string.money) + " " + currentCharacter.get_masteryPoints();
        // Set Texts
        _masteryPoints.setText(_mpPlaceholder);
        _dmgStatus.setText(currentCharacter.get_powerStat());
        _speedStatus.setText(currentCharacter.get_asStat());
        _vitalityStatus.setText(currentCharacter.get_hpStat());

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
