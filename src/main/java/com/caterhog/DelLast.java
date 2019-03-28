package com.caterhog;

import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yuriy on 14.12.2016.
 */
public class DelLast {
    public static StringBuilder delLastIncome(String username) {
        Locale.setDefault(Locale.ENGLISH);
        StringBuilder res = new StringBuilder();
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "delete from spend where id=(select max(id) from spend where username =? and income <> 0)");
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("Последняя операция внесения дохода удалена: ");
            //
            preparedStatement = connection.prepareStatement("SELECT balance FROM spend where id = (select max(id) FROM spend where username=?)");
            preparedStatement.setString(1, username);
            ResultSet result1 = preparedStatement.executeQuery();
            System.out.println("Текущий баланс: ");
            //
            while (result1.next()) {
                System.out.println("Баланс: " + result1.getDouble("balance"));

                res.append("Текущий баланс: " + result1.getDouble("balance"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(DelLast.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(DelLast.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
    public static StringBuilder delLastExpense(String username) {
        Locale.setDefault(Locale.ENGLISH);
        StringBuilder res = new StringBuilder();
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "delete from spend where id=(select max(id) from spend where username =? and consumption <> 0)");
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("Последняя операция внесения расхода удалена: ");
            //
            preparedStatement = connection.prepareStatement("SELECT balance FROM spend where id = (select max(id) FROM spend where username=?)");
            preparedStatement.setString(1, username);
            ResultSet result1 = preparedStatement.executeQuery();
            System.out.println("Текущий баланс: ");
            //
            while (result1.next()) {
                System.out.println("Баланс: " + result1.getDouble("balance"));

                res.append("Текущий баланс: " + result1.getDouble("balance"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(DelLast.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(DelLast.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

}
