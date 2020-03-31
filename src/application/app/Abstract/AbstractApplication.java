package application.app.Abstract;

import application.app.configSetup.INIParser;
import brains.src.Interfaces.IPlayer;
import objects.src.colors.GameColors;
import org.jetbrains.annotations.NotNull;
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
        INIParser parser = new INIParser("src/application/configs/config.ini");
        parser.setColorConfig();
        this.setConfig(parser.getConfig());
    }

    /**
     * Основной игровой цикл
     */
    protected void gameRun() {
        //Начальная отрисовка области
        this.visual.Draw(this.area);//TODO
        //Решение порядка ходов
        IPlayer firstPlayer = this.brains[0].getColor() == GameColors.firstColor ? this.brains[0] : this.brains[1];
        IPlayer secondPlayer = !(this.brains[0].getColor() == GameColors.firstColor) ? this.brains[0] : this.brains[1];
        while (true) {
            // 0 - норма, 1 - проиграл, 2 - ход невозможен
            boolean exit = false;
            while (true) {
                this.visual.Draw(this.area);//TODO
                int stepResult = firstPlayer.step();
                if (stepResult == 1) {
                    exit = true;
                }
                if (stepResult == 2) continue;
                break;
            }
            if (exit) break;
            while (true) {
                this.visual.Draw(this.area);//TODO
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
    protected String[] playerConfig(@NotNull String[] configItems) {
        String[] configData = new String[configItems.length];
        for (int index = 0; index < configItems.length; index++) {
            configData[index] = this.visual.sendMessage("Введи параметр " + configItems[index], true, false).trim();
        }
        return configData;
    }

    /**
     * @throws IOException Вызывает ошибку при отсутствии файла
     */
    protected void loadConfigFromFile(boolean doLoad) throws IOException {
        if (doLoad) {
            this.gameSetup();
        } else {
            this.setConfig(this.playerConfig(
                    new String[]{
                            "areaType", "firstPlayerColor", "firstPlayerType",
                            "secondPlayerType", "secondPlayerNickName",
                            "firstPlayerNickName"
                    })); //При добавлении новых параметров в конфиге добавить их имена в массив
        }
    }
}
