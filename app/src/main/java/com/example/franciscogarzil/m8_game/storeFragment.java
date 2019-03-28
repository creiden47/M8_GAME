package com.example.franciscogarzil.m8_game;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import static com.example.franciscogarzil.m8_game.MainMenu.currentCharacter;

public class storeFragment extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store);

        new StatusInit(this);

        // Initialize Buttons:
        ImageButton _goBack = findViewById(R.id.btnFkGoBack);
        Button _incDmg = findViewById(R.id.btnIncrementDmg);
        Button _incSpeed = findViewById(R.id.btnIncrementSpeed);
        Button _incVitality = findViewById(R.id.btnIncrementVitality);
        // On Click Listeners:
        _goBack.setOnClickListener(this);
        _incDmg.setOnClickListener(this);
        _incSpeed.setOnClickListener(this);
        _incVitality.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Current Character val:
        Character currentCharacter;

        // On Click Switch:
        switch (v.getId()) {
            case R.id.btnFkGoBack:
                // Go Back to the CharacterPage:
                Intent myIntent = new Intent(storeFragment.this, CharacterPage.class);
                storeFragment.this.startActivity(myIntent);
                break;
            // Increment Buttons
            case R.id.btnIncrementDmg:
                // Damage:
                try {
                    currentCharacter = ConnectorSQL.getCharacter(MainMenu.currentCharacter.get_name());
                    ConnectorSQL.incrementStat("power_stat", currentCharacter.get_name());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnIncrementSpeed:
                // Speed:
                try {
                    currentCharacter = ConnectorSQL.getCharacter(MainMenu.currentCharacter.get_name());
                    ConnectorSQL.incrementStat("as_stat", currentCharacter.get_name());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnIncrementVitality:
                // Vitality:
                try {
                    currentCharacter = ConnectorSQL.getCharacter(MainMenu.currentCharacter.get_name());
                    ConnectorSQL.incrementStat("hp_stat", currentCharacter.get_name());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
