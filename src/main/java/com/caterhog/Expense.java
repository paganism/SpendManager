package com.caterhog;

import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Expense {

    public static void main(String[] args) {

    }

    public static StringBuilder consumptionIns(double x, int t, String y, String data, int user_id, String username) {
        Locale.setDefault(Locale.ENGLISH);
        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        //String url = "jdbc:postgresql://localhost:5432/yozhdb";//jdbc:oracle:thin:@montreal:1521:xe
        //Имя пользователя БД
        //String name = "yozh";
        //Пароль
        //String password = "yozh";
        StringBuilder res = new StringBuilder("<b>Текущая операция: </b>\n");
        try {
            //2 Class.forName("org.postgresql.Driver");
            //System.out.println("Драйвер подключен");
            //Создаём соединение
            //1 connection = DriverManager.getConnection(url, name, password);
            CreateConnection createconnect = new CreateConnection();
            Connection connect = createconnect.createConnect();
            //System.out.println("Соединение установлено");
            //PreparedStatement: предварительно компилирует запросы, которые могут содержать входные параметры
            PreparedStatement preparedStatement = null;
            if (data.equals("")) {
                preparedStatement = connect.prepareStatement("INSERT INTO spend (consumption, category_id, description, user_id, username) values(?, ?, ?, ?, ?)");
                //preparedStatement.setString(1, "1500");
                preparedStatement.setDouble(1, x);
                preparedStatement.setDouble(2, t);
                preparedStatement.setString(3, y);
                preparedStatement.setInt(4, user_id);
                preparedStatement.setString(5, username);
                //метод принимает значение без параметров
                //темже способом можно сделать и UPDATE
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = connect.prepareStatement("INSERT INTO spend (consumption, category_id, description, op_date, user_id, username) values(?, ?, ?, to_date(?, 'dd.mm.yy'), ?, ?)");
                //preparedStatement.setString(1, "1500");
                preparedStatement.setDouble(1, x);
                preparedStatement.setDouble(2, t);
                preparedStatement.setString(3, y);
                preparedStatement.setString(4, data);
                preparedStatement.setInt(5, user_id);
                preparedStatement.setString(6, username);
                //метод принимает значение без параметров
                //темже способом можно сделать и UPDATE
                preparedStatement.executeUpdate();
            }
            preparedStatement = connect.prepareStatement(
                    //"update spend set balance = (select balance from spend where id in (select max(id)-1 from spend)) - consumption where id in (select max(id) from spend)");
            "UPDATE spend set balance = (select sum(income)-sum(consumption) from spend where username = ?) where id=(select max(id) from spend where username = ?)");
            //чтобы выполнить update, нужо вызвать executeUpdate()
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            //Далее нужно показать пользователю то, что он потратил и текущий баланс
            //Statement используется для простых случаев без параметров
            Statement statement = null;

            statement = connect.createStatement();
            //Выполним запрос
            ResultSet result = statement.executeQuery(
                    "SELECT id, (select username from users where id = user_id) as username, consumption, balance, (select name from category where id = category_id) as category, description, op_date FROM spend where id =(select max(id) from spend)");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Текущее финансовое состояние: ");
            while (result.next()) {
                System.out.println("id операции: " + result.getInt("id")
                        + "\t Дата: " + result.getString("op_date")
                        + "\t Пользователь: " + result.getString("username")
                        + "\t Расход: " + result.getInt("consumption")
                        + "\t Баланс: " + result.getInt("balance")
                        + "\t Категория: " + result.getString("category")
                        + "\t Описание: " + result.getString("description")
                        + "\n");
                res.append("Вы внесли расход: " + result.getDouble("consumption") + "\n");
                res.append("Баланс: " + result.getDouble("balance") + "\n");
                res.append("Описание: " + result.getString("description"));
            }


        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Expense.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Expense.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
}
