package com.example.franciscogarzil.m8_game.PKG_ACTIVITIES;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.franciscogarzil.m8_game.PKG_CONNECTION.ConnectorSQL;
import com.example.franciscogarzil.m8_game.R;

import java.util.Timer;


public class Login extends AppCompatActivity {
    String username = "";
    String password = "";
    public static String currentUserName;
    EditText EditTextUsername = null;
    EditText EditTextPassword = null;
    Button btnLogin = null;
    Button btnRegister = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        references();
    }

    @Override
    public void onBackPressed () {

    }

    private void references(){
        EditTextUsername = (EditText) findViewById(R.id.EditTextUsername);
        EditTextPassword = (EditText) findViewById(R.id.EditTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    public void login(View v) throws ClassNotFoundException {
        btnLogin.setBackgroundResource(R.drawable.button_selected);
        btnLogin.setTextColor(getResources().getColor(R.color.colorTextSelected));
        username = EditTextUsername.getText().toString();
        password = EditTextPassword.getText().toString();
        String exists = ConnectorSQL.checkUsername(username, password);

        if(exists == "1"){
            msToast("Login correcto");
            Intent myIntent = new Intent(Login.this, MainMenu.class);
            currentUserName = username;
            Login.this.startActivity(myIntent);
        }else if (exists == "0"){
            msToast("Credenciales incorrectas.");
            btnLogin.setBackgroundResource(R.drawable.button);
            btnLogin.setTextColor(getResources().getColor(R.color.colorTextLight));
        } else {
            msToast(exists);
        }
    }

    public void register(View v) throws ClassNotFoundException {
        btnRegister.setBackgroundResource(R.drawable.button_selected);
        btnRegister.setTextColor(getResources().getColor(R.color.colorTextSelected));
        username = EditTextUsername.getText().toString();
        password = EditTextPassword.getText().toString();
        if(!ConnectorSQL.checkUserExists(username)){
            msToast(ConnectorSQL.insertUser(username, password));
        } else{
            msToast("This username alredy exists.");
            btnRegister.setBackgroundResource(R.drawable.button);
            btnRegister.setTextColor(getResources().getColor(R.color.colorTextLight));
        }
    }

    public void msToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
