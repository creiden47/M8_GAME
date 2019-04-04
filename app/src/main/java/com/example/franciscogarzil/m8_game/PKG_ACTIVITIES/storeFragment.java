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

import static com.example.franciscogarzil.m8_game.PKG_ACTIVITIES.MainMenu.currentCharacter;

public class storeFragment extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store);
        getSupportActionBar().hide();

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

        try {
            MainMenu.currentCharacter = ConnectorSQL.getCharacter(currentCharacter.get_name());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void msToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    public void incrementDmg(View view) throws ClassNotFoundException {
        msToast(ConnectorSQL.incrementStat("power_stat" , currentCharacter.get_name()));
        TextView dmg = findViewById(R.id.txtStatusDmg);
        MainMenu.currentCharacter = ConnectorSQL.getCharacter(MainMenu.currentCharacter.get_name());
        dmg.setText(String.valueOf(MainMenu.currentCharacter.get_powerStat()));
        TextView mp = findViewById(R.id.masteryPoints);
        mp.setText(getResources().getString(R.string.money) + " " + MainMenu.currentCharacter.get_masteryPoints());
    }
    public void incrementSpeed(View view) throws ClassNotFoundException {
        msToast(ConnectorSQL.incrementStat("as_stat" , currentCharacter.get_name()));
        TextView speed = findViewById(R.id.txtStatusSpeed);
        currentCharacter = ConnectorSQL.getCharacter(currentCharacter.get_name());
        speed.setText(String.valueOf(currentCharacter.get_asStat()));
        TextView mp = findViewById(R.id.masteryPoints);
        mp.setText(getResources().getString(R.string.money) + " " + currentCharacter.get_masteryPoints());
    }
    public void incrementVitality(View view) throws ClassNotFoundException {
        msToast(ConnectorSQL.incrementStat("hp_stat" , currentCharacter.get_name()));
        TextView vitality = findViewById(R.id.txtStatusVitality);
        currentCharacter = ConnectorSQL.getCharacter(currentCharacter.get_name());
        vitality.setText(String.valueOf(currentCharacter.get_hpStat()));
        TextView mp = findViewById(R.id.masteryPoints);
        mp.setText(getResources().getString(R.string.money) + " " + currentCharacter.get_masteryPoints());
    }


    public void goBack(View view) {
        Intent myIntent = new Intent(storeFragment.this, CharacterPage.class);
        storeFragment.this.startActivity(myIntent);
    }
}
