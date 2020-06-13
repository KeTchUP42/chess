package bgs.brains.bots;

import bgs.area.IArea;
import bgs.brains.scanners.ChessKingScanner;
import bgs.brains.src.AbstractPlayer;
import bgs.brains.vars.StepLog;
import bgs.brains.vars.TimeSpan;
import bgs.visual.src.IVisual;

import java.awt.*;


/**
 * @author Roman
 */
public class Bot_0 extends AbstractPlayer {
    public Bot_0(IArea area, Color color, IVisual visual, String name) {
        super(area, color, visual, name);
    }

    @Override
    public StepLog step() {
        if (new ChessKingScanner(this.Area).isKingDead(this.Color)) {
            this.Visual.showMessage(this.Name + " defeat on " + this.stepNumber + " step.", false);
            return StepLog.DEFEAT;
        }
        int squareNumber, targetSquareNumber;
        do {
            squareNumber = (int) (Math.random() * this.Area.getMaxSquareNumber());
            targetSquareNumber = (int) (Math.random() * this.Area.getMaxSquareNumber());

        } while (!this.Area.moveObjectSafe(squareNumber, targetSquareNumber, this.Color));
        return this.sendNormalLog(TimeSpan.TIME_SPAN);
    }
}
