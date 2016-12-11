package com.caterhog;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yuriy on 10.12.2016.
 */
public class User {
    public static void main(String[] args) {
    }

    ChekInput ch = new ChekInput();
    Scanner scan = new Scanner(System.in);
    int id;

    public int speakWithUser() {
        System.out.println("    Здравсвуйте! Вас приветсвует менеджер расходов");
        System.out.println("Если вы уже были зарегистрированы, укажите имя, иначе введите новое имя");
        String username = scan.next();

        try {
            Connection connection = CreateConnection.createConnect();
            Statement statement = null;
            statement = connection.createStatement();
            PreparedStatement preparedStatement = null;


            preparedStatement = connection.prepareStatement("SELECT id,username FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");

                System.out.println("Пользователь " + username + " найден! Настало время приключений!");
                return id;
            } else {

                preparedStatement = connection.prepareStatement("INSERT INTO users (username) values(?)");
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
                ResultSet rs1 = statement.executeQuery(
                        "SELECT MAX(id) FROM users");
                if (rs1.next()) {
                    int id = rs1.getInt("max(id)");
                    System.out.println("Пользователь " + username + " добавлен в базу. Добро пожаловать!");
                     return id;
                }


            }


        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return id;
    }


}
