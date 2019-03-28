package com.caterhog;
import java.util.Locale;
//Locale.setDefault(Locale.ENGLISH);
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import  java.sql.ResultSet;
import java.sql.*;
import oracle.jdbc.driver.*;
public class Hello {

    final private static String driverName = "oracle.jdbc.driver.OracleDriver";
    private static String url;
    //Locale.setDefault(java.util.Locale.ENGLISH);
    final private static String server = "montreal";
    final private static String port = "1521";
    final private static String sid = "xe";
    final private static String username = "test";
    final private static String password = "123";
    private final static boolean AUTO_COMMIT_MODE = true;
    private static Connection connection;
    private static boolean isConnected = false;
    private static boolean connect() {
        try {
            url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;
            System.out.println(url);
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("connecting: " + url);
            if(connection.equals(null))
                isConnected = false;
            else
                isConnected = true;
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            isConnected = false;
        } catch (SQLException e) {
            System.out.println("SQLException\n" + e.getMessage());
            isConnected = false;
        }
        return isConnected;
    }
    public static void main(String[] args) {
        Locale.setDefault(java.util.Locale.ENGLISH);
        if (connect()) System.out.println("connected");
        else System.out.println("not connected");
        Hello ins = new Hello();
        ins.insertDB(1000);

    }
    public void insertDB(double money){

        String insrtSQL =  "INSERT INTO spend (ID) VALUES (money); commit;";
    }



}

