package com.caterhog.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Просто отвечает на ваши слова
 */
public class MulTableCommand extends BotCommand {

    private static final String LOGTAG = HelloCommand.class.getSimpleName();

    public MulTableCommand() {
        super("multable", "Вывести таблицу умножения: параметр - размер");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        // Размер таблицы умножения
        int size = 3;
        if (arguments != null && arguments.length >= 1) {
            size = Integer.parseInt(arguments[0]);
        }

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.setText(genMulTable(size));
        answer.enableHtml(true);

        try {
            absSender.sendMessage(answer);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    public String genMulTable(int size) {
        // Заголовок таблицы
        StringBuilder msg = new StringBuilder(String.format("Таблица умножения: %d x %d%n", size, size));
        // Будем выводить с моноширинным шрифтом
        msg.append("<pre>");
        // Ширина столбца:
        int maxValue = size * size; // Максимальное значение
        int width = Integer.toString(maxValue).length() + 1; // Его длина + 1
        String formatString = "%" + width + "d";
        // fori
        final String NEW_LINE = System.lineSeparator();
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                msg.append(String.format(formatString, i * j));
            }
            msg.append(NEW_LINE); // Окончание строки
        }
        msg.append("</pre>");
        return msg.toString();
    }
}