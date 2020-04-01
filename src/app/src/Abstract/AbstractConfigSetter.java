package app.src.Abstract;

import area.board.factory.BoardFactory;
import area.src.Interfaces.IArea;
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
            case "1":
                this.area = new BoardFactory().getTestBoard();
                break;
            case "standard":
            case "0":
            default:
                this.area = new BoardFactory().getStandardBoard();
                break;
        }
    }

    /**
     * Создаем соответствующего бота или игрока - соответствующего цвета
     *
     * @param chooseConfig Основной конфиг решающий бот или игрок
     * @param colorConfig  Конфиг цвета
     */
    protected void setFirstPlayerOrBot(@NotNull String chooseConfig, String nickName, String colorConfig) {
        switch (chooseConfig.toLowerCase()) {

            case "bot_0":
                this.brains[0] = new Bot_0(this.area, colorConfig.equals("") || colorConfig.equals("standard")
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
            case "standard":
            case "player":
            default:
                this.brains[0] = new Player(this.area, colorConfig.equals("") || colorConfig.equals("standard")
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
        }
    }

    /**
     * Создаем соответствующего бота с цветом не равным цвета игрока\другого бота
     *
     * @param chooseConfig Основной конфиг решиющий какого бота или игрока создать
     * @param colorConfig  Конфиг цвета
     */
    protected void setSecondBotOrPlayer(@NotNull String chooseConfig, String nickName, String colorConfig) {
        switch (chooseConfig.toLowerCase()) {

            case "player":
                this.brains[1] = new Player(this.area, !(colorConfig.equals("") || colorConfig.equals("standard"))
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
            case "standard":
            case "bot_0":
            default:
                this.brains[1] = new Bot_0(this.area, !(colorConfig.equals("") || colorConfig.equals("standard"))
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
        }
    }

    /**
     * @param configData Массив параметров
     */
    protected void setConfig(@NotNull String[] configData) {
        this.setArea(configData[0]);
        this.brains = new IPlayer[2];
        this.setFirstPlayerOrBot(configData[2], configData[5], configData[1]);
        this.setSecondBotOrPlayer(configData[3], configData[4], configData[1]);
    }
}
