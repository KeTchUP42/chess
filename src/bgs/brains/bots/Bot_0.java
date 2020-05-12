package bgs.brains.bots;

import bgs.area.IArea;
import bgs.brains.scanners.ChessScanner;
import bgs.brains.src.AbstractPlayer;
import bgs.brains.src.IPlayer;
import bgs.brains.src.repo.StepLog;
import bgs.brains.src.repo.TimeSpan;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


/**
 * @author Roman
 */
public class Bot_0 extends AbstractPlayer {
    public Bot_0(IArea area, Color color, IVisual visual, String name) {
        super(area, color, visual, name);
    }

    public Bot_0() {
    }

    @Override
    public StepLog step() {

        if (new ChessScanner(this.Area).isKingDead(this.Color)) {
            this.Visual.showMessage(this.Name + " defeat on " + this.stepNumber + " step.", false);
            return StepLog.DEFEAT;
        }
        int squareNumber, targetSquareNumber;
        do {
            squareNumber = (int) (Math.random() * this.Area.getMaxSquareNumber());
            targetSquareNumber = (int) (Math.random() * this.Area.getMaxSquareNumber());

        } while (!this.Area.moveObjectSafe(squareNumber, targetSquareNumber, this.Color));
        return this.finalize(squareNumber, targetSquareNumber, TimeSpan.TIME_SPAN);
    }

    /**
     * Method reconfigures or builds new player
     *
     * @param area
     * @param color
     * @param visual
     * @param name
     * @return
     */
    @Override
    public IPlayer rebuild(@NotNull IArea area, @NotNull Color color, @NotNull IVisual visual, String name) {
        return new Bot_0(area, color, visual, name);
    }
}
