package app.src.Abstract;

import area.src.Interfaces.IArea;
import brains.bots.Bot_0;
import brains.player.Player;
import brains.src.Interfaces.IPlayer;
import objects.board.factory.BoardFactory;
import objects.board.factory.Interfaces.IBoardFactory;
import objects.figures.src.colors.GameColors;
import org.jetbrains.annotations.NotNull;
import visual.Interfaces.IVisual;

abstract class AbstractConfigSetter {
    protected IVisual visual;
    protected IPlayer secondPlayer;
    protected IPlayer firstPlayer;
    protected IArea area;


    /**
     * Устанавливаем и заполняем игровую область
     *
     * @param factoryConfig Основной конфиг решающий какой метод фабрики будет использован
     */
    protected void setArea(@NotNull String factoryConfig) {
        IBoardFactory factory = new BoardFactory();

        switch (factoryConfig.toLowerCase()) {

            case "standard":
            case "0":
            default:
                this.area = factory.getStandardBoard();
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
                this.firstPlayer = new Bot_0(this.area, colorConfig.equals("0") ? GameColors.firstColor :
                        GameColors.secondColor, this.visual);
                break;
            case "standard":
            case "player":
            default:
                this.firstPlayer = new Player(this.area, colorConfig.equals("0") ? GameColors.firstColor :
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
                this.secondPlayer = new Player(this.area, !colorConfig.equals("0") ? GameColors.firstColor :
                        GameColors.secondColor, this.visual, nickName);
                break;
            case "standard":
            case "bot_0":
            default:
                this.secondPlayer = new Bot_0(this.area, !colorConfig.equals("0") ? GameColors.firstColor :
                        GameColors.secondColor, this.visual);
                break;
        }
    }

    /**
     * @param configData Массив параметров
     */
    protected void setConfig(@NotNull String[] configData) {
        this.setArea(configData[0]);
        this.setFirstPlayerOrBot(configData[2], configData[5], configData[1]);
        this.setSecondBotOrPlayer(configData[3], configData[4], configData[1]);
    }
}
