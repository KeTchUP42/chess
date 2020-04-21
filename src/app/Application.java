package app;

import app.setup.INI.INIParser;
import app.setup.recipient.ConfigRecipient;
import app.src.AbstractApplication;
import visual.src.IVisual;

import java.io.IOException;

/**
 * @author Roman
 */
class Application extends AbstractApplication {

    /**
     * Задается визуал
     *
     * @param Visual Объект заключающий в себе функционал визуала
     */
    public Application(IVisual Visual) {
        super(Visual);
    }

    /**
     * Запуск приложения
     */
    public void run(String configFilePathOrNull) {
        try {
            this.loadSettings(configFilePathOrNull, new String[]{
                    "areaType",
                    "firstPlayerColor", "firstPlayerType", "firstPlayerName",
                    "secondPlayerType", "secondPlayerName",
                    "logFilePath"
            });
            this.runGame();
        } catch (Exception | Error e) {
            this.Visual.showMessage(e.getMessage(), false, false);
        } finally {
            this.Visual.showMessage("Завершение работы...", false, false);
        }
    }

    /**
     * null - ввод настроек вручную
     *
     * @throws IOException Вызывает ошибку при отсутствии файла
     */
    protected void loadSettings(String configPath, String[] configFields) throws IOException {
        if (configPath != null) {
            this.applySettings(new INIParser(configPath).loadConfig(configFields));
        } else {
            this.applySettings(new ConfigRecipient(this.Visual).getPlayerConfigs(configFields));
        }
    }
}
