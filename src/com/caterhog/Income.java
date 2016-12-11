package com.caterhog;

import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Income {

    public static void main(String[] args) {

    }

    public static void incomeIns(double x, String y, String username) {
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
            //System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            //System.out.println("Соединение установлено");
            //PreparedStatement: предварительно компилирует запросы, которые могут содержать входные параметры
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("INSERT INTO spend (income, description, username) values(?, ?, ?)");
            //preparedStatement.setString(1, "1500");
            preparedStatement.setDouble(1, x);
            preparedStatement.setString(2, y);
            preparedStatement.setString(3, username);
            //метод принимает значение без параметров
            //темже способом можно сделать и UPDATE
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(
                    //"update spend set balance=income + (select balance from spend where id in (select max(id)-1 from spend)) where id in (select max(id) from spend)");
                    "UPDATE spend set balance = (select sum(income)-sum(consumption) from spend where username = ?) where id=(select max(id) from spend where username = ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            //"UPDATE spend set balance = (select sum(income)-sum(consumption) from spend) where id=(select max(id) from spend)");
            //чтобы выполнить update, нужо вызвать executeUpdate()
            preparedStatement.executeUpdate();

            //Далее нужно показать пользователю то, что он добавил и текущий баланс
            //Statement: используется для простых случаев без параметров
            Statement statement = null;

            statement = connection.createStatement();
            //Выполним запрос
            ResultSet result = statement.executeQuery(
                    "SELECT id, income, balance, description, op_date FROM spend where id =(select max(id) from spend)");
            //result это указатель на первую строку с выборки
            ///test
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Текущее финансовое состояние: ");
            while (result.next()) {
                System.out.println("Номер транзакции: " + result.getInt("id")
                        + "\t Дата: " + result.getString("op_date")
                        + "\t Доход: " + result.getInt("income")
                        + "\t Баланс: " + result.getInt("balance")
                        + "\t Описание: " + result.getString("description"));
            }


        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
