package bgs.src;

import area.IArea;
import area.factory.BoardFactory;
import brains.bots.Bot_0;
import brains.player.Player;
import brains.src.IPlayer;
import org.jetbrains.annotations.NotNull;
import tools.logger.Logger;
import visual.src.GameColors;
import visual.src.IVisual;

/**
 * @author Roman
 */
abstract class AbstractApplicationBase {

    protected IVisual Visual;

    protected IArea Area;

    protected IPlayer[] Players;

    public AbstractApplicationBase(IVisual Visual) {
        this.Visual = Visual;
    }

    protected IArea generateArea(@NotNull String factoryConfig) {
        switch (factoryConfig.toLowerCase()) {
            case "test":
                return new BoardFactory().createTestArea();
            case "standard":
            default:
                return new BoardFactory().createStandardArea();
        }
    }

    protected IPlayer generatePlayer(@NotNull String typeConfig, String Name, boolean colorConfig) {
        switch (typeConfig.toLowerCase()) {
            case "player":
                return new Player(this.Area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.Visual, Name);
            case "bot_0":
            default:
                return new Bot_0(this.Area, colorConfig
                        ? GameColors.firstColor :
                        GameColors.secondColor, this.Visual, Name);
        }
    }

    /**
     * Main config applier
     *
     * @param configData params
     */
    protected void applySettings(@NotNull String[] configData) {
        Logger.configureGlobalLogger(configData[ConfigFields.LOG_FILE_PATH]);
        this.Area = generateArea(configData[ConfigFields.AREA_TYPE]);
        boolean colorPriority = configData[ConfigFields.FIRST_PLAYER_COLOR].equals("") || configData[1].equals("standard");
        this.Players = new IPlayer[]
                {
                        this.generatePlayer(configData[ConfigFields.FIRST_PLAYER_TYPE], configData[ConfigFields.FIRST_PLAYER_NAME],
                                colorPriority),
                        this.generatePlayer(configData[ConfigFields.SECOND_PLAYER_TYPE], configData[ConfigFields.SECOND_PLAYER_NAME],
                                !colorPriority)
                };
    }
}
