package com.caterhog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Date;

/**
 * Created by yuriy on 06.12.2016.
 */
public class ChekInput {

    public double readDouble(Scanner in) {
        double z = 0;
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

    public static String readString1(Scanner in) {
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

    public int readCategory(Scanner in) {
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

    public static boolean isValidDate(String date) {
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
        Scanner scan = new Scanner(System.in);
        System.out.println(isValidDate(scan.nextLine())); // false
        //System.out.println(isValidDate("29.02.16")); // true
    }
}

