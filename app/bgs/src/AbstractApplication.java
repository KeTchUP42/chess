package bgs.src;

import brains.src.IPlayer;
import brains.src.rep.StepLog;
import visual.src.GameColors;
import visual.src.IVisual;

/**
 * @author Roman
 */
public abstract class AbstractApplication extends AbstractApplicationBase {

    public AbstractApplication(IVisual Visual) {
        super(Visual);
    }

    public void run(String[] configData) {
        try {
            this.applySettings(configData);
            this.runGame();
        } catch (Exception | Error e) {
            this.Visual.showMessage(e.getMessage(), false);
        } finally {
            this.Visual.showMessage("Exiting...", false);
        }
    }

    /**
     * Game circle
     */
    protected void runGame() {
        //First player choosing
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
