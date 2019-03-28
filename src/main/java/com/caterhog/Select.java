package com.caterhog;

/**
 * Created by yuriy on 30.11.2016.
 */
import java.sql.*;
import java.util.Locale;
import java.util.logging.*;

public class Select {

    public static void main(String[] args) {
        Locale.setDefault(java.util.Locale.ENGLISH);
        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        //String url = "jdbc:oracle:thin:@montreal:1521:xe";//jdbc:oracle:thin:@montreal:1521:xe

        //Имя пользователя БД
        //String name = "test";
        //Пароль
        //String password = "123";
        try {
            CreateConnection createconnect = new CreateConnection();
            Connection connect = createconnect.createConnect();
            //Загружаем драйвер
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //System.out.println("Драйвер подключен");
            //Создаём соединение
            //connection = DriverManager.getConnection(url, name, password);
            //System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            /*Statement statement = null;

            statement = connection.createStatement();
            //Выполним запрос
            ResultSet result1 = statement.executeQuery(
                    "SELECT id,income,balance, op_date FROM spend where id =1");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println("Доход #" + result1.getInt("income")
                        + "\t Номер в базе #" + result1.getInt("id")
                        + "\t Баланс #" + result1.getInt("balance") );
            }
            // Вставить запись
           /* statement.executeUpdate(
                    "INSERT INTO spend (description) values('name')");
            //Обновить запись
            statement.executeUpdate(
                    "UPDATE spend SET description = 'admin' where id = 2");
*/


            //2.PreparedStatement: предварительно компилирует запросы,
            //которые могут содержать входные параметры
            PreparedStatement preparedStatement = null;
            // ? - место вставки нашего значеня
            preparedStatement = connect.prepareStatement(
                    "SELECT * FROM spend where id >= ? and id <= ? and balance <> 0");
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 11);
            //preparedStatement.setInt(3, 0);
            //выполняем запрос
            ResultSet result2 = preparedStatement.executeQuery();

            System.out.println("Выводим PreparedStatement");
            while (result2.next()) {
                System.out.println("Номер в выборке #" + result2.getRow()
                        + "\t Номер в базе #" + result2.getInt("id")
                        + "\t" + result2.getString("description")
                        + "\t" + result2.getString("op_date")
                        + "\t Доход " + result2.getString("income")
                        + "\t Расход " + result2.getString("consumption")
                );
            }

            preparedStatement = connect.prepareStatement(
                    "INSERT INTO spend (income, description) values(?, ?)");
            preparedStatement.setString(1, "1500");
            preparedStatement.setString(2, "Зарплата МТС");
            //метод принимает значение без параметров
            //темже способом можно сделать и UPDATE
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement(
                    "UPDATE spend set balance = (select sum(income)-sum(consumption) from spend) where id=(select max(id) from spend)");
            //preparedStatement.setString(1, "1500");
            preparedStatement.executeUpdate();



          /*  //3.CallableStatement: используется для вызова хранимых функций,
            // которые могут содержать входные и выходные параметры
            CallableStatement callableStatement = null;
            //Вызываем функцию myFunc (хранится в БД)
            callableStatement = connection.prepareCall(
                    " { call myfunc(?,?) } ");
            //Задаём входные параметры
            callableStatement.setString(1, "Dima");
            callableStatement.setString(2, "Alex");
            //Выполняем запрос
            ResultSet result3 = callableStatement.executeQuery();
            //Если CallableStatement возвращает несколько объектов ResultSet,
            //то нужно выводить данные в цикле с помощью метода next
            //у меня функция возвращает один объект
            result3.next();
            System.out.println(result3.getString("MESSAGE"));
            //если функция вставляет или обновляет, то используется метод executeUpdate()
*/
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