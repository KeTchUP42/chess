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

abstract class AbstractConfigSetter {

    protected IVisual visual;

    protected IArea area;

    protected IPlayer[] brains;

    /**
     * Устанавливаем и заполняем игровую область
     *
     * @param factoryConfig Основной конфиг решающий какой метод фабрики будет использован
     */
    protected void setArea(@NotNull String factoryConfig) {

        switch (factoryConfig.toLowerCase()) {
            case "test":
                this.area = new BoardFactory().getTestArea();
                break;
            case "standard":
            default:
                this.area = new BoardFactory().getStandardArea();
                break;
        }
    }

    /**
     * Создаем соответствующего бота или игрока - соответствующего цвета
     *
     * @param typeConfig  Основной конфиг решающий бот или игрок
     * @param colorConfig Конфиг цвета
     */
    protected void setFirstBrain(@NotNull String typeConfig, String nickName, boolean colorConfig) {
        switch (typeConfig.toLowerCase()) {

            case "bot_0":
                this.brains[0] = new Bot_0(this.area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
            case "standard":
            case "player":
            default:
                this.brains[0] = new Player(this.area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
        }
    }

    /**
     * Создаем соответствующего бота с цветом не равным цвета игрока\другого бота
     *
     * @param typeConfig  Основной конфиг решиющий какого бота или игрока создать
     * @param colorConfig Конфиг цвета
     */
    protected void setSecondBrain(@NotNull String typeConfig, String nickName, boolean colorConfig) {
        switch (typeConfig.toLowerCase()) {

            case "player":
                this.brains[1] = new Player(this.area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
            case "standard":
            case "bot_0":
            default:
                this.brains[1] = new Bot_0(this.area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
        }
    }

    /**
     * @param configData Массив параметров
     */
    protected void setConfig(@NotNull String[] configData) {
        this.setArea(configData[ConfigFields.areaType]);
        this.brains = new IPlayer[2];
        this.setFirstBrain(configData[ConfigFields.firstBrainType], configData[ConfigFields.firstBrainNickName], configData[ConfigFields.firstBrainColor].equals("") || configData[1].equals("standard"));
        this.setSecondBrain(configData[ConfigFields.secondBrainType], configData[ConfigFields.secondBrainNickName], !(configData[ConfigFields.firstBrainColor].equals("") || configData[1].equals("standard")));
    }
}
