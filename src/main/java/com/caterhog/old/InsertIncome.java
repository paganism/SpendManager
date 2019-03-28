package com.caterhog;

import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.Locale;
import java.util.logging.*;

public class InsertIncome {

    public static void main(String[] args) {
        //incIns(250, "Tetris");

    }

    public static void incIns(double x, String y) {
        Locale.setDefault(Locale.ENGLISH);
        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url = "jdbc:oracle:thin:@montreal:1521:xe";//jdbc:oracle:thin:@montreal:1521:xe

        //Имя пользователя БД
        String name = "test";
        //Пароль
        String password = "123";
        try {
            //Загружаем драйвер
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement = null;

            statement = connection.createStatement();
            //Выполним запрос

            //2.PreparedStatement: предварительно компилирует запросы,
            //которые могут содержать входные параметры
            PreparedStatement preparedStatement = null;
            // ? - место вставки нашего значеня
            //preparedStatement = connection.prepareStatement(
            //        "SELECT * FROM spend where id >= ? and id <= ? and balance <> 0");
            //Устанавливаем в нужную позицию значения определённого типа
            // preparedStatement.setInt(1, 2);
            // preparedStatement.setInt(2, 11);
            //preparedStatement.setInt(3, 0);
            //выполняем запрос

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO spend (income, description) values(?, ?)");
            //preparedStatement.setString(1, "1500");
            preparedStatement.setDouble(1, x);
            preparedStatement.setString(2, y);
            //метод принимает значение без параметров
            //темже способом можно сделать и UPDATE
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(
                    "UPDATE spend set balance = (select sum(income)-sum(consumption) from spend) where id=(select max(id) from spend)");
            //preparedStatement.setString(1, "1500");
            preparedStatement.executeUpdate();


        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Select.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Select.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
