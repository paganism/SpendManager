package com.caterhog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yuriy on 06.12.2016.
 */
public class ChekInput {

    public double readDouble(Scanner in) { // метод используется для ввода значений в начальном селекторе программы
        double z = 0;                      // Нельзя указать 0 и число должно быть Finite
        while (true) {
            if (in.hasNextDouble()) {
                z = in.nextDouble();
                if (!Double.isFinite(z) || z <= 0) {
                    System.out.println("Вы ввели некооректное значение! Повторите ввод...");
                    continue;
                } else {
                    return z;
                }
                //break;
            } else {
                System.out.println("Вы ввели некорректное значение. Повторите ввод: ");
                in.next();
            }
        }
    }

    public String readString(Scanner in) {
        String z;
        while (true) {
            if (in.hasNext()) {
                z = in.nextLine();
                return z;
            }
        }
    }

    public static String readString1(Scanner in) { // используется для ввода комментариев
        String z;
        z = in.nextLine();
        return z;
    }

    public int readNum(Scanner in) {
        int z = 0;
        while (true) {
            if (in.hasNextInt()) {
                z = in.nextInt();
                if (z == 0) {
                    System.out.println("Вы ввели некооректное значение Повторите ввод...");
                    continue;
                } else {
                    return z;
                }
                //break;
            } else {
                System.out.println("Вы ввели некорректное значение. Повторите ввод: ");
                in.next();
            }
        }
    }

    public int readCategory(Scanner in) { // используется при проверке введенной категории
        int z = 0;
        while (true) {
            if (in.hasNextInt()) {
                z = in.nextInt();
                if ((z < 0) || (z > 11)) {
                    System.out.println("Пожалуйста введите категорию в диапазоне (1 - 11)...");
                    continue;
                } else {
                    return z;
                }
                //break;
            } else {
                System.out.println("Вы ввели некорректное значение. Повторите ввод: ");
                in.next();
            }
        }
    }

    public String readDate(Scanner in) {
        String formattedDate = "0";
        try {
            String z = in.nextLine();
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            df.setLenient(true);
            Date date = df.parse(z);
            formattedDate = df.format(date);
            System.out.println(formattedDate);

        } catch (Exception e) {
            System.out.println("некорректно введена дата");
            //e.printStackTrace();
        }
        return formattedDate;
    }

    public static boolean isValidDate(String date) { //реализована проверка даты на валидность
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy") {{
            setLenient(false);
        }};

        try {

            sdf.parse(date);
            return true;

        } catch (Exception e) {
        }
        if (date.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        //System.out.println(isValidDate(scan.nextLine())); // false
        //System.out.println(isValidDate("29.02.16")); // true
        mainMenu();
        showCategory();
    }

    public static void mainMenu() {
        Map<Integer, String> actionSelect = new HashMap<Integer, String>();
        actionSelect.put(1, ". Внести расход (введите 1) ");
        actionSelect.put(2, ". Внести доход (введите 2) ");
        actionSelect.put(3, ". Просмотр статистики (введите 3) ");
        actionSelect.put(4, ". Редактирование (введите 4) ");
        actionSelect.put(5, ". Выход (введите 5) ");
        /*for (Integer key : userChoice1.keySet()) {
            System.out.println(key);
        }*/
        for (Map.Entry entry : actionSelect.entrySet()) {
            System.out.println(entry.getKey() + "" + entry.getValue());
        }
    }

    public static void showCategory() {
        Map<Integer, String> categorySelect = new HashMap<Integer, String>();
        categorySelect.put(0,	"Любая");
        categorySelect.put(1,	"Еда");
        categorySelect.put(2,	"Кафе и ресторан");
        categorySelect.put(3,	"Развлечения");
        categorySelect.put(4,	"Одежда");
        categorySelect.put(5,	"Покупки");
        categorySelect.put(6,	"Отпуск");
        categorySelect.put(7,	"Спорт");
        categorySelect.put(8,	"Путешествия");
        categorySelect.put(9,	"Подарки");
        categorySelect.put(10,	"Основное");
        categorySelect.put(11,	"Дети");
        for(Map.Entry entry : categorySelect.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
}

