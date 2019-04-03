package com.example.franciscogarzil.m8_game.PKG_ACTIVITIES;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscogarzil.m8_game.PKG_CONNECTION.ConnectorSQL;
import com.example.franciscogarzil.m8_game.PKG_GAME.inGame;
import com.example.franciscogarzil.m8_game.R;

import static com.example.franciscogarzil.m8_game.PKG_ACTIVITIES.MainMenu.currentCharacter;

public class CharacterPage extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_page);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        _masteryPoints.setText(String.valueOf(_mpPlaceholder));
        _dmgStatus.setText(String.valueOf(currentCharacter.get_powerStat()));
        _speedStatus.setText(String.valueOf(currentCharacter.get_asStat()));
        _vitalityStatus.setText(String.valueOf(currentCharacter.get_hpStat()));

        // Buttons
        Button _newGame = findViewById(R.id.btnNewGame);
        Button _library = findViewById(R.id.btnLibrary);
        // On Click Listeners:
        _newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainMenu.currentCharacter = ConnectorSQL.getCharacter(currentCharacter.get_name());
                } catch (ClassNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Error while refreshing data.", Toast.LENGTH_LONG).show();
                }
                // Start new game:
                Intent myIntent = new Intent(CharacterPage.this, inGame.class);
                CharacterPage.this.startActivity(myIntent);

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
    @Override
    public void onBackPressed () {

    }
}
