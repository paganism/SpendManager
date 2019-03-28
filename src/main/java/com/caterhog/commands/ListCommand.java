package com.caterhog.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Помощь (справка) по всем командам
 */
public class ListCommand extends BotCommand {

    private static final String LOGTAG = ListCommand.class.getSimpleName();


    public ListCommand() {
        super("list", "Список всех задач");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chat.getId().toString());
        msg.enableHtml(true);

        StringBuilder result = new StringBuilder("<b>Список всех задач:</b>\n");
        result.append("1. Задача 1\n");
        result.append("2. Задача 2\n");
        msg.setText(result.toString());

        try {
            absSender.sendMessage(msg);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}