package com.example.franciscogarzil.m8_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    String username = "";
    String exp = "";
    String lvl = "";
    static int number = 0;
    static Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dbconnection
        /*try {
            dbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        mission1();
    }

    private static void dbConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root", "root");

        }catch(Exception ex){
            System.out.println("Error connection:" + ex);
        }
    }

    private static void mission1(){
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

    private static void mission2(){
        int creepLife1 = 100;
        int creepLife2 = 200;
        int evolvedCreepLife1 = 600;
        int evolvedCreepLIde2 = 700;
    }

    private static void bossBattle(){
        int creepLife1 = 100;
        int creepLIfe2 = 200;
        int evolvedCreepLife1 = 600;
        int evolvedCreepLIde2 = 700;
        int bossLife = 2000;
    }
}
