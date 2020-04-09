package app.src.Abstract;

import app.src.configSetup.INIParser;
import brains.src.Interfaces.IPlayer;
import objects.src.colors.GameColors;
import org.jetbrains.annotations.NotNull;
import visual.src.Interfaces.IVisual;

import java.io.IOException;

/**
 * @author Roman
 */
public abstract class AbstractApplication extends AbstractConfigSetter {

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
    public abstract void run();

    /**
     * @throws IOException Вызывает ошибку при отсутствии файла
     */
    protected void gameSetup(String configPath) throws IOException {
        INIParser parser = new INIParser(configPath);
        parser.setColorConfig();
        this.setConfig(parser.getConfig());

    }

    /**
     * Основной игровой цикл
     */
    protected void gameRun() {
        //Решение порядка ходов
        IPlayer firstBrain = this.Brains[0].getColor() == GameColors.firstColor ? this.Brains[0] : this.Brains[1];
        IPlayer secondBrain = !(this.Brains[0].getColor() == GameColors.firstColor) ? this.Brains[0] : this.Brains[1];
        while (true) {
            // 0 - норма, 1 - проиграл, 2 - ход невозможен
            boolean exit = false;
            while (true) {
                this.Visual.Draw(this.Area);
                int stepResult = firstBrain.step();
                if (stepResult == 1) {
                    exit = true;
                }
                if (stepResult == 2) continue;
                break;
            }
            if (exit) break;
            while (true) {
                this.Visual.Draw(this.Area);
                int stepResult = secondBrain.step();
                if (stepResult == 1) {
                    exit = true;
                }
                if (stepResult == 2) continue;
                break;
            }
            if (exit) break;
        }
    }


    /**
     * Метод реализует логику полученя информации от игрока
     *
     * @return Массив для setConfig
     */
    protected String[] playerConfig(@NotNull String[] configItems) {
        String[] configData = new String[configItems.length];
        for (int index = 0; index < configItems.length; index++) {
            configData[index] = this.Visual.sendMessage("Введи параметр " + configItems[index], true, false).trim();
        }
        return configData;
    }

    /**
     * @throws IOException Вызывает ошибку при отсутствии файла
     */
    protected void loadConfigFromFile(String playersConfigAw) throws IOException {
        if (playersConfigAw != null) {
            this.gameSetup(playersConfigAw);
        } else {
            this.setConfig(this.playerConfig(
                    new String[]{
                            "areaType", "firstBrainColor", "firstBrainType",
                            "secondBrainType", "secondBrainName",
                            "firstBrainName"
                    }));
        }
    }
}
