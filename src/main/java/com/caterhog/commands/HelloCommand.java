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
public class HelloCommand extends BotCommand {

    private static final String LOGTAG = HelloCommand.class.getSimpleName();

    public HelloCommand() {
        super("hello", "Тестовая команда: скажи боту Привет :)");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        String userName = chat.getUserName();
        if (userName == null || userName.isEmpty()) {
            userName = user.getFirstName() + " " + user.getLastName();
        }

        StringBuilder msg = new StringBuilder("Привет, ").append(userName).append("! ");
        if (arguments != null && arguments.length > 0) {
            msg.append("\n");
            msg.append(String.format("Вы написали: \"%s\"", String.join(" ", arguments)));
        }

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.setText(msg.toString());

        try {
            absSender.sendMessage(answer);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}