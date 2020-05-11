package bgs.src;

import bgs.brains.src.IPlayer;
import bgs.brains.src.repo.StepLog;
import bgs.visual.src.GameColors;
import bgs.visual.src.IVisual;

/**
 * @author Roman
 */
public abstract class AbstractApplication extends AbstractApplicationBase {

    public AbstractApplication(IVisual Visual) {
        super(Visual);
    }

    /**
     * @param configData
     */
    public void run(String[] configData) {
        try {
            this.applySettings(configData);
            this.runGame();
        } catch (Exception | Error exception) {
            this.Visual.showMessage(exception.getMessage(), false);
        } finally {
            this.Visual.showMessage("Exiting...", false);
        }
    }

    /**
     * Game circle
     */
    protected void runGame() {
        IPlayer[] players = new IPlayer[this.Players.length];
        for (IPlayer player : this.Players) {
            if (player.getColor() == GameColors.firstColor) players[0] = player;
            if (player.getColor() == GameColors.secondColor) players[1] = player;
        }
        while (true) {
            boolean exit = false;
            for (IPlayer player : players
            ) {
                while (true) {
                    this.Visual.Draw(this.Area);
                    StepLog stepResult = player.step();
                    if (stepResult == StepLog.DEFEAT) {
                        exit = true;
                    }
                    if (stepResult == StepLog.STEP_IS_IMPOSSIBLE) continue;
                    break;
                }
                if (exit) break;
            }
            if (exit) break;
        }
    }
}
