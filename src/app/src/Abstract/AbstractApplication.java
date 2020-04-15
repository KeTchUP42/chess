package app.src.Abstract;

import app.src.setup.ConfigRecipient;
import app.src.setup.INI.INIParser;
import area.factory.src.GameColors;
import brains.src.Interfaces.IPlayer;
import brains.src.StepLog;
import visual.src.Interfaces.IVisual;

import java.io.IOException;

/**
 * @author Roman
 */
public abstract class AbstractApplication extends AbstractApplicationBase {

    /**
     * Задается визуал
     *
     * @param Visual Объект заключающий в себе функционал визуала
     */
    public AbstractApplication(IVisual Visual) {
        this.Visual = Visual;
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
     * Основной игровой цикл
     */
    protected void runGame() {
        //Решение порядка ходов
        boolean stepPriority = this.Players[0].getColor() == GameColors.firstStepColor;
        IPlayer firstPlayer = stepPriority ? this.Players[0] : this.Players[1];
        IPlayer secondPlayer = !stepPriority ? this.Players[0] : this.Players[1];
        while (true) {
            boolean exit = false;
            while (true) {
                this.Visual.Draw(this.Area);
                StepLog stepResult = firstPlayer.step();
                if (stepResult == StepLog.DEFEAT) {
                    exit = true;
                }
                if (stepResult == StepLog.STEP_IS_IMPOSSIBLE) continue;
                break;
            }
            if (exit) break;
            while (true) {
                this.Visual.Draw(this.Area);
                StepLog stepResult = secondPlayer.step();
                if (stepResult == StepLog.DEFEAT) {
                    exit = true;
                }
                if (stepResult == StepLog.STEP_IS_IMPOSSIBLE) continue;
                break;
            }
            if (exit) break;
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
