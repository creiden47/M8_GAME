package com.example.franciscogarzil.m8_game;

import android.app.Activity;
import android.widget.TextView;

import static com.example.franciscogarzil.m8_game.MainMenu.currentCharacter;

class StatusInit {

    StatusInit(Activity _activity) {

        // Text Views
        // Welcome message
        TextView welcomeMsg = _activity.findViewById(R.id.welcome_msg);
        // Get Placeholder
        String welcome_placeholder = _activity.getResources().getString(R.string.welcome) + " " + currentCharacter.get_name();
        // Set Texts
        welcomeMsg.setText(welcome_placeholder);
        // Character Status
        // Text Views
        TextView _dmgStatus = _activity.findViewById(R.id.txtStatusDmg);
        TextView _speedStatus = _activity.findViewById(R.id.txtStatusSpeed);
        TextView _vitalityStatus = _activity.findViewById(R.id.txtStatusVitality);
        TextView _masteryPoints = _activity.findViewById(R.id.masteryPoints);
        // Get Placeholder
        String _mpPlaceholder = _activity.getResources().getString(R.string.money) + " " + currentCharacter.get_masteryPoints();
        // Set Texts
        _masteryPoints.setText(_mpPlaceholder);
        _dmgStatus.setText("" + currentCharacter.get_powerStat());
        _speedStatus.setText("" + currentCharacter.get_asStat());
        _vitalityStatus.setText("" + currentCharacter.get_hpStat());
    }


}
