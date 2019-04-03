package com.example.franciscogarzil.m8_game.PKG_ACTIVITIES;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.franciscogarzil.m8_game.PKG_CLASS.Character;
import com.example.franciscogarzil.m8_game.PKG_CONNECTION.ConnectorSQL;
import com.example.franciscogarzil.m8_game.R;

public class NewCharacter extends AppCompatActivity {
    EditText EditTextNewCharacter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_character);
        getSupportActionBar().hide();

    }

    public void createNewCharacter(View v) throws ClassNotFoundException {
        try {
            EditTextNewCharacter = (EditText) findViewById(R.id.EditTextName);
            String newCharacter = EditTextNewCharacter.getText().toString();

            ConnectorSQL.insertNewCharacter(newCharacter, Login.currentUserName);

            Intent myIntent = new Intent(NewCharacter.this, MainMenu.class);
            NewCharacter.this.startActivity(myIntent);
        }catch(Exception e){
            msToast("Error" + e);
        }
    }

    public void msToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
