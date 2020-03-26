package app.src.Abstract;

import app.src.configSetup.INIParser;
import brains.src.Interfaces.IPlayer;
import objects.figures.src.colors.GameColors;
import visual.Interfaces.IVisual;

import java.io.IOException;

public abstract class AbstractApplication extends AbstractConfigSetter {

    /**
     * Задается визуал
     *
     * @param visual Объект заключающий в себе функционал визуала
     */
    public AbstractApplication(IVisual visual) {
        this.visual = visual;
    }

    /**
     * Запуск приложения
     */
    public abstract void run();

    /**
     * @throws IOException Вызывает ошибку при отсутствии файла
     */
    protected void gameSetup() throws IOException {
        INIParser parser = new INIParser("src/app/configs/config.ini"); //TODO
        parser.setColorConfig();
        this.setConfig(parser.getConfig());
    }

    /**
     * Основной игровой цикл
     */
    protected void gameRun() {
        //Решение порядка ходов
        IPlayer firstPlayer = this.firstPlayer.getColor() == GameColors.firstColor ? this.firstPlayer : this.secondPlayer;
        IPlayer secondPlayer = !(this.firstPlayer.getColor() == GameColors.firstColor) ? this.firstPlayer : this.secondPlayer;
        while (true) {
            // 0 - норма, 1 - проиграл, 2 - ход невозможен
            boolean exit = false;
            while (true) {
                int stepResult = firstPlayer.step();
                if (stepResult == 1) {
                    exit = true;
                }
                if (stepResult == 2) continue;
                break;
            }
            if (exit) break;
            while (true) {
                int stepResult = secondPlayer.step();
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
    protected String[] playerConfig(String[] configItems) {
        String[] configData = new String[configItems.length];
        for (int index = 0; index < configItems.length; index++) {
            configData[index] = this.visual.sendMessage("Введи параметр " + configItems[index], true, false).trim();
        }
        return configData;
    }


    /**
     * @throws IOException Вызывает ошибку при отсутствии файла
     */
    protected void loadConfigFromFile(boolean variable) throws IOException {
        if (variable) {
            this.gameSetup();
        } else {
            this.setConfig(this.playerConfig(
                    new String[]{
                            "areaType", "firstPlayerColor", "firstPlayerType",
                            "secondPlayerType", "secondPlayerNickName",
                            "firstPlayerNickName" //При добавлении новых параметров в конфиге добавить их имена в массив
                    }));
        }
        this.visual.reDraw(this.area); //TODO
    }
}
