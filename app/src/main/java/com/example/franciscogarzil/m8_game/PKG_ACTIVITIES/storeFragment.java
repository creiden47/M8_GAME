package com.example.franciscogarzil.m8_game.PKG_ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.franciscogarzil.m8_game.PKG_CLASS.Character;
import com.example.franciscogarzil.m8_game.PKG_CONNECTION.ConnectorSQL;
import com.example.franciscogarzil.m8_game.R;

import static com.example.franciscogarzil.m8_game.PKG_ACTIVITIES.MainMenu.currentCharacter;

public class storeFragment extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store);

        // Text Views
        // Welcome message
        TextView welcomeMsg = findViewById(R.id.welcome_msg);
        // Get Placeholder
        String welcome_placeholder = "Welcome " + currentCharacter.get_name();
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
        _dmgStatus.setText("" + currentCharacter.get_powerStat());
        _speedStatus.setText("" + currentCharacter.get_asStat());
        _vitalityStatus.setText("" + currentCharacter.get_hpStat());

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
