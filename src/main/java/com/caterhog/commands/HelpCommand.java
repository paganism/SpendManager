package com.caterhog.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.bots.commands.ICommandRegistry;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Помощь (справка) по всем командам
 */
public class HelpCommand extends BotCommand {

    private static final String LOGTAG = HelpCommand.class.getSimpleName();

    // Реестр всех команд
    private final ICommandRegistry commandRegistry;

    public HelpCommand(ICommandRegistry commandRegistry) {
        super("help", "Список всех поддерживаемых команд");
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        // Создаём сообщение
        SendMessage msg = new SendMessage();
        // Отправляем его тому кто вызвал команду
        msg.setChatId(chat.getId().toString());
        // В сообщении можно использовать HTML-форматирование
        msg.enableHtml(true);
        // Создаём текст сообщения
        StringBuilder result = new StringBuilder("<b>Команды бота:</b>\n");
        for (BotCommand cmd : commandRegistry.getRegisteredCommands()) {
            result.append(cmd.toString()).append("\n");
        }
        msg.setText(result.toString());

        try {
            absSender.sendMessage(msg);
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }
}