package com.caterhog;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Locale;
import java.util.Date;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        //Income ins = new Income();
        //ins.incomeIns(500, "Не Мурлычьи деньги");
        Consumption cons = new Consumption();
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
        System.out.println("    Менеджер расходов");
        System.out.println("Выберите желаемое действие: "
                + "\n 1. Внести расход (введите 1) "
                + "\n 2. Внести доход (введите 2) "
                + "\n 3. Просмотр статистики (введите 3) "
                + "\n 4. Редактирование (введите 4) "
                + "\n 5. Выход (введите 5) ");
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
                    System.out.print("Укажите категорию расхода: "
                            + "\n  0	Любая"
                            + "\n  1	Еда"
                            + "\n  2	Кафе и ресторан"
                            + "\n  3	Развлечения"
                            + "\n  4	Одежда"
                            + "\n  5	Покупки"
                            + "\n  6	Отпуск"
                            + "\n  7	Спорт"
                            + "\n  8	Путешествия"
                            + "\n  9	Подарки"
                            + "\n  10  Основное"
                            + "\n  11  Дети"
                            + "\n");
                    int t = ch.readCategory(scan);
                    scan.nextLine();
                    System.out.print("Укажите комментарий: ");
                    String b = ch.readString1(scan);
                    Expense.consumptionIns(a, t, b, data);
                    break;
                case 2:
                    System.out.print("Укажите сумму дохода: ");
                    double d = ch.readDouble(scan);
                    scan.nextLine();
                    System.out.print("Укажите источник дохода: ");
                    String e = ch.readString(scan);
                    Income.incomeIns(d, e);
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
            System.out.println("Выберите желаемое действие: "
                    + "\n 1. Внести расход (введите 1) "
                    + "\n 2. Внести доход (введите 2) "
                    + "\n 3. Просмотр статистики (введите 3) "
                    + "\n 4. Редактирование (введите 4) "
                    + "\n 5. Выход (введите 5) ");
        }
    }
}
