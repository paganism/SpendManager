package com.caterhog;

import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Balance {

    public static void main(String[] args) {

    }

    public static  void dayBalance(int user_id) {
        Locale.setDefault(Locale.ENGLISH);
        //Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        //String url = "jdbc:oracle:thin:@montreal:1521:xe";//jdbc:oracle:thin:@montreal:1521:xe
        //Имя пользователя БД
        //String name = "test";
        //Пароль
        //String password = "123";
        try {
            //Загружаем драйвер
            //Connection connection = null;
            Connection connection = CreateConnection.createConnect();

           // Class.forName("oracle.jdbc.driver.OracleDriver");
            //System.out.println("Драйвер подключен");
            //Создаём соединение
           // connection = DriverManager.getConnection(url, name, password);
            //System.out.println("Соединение установлено");
            //Далее нужно показать пользователю то, что он добавил и текущий баланс
            //Statement: используется для простых случаев без параметров
            //Statement statement = null;
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = null;
            //Выполним запрос
            preparedStatement = connection.prepareStatement("SELECT id, income, consumption, balance, description, op_date FROM spend where user_id = ? and op_date between trunc(sysdate) and sysdate");
            preparedStatement.setInt(1, user_id);
            ResultSet result = preparedStatement.executeQuery();
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Движение денег за сегодня: ");
            while (result.next()) {
                System.out.println("Номер транзакции: " + result.getInt("id")
                        + "\t Дата: " + result.getString("op_date")
                        + "\t Доход: " + result.getInt("income")
                        + "\t Расход: " + result.getInt("consumption")
                        + "\t Баланс: " + result.getInt("balance")
                        + "\t Описание: " + result.getString("description"));
            }


        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if ( CreateConnection.createConnect() != null) {
                try {
                     CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static  void weekBalance(int user_id) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            //Statement: используется для простых случаев без параметров
            //Statement statement = null;

            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("SELECT id, income, consumption, balance, description, op_date FROM spend where user_id = ? and op_date between sysdate-7 and sysdate order by id");
            preparedStatement.setInt(1, user_id);
            //Выполним запрос
             ResultSet result = preparedStatement.executeQuery();
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Движение денег за сутки: ");
            while (result.next()) {
                System.out.println("Номер транзакции: " + result.getInt("id")
                        + "\t Дата: " + result.getString("op_date")
                        + "\t Доход: " + result.getInt("income")
                        + "\t Расход: " + result.getInt("consumption")
                        + "\t Баланс: " + result.getInt("balance")
                        + "\t Описание: " + result.getString("description"));
            }


        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if ( CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static  void monthBalance(int user_id) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            //Statement: используется для простых случаев без параметров
            //Statement statement = null;
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("SELECT id, income, consumption, balance, description, op_date FROM spend where user_id =? and op_date between sysdate-30 and sysdate order by id");
            preparedStatement.setInt(1, user_id);

            //Выполним запрос
            ResultSet result = preparedStatement.executeQuery();
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Движение денег за месяц: ");
            while (result.next()) {
                System.out.println("Номер транзакции: " + result.getInt("id")
                        + "\t Дата: " + result.getString("op_date")
                        + "\t Доход: " + result.getInt("income")
                        + "\t Расход: " + result.getInt("consumption")
                        + "\t Баланс: " + result.getInt("balance")
                        + "\t Описание: " + result.getString("description"));
            }


        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if ( CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static  void dayIncome(int user_id) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            //Statement: используется для простых случаев без параметров
            //Statement statement = null;
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("SELECT sum(income) FROM spend where user_id=? and op_date between trunc(sysdate) and sysdate");
            preparedStatement.setInt(1, user_id);

            //Выполним запрос
            ResultSet result = preparedStatement.executeQuery();
             //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Движение денег за день: ");
            while (result.next()) {
                System.out.println("Доход: " + result.getInt("sum(income)"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if ( CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static  void monthIncome(int user_id) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            //Statement: используется для простых случаев без параметров
            //Statement statement = null;
            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("SELECT sum(income) FROM spend where user_id=? and op_date between trunc(sysdate-30) and sysdate");
            preparedStatement.setInt(1, user_id);

            //Выполним запрос
            ResultSet result = preparedStatement.executeQuery();
             //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Движение денег за 30 дней: ");
            while (result.next()) {
                System.out.println("Доход: " + result.getInt("sum(income)"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if ( CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static  void dayConsumption(int user_id) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            //Statement: используется для простых случаев без параметров
            //Statement statement = null;

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("SELECT sum(consumption) FROM spend where user_id=? and op_date between trunc(sysdate) and sysdate");
            preparedStatement.setInt(1, user_id);
            //Выполним запрос
            ResultSet result = preparedStatement.executeQuery();
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Движение денег за день: ");
            while (result.next()) {
                System.out.println("Расход: " + result.getInt("sum(consumption)"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if ( CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static  void monthConsumption(int user_id) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            //Загружаем драйвер
            Connection connection = CreateConnection.createConnect();
            //Statement: используется для простых случаев без параметров
            //Statement statement = null;
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement("\"SELECT sum(consumption) FROM spend where where user_id=? and op_date between trunc(sysdate-30) and sysdate");
            preparedStatement.setInt(1, user_id);
            //Выполним запрос
            ResultSet result = preparedStatement.executeQuery();
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Движение денег за 30 дней: ");
            while (result.next()) {
                System.out.println("Расход: " + result.getInt("sum(consumption)"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if ( CreateConnection.createConnect() != null) {
                try {
                    CreateConnection.createConnect().close();
                } catch (SQLException ex) {
                    Logger.getLogger(Balance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
