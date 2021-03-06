package com.caterhog.commands;

import com.caterhog.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Команда /dayincome показывает доход за текущий день
 *
 */
public class DelLastIncCommand  extends BotCommand {
    private static final String LOGTAG = IncomeCommand.class.getSimpleName();

    public DelLastIncCommand() {
        super("dellastinc", "Удалиь последнюю запись о доходе ");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        SendMessage msg = new SendMessage();
        DelLast in = new DelLast();
        msg.setChatId(chat.getId().toString());
        msg.enableHtml(true);

        StringBuilder result1 = new StringBuilder("<b></b>\n");
        result1.append(in.delLastIncome(chat.getId().toString()));
        msg.setText(result1.toString());

        try {
            absSender.sendMessage(msg);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}