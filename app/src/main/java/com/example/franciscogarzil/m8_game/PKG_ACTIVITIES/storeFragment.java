package com.example.franciscogarzil.m8_game.PKG_ACTIVITIES;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscogarzil.m8_game.PKG_CLASS.Character;
import com.example.franciscogarzil.m8_game.PKG_CONNECTION.ConnectorSQL;
import com.example.franciscogarzil.m8_game.R;

public class storeFragment extends AppCompatActivity implements View.OnClickListener {
    Character character;
    TextView _dmgStatus, _speedStatus,_vitalityStatus, _masteryPoints;
    String _mpPlaceholder,_powerPlaceholder,_speedPlaceholder,_vitalityPlaceholder;
    ImageButton _goBack;
    Button _incDmg,_incSpeed,_incVitality;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store);

        getReferences();
        refreshStats();

        // On Click Listeners:
        _goBack.setOnClickListener(this);
        _incDmg.setOnClickListener(this);
        _incSpeed.setOnClickListener(this);
        _incVitality.setOnClickListener(this);
    }

    private void getReferences() {
        character = MainMenu.currentCharacter;
        _dmgStatus = findViewById(R.id.txtStatusDmg);
        _speedStatus = findViewById(R.id.txtStatusSpeed);
        _vitalityStatus = findViewById(R.id.txtStatusVitality);
        _masteryPoints = findViewById(R.id.masteryPoints);
        // Initialize Buttons:
        _goBack = findViewById(R.id.btnFkGoBack);
        _incDmg = findViewById(R.id.btnIncrementDmg);
        _incSpeed = findViewById(R.id.btnIncrementSpeed);
        _incVitality = findViewById(R.id.btnIncrementVitality);
    }

    private void refreshStats() {
        // Get Placeholder
        _mpPlaceholder = getResources().getString(R.string.money) + " " + character.get_masteryPoints();
        _powerPlaceholder = String.valueOf(character.get_powerStat());
        _speedPlaceholder = String.valueOf(character.get_asStat());
        _vitalityPlaceholder = String.valueOf(character.get_hpStat());
        // Set Text
        _masteryPoints.setText(String.valueOf(_mpPlaceholder));
        _dmgStatus.setText(_powerPlaceholder);
        _speedStatus.setText(_speedPlaceholder);
        _vitalityStatus.setText(_vitalityPlaceholder);
    }

    public void msToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        // On Click Switch:
        switch (v.getId()) {
            case R.id.btnFkGoBack:
                // Go Back to the CharacterPage:
                Intent myIntent = new Intent(storeFragment.this, CharacterPage.class);
                storeFragment.this.startActivity(myIntent);
                msToast("back.");
                break;
            // Increment Buttons
            case R.id.btnIncrementDmg:
                // Damage:
                try {
                    ConnectorSQL.incrementStat("power_stat", character.get_name());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnIncrementSpeed:
                // Speed:
                try {
                    ConnectorSQL.incrementStat("as_stat", character.get_name());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnIncrementVitality:
                // Vitality:
                try {
                    ConnectorSQL.incrementStat("hp_stat", character.get_name());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }

        refreshStats();
    }
}
