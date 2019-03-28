package com.example.franciscogarzil.m8_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            con.setAutoCommit(true);
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
                return username +  " registered";
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

    public static String checkUsername(String username, String password) throws ClassNotFoundException {
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
                    return "1";
                } else { //Incorrect
                    return "0";
                }
            } catch (SQLException e) {  //Sql error
                return "Sql Error";
            }
        } else {
            return "Can't connect to the server";
        }
    }

    public static String insertNewCharacter(String name, String username) throws ClassNotFoundException {
        Connection con = getConnection(); //Get connection
        if (con != null) {
            Statement stmt;
            String sql = "INSERT INTO Characters (pk_name, fk_username) VALUES "
                    + "('" + name + "','" + username + "')";
            try {
                stmt = con.createStatement();
                stmt.executeUpdate(sql); //Do insert

                con.commit(); //Commit
                con.close(); //Close connection
                return name +  " created";
            } catch (SQLException e) { //Error sql
                return "SQL Error while inserting new user: " + username + ".";
            }
        } else {
            return "Can't establish server connection.";
        }
    }

    public static ArrayList<Character> getListCharacters(String username) throws ClassNotFoundException {
        Connection con = getConnection(); //Get connection
        ArrayList<Character> al = new ArrayList<Character>();
        if (con != null) { //If connected
            Statement stmt = null;
            ResultSet rs;
            String sqlSelect = "SELECT * FROM Characters WHERE fk_username = '" + username + "';";
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sqlSelect);
                while(rs.next()) {
                    Character c = new Character(rs.getString(1), rs.getString(2), rs.getInt(3),
                            rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getDate(7));
                    al.add(c);
                }
            return al;
            } catch (SQLException e) { //Sql error
            }
        }
        return null;
    }

    public static Character getCharacter(String nameCharacter) throws ClassNotFoundException {
        Connection con = getConnection(); //Get connection
        ArrayList<Character> al = new ArrayList<Character>();
        if (con != null) { //If connected
            Statement stmt = null;
            ResultSet rs;
            String sqlSelect = "SELECT * FROM Characters WHERE pk_name = '" + nameCharacter + "';";
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sqlSelect);
                rs.next();
                Character c = new Character(rs.getString(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getDate(7));
                return c;
            } catch (SQLException e) { //Sql error
                return null;
            }
        }
        return null;
    }

    public static String incrementStat(String stat, String nameCharacter) throws ClassNotFoundException{
        Connection con = getConnection(); //Get connection
        if (con != null) { //If connected
            Statement stmt = null;
            String sqlUpdate = "UPDATE Characters" +
                    "set " + stat + " = " +
                    "(Select " + stat + "from Characters where pk_name = " + nameCharacter + ") + 1" +
                    "WHERE pk_name = " + nameCharacter + ";";
            try {
                stmt = con.createStatement();
                stmt.executeUpdate(sqlUpdate);
                con.commit();
                return nameCharacter + "incremented his stats";
            } catch (SQLException e) { //Sql error
                return e.getMessage();
            }
        }else {
            return "Can't connect to the server";
        }

    }
}
