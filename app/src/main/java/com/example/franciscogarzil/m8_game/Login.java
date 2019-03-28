package com.example.franciscogarzil.m8_game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.util.Timer;


public class Login extends AppCompatActivity {
    String username = "";
    String password = "";
    public static String currentUserName;
    static int number = 0;
    private Timer timer;
    //private MyTimerTask task;
    private Context context;
    private int counter = 25;
    private int interval = 10;
    EditText EditTextUsername = null;
    EditText EditTextPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        references();
    }

    private void references(){
        EditTextUsername = (EditText) findViewById(R.id.EditTextUsername);
        EditTextPassword = (EditText) findViewById(R.id.EditTextPassword);
    }

    public void login(View v) throws ClassNotFoundException {
        Button btnLogin = findViewById(R.id.btnLogin);
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

    public void msToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
