package com.caterhog.commands;

import com.caterhog.Income;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Команда /start выполняется при добавлении бота.
 * Выводится помощь (справка) по всем командам
 */
public class IncomeCommand extends BotCommand {
    private static final String LOGTAG = IncomeCommand.class.getSimpleName();

    public IncomeCommand() {
        super("income", "Внести доход: + параметры(сумма дохода и описание)");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        SendMessage msg = new SendMessage();
        Income in = new Income();
        msg.setChatId(chat.getId().toString());
        msg.enableHtml(true);
        double inc = 0;
        String category = "";
        if (arguments != null && arguments.length >= 1) {
            inc = Double.parseDouble(arguments[0].replaceAll(" ","").replace(',','.'));
        }
        if (arguments != null && arguments.length >= 2) {
            category = arguments[1];
        }

        StringBuilder result = new StringBuilder("<b>Список всех задач:</b>\n");
        result.append("1. Задача 1\n");
        result.append("2. Задача 2\n");
        StringBuilder result1 = new StringBuilder("<b></b>\n");
        result1.append(in.incomeIns(Double.valueOf(inc), category, chat.getId().toString()));
        msg.setText(result1.toString());
        //msg.setText("Гусеница, Ля ля ля :)");

        //in.incomeIns(Double.valueOf(inc), "HZ", chat.getId().toString());
        try {
            //absSender.sendMessage(msg);
            //absSender.sendMessage(msg);
            absSender.sendMessage(msg);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}