package com.example.franciscogarzil.m8_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import android.os.StrictMode;

public class ConnectorSQL {

    public static Connection getConnection() throws ClassNotFoundException { //Función para conectar al server
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); //Driver
            //connection Establishment
            Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://SQLServer-PC:1433;databaseName=WOLF_DUNGEON;user=sa;password=Monlau2019"); //Url
            con.setAutoCommit(false);
            return con; //return con
        } catch (SQLException e) { //If no connection
            return null; //return null
        }
    }

    public static String insertUser(String username, String password, String salt) throws ClassNotFoundException {
        Connection con = getConnection(); //Get connection
        if (con != null) {
            Statement stmt;
            String sql = "INSERT INTO Users (pk_username, password) VALUES "
                    + "('" + username + "','" + password + "')";
            try {
                stmt = con.createStatement();
                stmt.executeUpdate(sql); //Do insert

                con.commit(); //Commit
                con.close(); //Close connection
                return username +  "registered";
            } catch (SQLException e) { //Error sql
                return "SQL Error while inserting new user: " + username + ".";
            }
        } else {
            return "Can't establish server connection.";
        }
    }

    public static boolean checkUserExists(String username) throws ClassNotFoundException {
        Connection con = getConnection(); //Get connection
        if (con != null) { //If connected
            Statement stmt;
            ResultSet rs;
            String sqlSelect = "SELECT COUNT(*) FROM Users WHERE pk_username = '" + username + "'";
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sqlSelect);
                rs.next();
                int rowCount = rs.getInt(1);
                if (rowCount > 0) { //Exists
                    con.close(); //Close connection
                    return true;
                } else { //No existe
                    con.close(); //Close connection
                    return false;
                }
            } catch (SQLException e) { //Sql error
                return false;
            }
        } else { //If no connection
            return false;
        }
    }

    public static boolean checkUsername(String username, String password) throws ClassNotFoundException {
        Connection con = getConnection(); //Get connection
        if (con != null) { //If connected
            Statement stmt = null;
            ResultSet rs;
            String sqlSelect = "SELECT count(*) FROM Users WHERE pk_username = '" + username + "' and password = '" + password + "';";
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sqlSelect);
                rs.next();
                int rowCount = rs.getInt(1);
                if (rowCount > 0) { //Correct
                    return true;
                } else { //Incorrect
                    return false;
                }
            } catch (SQLException e) {  //Sql error
            }
        } return false;

    }


}
