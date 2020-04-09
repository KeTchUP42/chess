package app.src.Abstract;

import app.src.ConfigFields;
import area.Interfaces.IArea;
import area.factory.BoardFactory;
import brains.bots.Bot_0;
import brains.player.Player;
import brains.src.Interfaces.IPlayer;
import objects.src.colors.GameColors;
import org.jetbrains.annotations.NotNull;
import visual.src.Interfaces.IVisual;

/**
 * @author Roman
 */
abstract class AbstractConfigSetter {

    protected IVisual Visual;

    protected IArea Area;

    protected IPlayer[] Brains;

    /**
     * Устанавливаем и заполняем игровую область
     *
     * @param factoryConfig Основной конфиг решающий какой метод фабрики будет использован
     */
    protected void setArea(@NotNull String factoryConfig) {

        switch (factoryConfig.toLowerCase()) {
            case "test":
                this.Area = new BoardFactory().getTestArea();
                break;
            case "standard":
            default:
                this.Area = new BoardFactory().getStandardArea();
                break;
        }
    }

    /**
     * Создаем соответствующего бота или игрока - соответствующего цвета
     *
     * @param typeConfig  Основной конфиг решающий бот или игрок
     * @param colorConfig Конфиг цвета
     */
    protected void setFirstBrain(@NotNull String typeConfig, String Name, boolean colorConfig) {
        switch (typeConfig.toLowerCase()) {

            case "bot_0":
                this.Brains[0] = new Bot_0(this.Area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.Visual, Name);
                break;
            case "standard":
            case "player":
            default:
                this.Brains[0] = new Player(this.Area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.Visual, Name);
                break;
        }
    }

    /**
     * Создаем соответствующего бота с цветом не равным цвета игрока\другого бота
     *
     * @param typeConfig  Основной конфиг решиющий какого бота или игрока создать
     * @param colorConfig Конфиг цвета
     */
    protected void setSecondBrain(@NotNull String typeConfig, String Name, boolean colorConfig) {
        switch (typeConfig.toLowerCase()) {

            case "player":
                this.Brains[1] = new Player(this.Area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.Visual, Name);
                break;
            case "standard":
            case "bot_0":
            default:
                this.Brains[1] = new Bot_0(this.Area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.Visual, Name);
                break;
        }
    }

    /**
     * @param configData Массив параметров
     */
    protected void setConfig(@NotNull String[] configData) {
        this.setArea(configData[ConfigFields.AREA_TYPE]);
        this.Brains = new IPlayer[2];
        this.setFirstBrain(configData[ConfigFields.FIRST_BRAIN_TYPE], configData[ConfigFields.FIRST_BRAIN_NAME], configData[ConfigFields.FIRST_BRAIN_COLOR].equals("") || configData[1].equals("standard"));
        this.setSecondBrain(configData[ConfigFields.SECOND_BRAIN_TYPE], configData[ConfigFields.SECOND_BRAIN_NAME], !(configData[ConfigFields.FIRST_BRAIN_COLOR].equals("") || configData[1].equals("standard")));
    }
}
