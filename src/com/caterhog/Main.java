package com.caterhog;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Locale;
import java.util.Date;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        //test от мурлыки
        //test #2
        /////////////////////////////////////////////////
        //test 3
        Locale.setDefault(Locale.ENGLISH);
        //Income ins = new Income();
        //ins.incomeIns(500, "Не Мурлычьи деньги");
       // Consumption cons = new Consumption();
        //cons.consumptionIns(1000, "Расходы на конфеты");
        //Balance balance = new Balance();
        //balance.monthConsumption();
        //Insert.testIncome(500, "Не НЕ Мурлычьи деньги");
        //balance.weekBalance();
        //Реализуем пользовательский интерфейс
        Scanner scan = new Scanner(System.in);
        ChekInput ch = new ChekInput();
       /* String z = ch.readString1(scan);
        System.out.println(z);
        cons.consumptionIns(1000,z);
        return;
            } */
        //System.out.println("    Менеджер расходов");
       // System.out.println("Укажите имя пользователя: ");
       // String username = ch.readString(scan);
        User user = new User();
        int user_id=user.speakWithUser();
        System.out.println("ID пользователя: " + user_id);
        System.out.println("Выберите желаемое действие: ");
        ch.mainMenu();
        int c;
        while ((c = ch.readNum(scan)) != 5) {

            switch (c) {
                case 1:
                    System.out.println("Укажите дату в формате dd.mm.yy или нажмите Enter для использования текущей даты: "); // реализована проверка валидности формата даты
                    scan.nextLine();
                    String data = ch.readString1(scan);
                    while (!(ch.isValidDate(data))) {
                        System.out.println("Повторите ввод даты в формате dd.mm.yy или нажмите Enter для использования текущей даты: ");
                        data =ch.readString1(scan);
                    }
                    /*if (data.equals("")) {
                        data = "sysdate";
                    }*/
                    System.out.print("Укажите сумму расхода: ");
                    double a = ch.readDouble(scan);
                    System.out.print("Укажите категорию расхода: ");
                    ch.showCategory();
                    int t = ch.readCategory(scan);
                    scan.nextLine();
                    System.out.print("Укажите комментарий: ");
                    String b = ch.readString1(scan);
                    Expense.consumptionIns(a, t, b, data, user_id);
                    break;
                case 2:
                    System.out.print("Укажите сумму дохода: ");
                    double d = ch.readDouble(scan);
                    scan.nextLine();
                    System.out.print("Укажите источник дохода: ");
                    String e = ch.readString(scan);
                    Income.incomeIns(d, e,"kot");
                    break;
                case 3:
                    System.out.print("Статистика: ");
                    break;
                case 4:
                    System.out.print("Коррекция: ");
                    break;
                case 5:
                    System.out.println("Выходим...");
                    return;
                default:
                    System.out.print("Неверный выбор... ");
                    break;
            }
            ch.mainMenu();
        }
    }
}
