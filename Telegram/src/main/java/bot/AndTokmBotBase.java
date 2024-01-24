package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public abstract class AndTokmBotBase extends TelegramLongPollingBot
{
    @Override
    public String getBotUsername() {
        return "AndTokmBot";
    }

    @Override
    public String getBotToken() {
        return "6719842904:AAHlglSjAI-fsIkJVuZlDTq4Htd5x5CDY1c";
    }
}