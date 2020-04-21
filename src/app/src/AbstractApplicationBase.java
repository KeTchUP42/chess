package app.src;

import app.logger.Logger;
import area.IArea;
import area.factory.BoardFactory;
import area.factory.src.GameColors;
import brains.bots.Bot_0;
import brains.player.Player;
import brains.src.IPlayer;
import org.jetbrains.annotations.NotNull;
import visual.src.IVisual;

/**
 * @author Roman
 */
abstract class AbstractApplicationBase {

    protected IVisual Visual;

    protected IArea Area;

    protected IPlayer[] Players;

    /**
     * Устанавливаем и заполняем игровую область
     *
     * @param factoryConfig Основной конфиг решающий какой метод фабрики будет использован
     */
    public static IArea generateArea(@NotNull String factoryConfig) {
        switch (factoryConfig.toLowerCase()) {
            case "test":
                return new BoardFactory().createTestArea();
            case "standard":
            default:
                return new BoardFactory().createStandardArea();
        }
    }

    /**
     * Создаем бота или игрока - соответствующего цвета
     *
     * @param typeConfig  Тип игрока
     * @param colorConfig Конфиг цвета
     */
    public IPlayer generatePlayer(@NotNull String typeConfig, String Name, boolean colorConfig) {
        switch (typeConfig.toLowerCase()) {
            case "player":
                return new Player(this.Area, colorConfig
                        ? GameColors.firstStepColor :
                        GameColors.secondStepColor, this.Visual, Name);
            case "bot_0":
            default:
                return new Bot_0(this.Area, colorConfig
                        ? GameColors.firstStepColor :
                        GameColors.secondStepColor, this.Visual, Name);
        }
    }

    /**
     * Главный метод инициализации
     *
     * @param configData Массив параметров
     */
    protected void applySettings(@NotNull String[] configData) {
        Logger.configureGlobalLogger(configData[ConfigFields.LOG_FILE_PATH]);
        this.Area = generateArea(configData[ConfigFields.AREA_TYPE]);
        this.Players = new IPlayer[2];

        boolean colorPriority = configData[ConfigFields.FIRST_PLAYER_COLOR].equals("") || configData[1].equals("standard");

        this.Players[0] = this.generatePlayer(configData[ConfigFields.FIRST_PLAYER_TYPE], configData[ConfigFields.FIRST_PLAYER_NAME],
                colorPriority);
        this.Players[1] = this.generatePlayer(configData[ConfigFields.SECOND_PLAYER_TYPE], configData[ConfigFields.SECOND_PLAYER_NAME],
                !colorPriority);
    }
}
