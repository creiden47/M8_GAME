package com.example.franciscogarzil.m8_game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;


public class Login extends AppCompatActivity {
    String username = "";
    String password = "";
    String exp = "";
    String lvl = "";
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
        references();
    }

    private void references(){
        EditTextUsername = (EditText) findViewById(R.id.EditTextUsername);
        EditTextPassword = (EditText) findViewById(R.id.EditTextPassword);
    }

    public void login(View v) throws ClassNotFoundException {
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setBackgroundResource(R.drawable.button_selected);
        btnLogin.setTextColor(getResources().getColor(R.color.colorTextLight));
        username = EditTextUsername.getText().toString();
        password = EditTextPassword.getText().toString();
        boolean exists = ConnectorSQL.checkUsername(username, password);

        if(exists == true){
            msToast("Login correcto");
            Intent myIntent = new Intent(Login.this, MainMenu.class);
            Login.this.startActivity(myIntent);
        }else {
            msToast("Credenciales incorrectas.");
        }
    }

    /*private void dbConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root", "root");

        }catch(Exception ex){
            System.out.println("Error connection:" + ex);
        }
    }

    private void combatStart(){
        int creepHP = 100;
        startTimer();



        if(creepHP == 0){
            stopTimer();
        }
    }

    private void mission1(){
        int creepLife1 = 100;
        int creepLife2 = 200;
        int creepLide3 = 300;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.println(number);
                number++;
            }
        },0, 25*1000);

    }

    private void mission2(){
        int creepLife1 = 100;
        int creepLife2 = 200;
        int evolvedCreepLife1 = 600;
        int evolvedCreepLIde2 = 700;
    }

    private void bossBattle(){
        int creepLife1 = 100;
        int creepLIfe2 = 200;
        int evolvedCreepLife1 = 600;
        int evolvedCreepLIde2 = 700;
        int bossLife = 2000;
    }

    //timer

    public void taskTimer() {
        try {
            --counter;
            if (counter == 0) {
                System.out.println("El enemigo ha utilizado: asdjasd. 7 Segundos.");
                wait(7000);
                counter = 25;


            }
        } catch (Exception e) {
            msToast("An error occurred while starting the timer");
        }
    }

    public void restartTimer(View v) {
        try {



        } catch (Exception e) {
            msToast("The Timer is allready restarted");
        }
    }

    private void startTimer() {
        context = this;
        task = new MyTimerTask(context);
        timer = new Timer("miTimer");
        timer.schedule(task, 0, interval);
    }

    private void stopTimer() {
        timer.cancel();
        timer = null;
        task = null;
    }

    class MyTimerTask extends TimerTask {
        Handler handler = new Handler();
        Context context;

        public MyTimerTask(Context context) {
            this.context = context;
        }

        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    ((Login) context).taskTimer();
                }
            });
        }

    }*/

    public void msToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
