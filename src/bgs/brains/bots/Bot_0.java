package bgs.brains.bots;

import bgs.area.IArea;
import bgs.brains.scanners.ChessScanner;
import bgs.brains.src.AbstractPlayer;
import bgs.brains.src.repo.StepLog;
import bgs.brains.src.repo.TimeSpan;
import bgs.visual.src.IVisual;

import java.awt.*;

/**
 * @author Roman
 */
public class Bot_0 extends AbstractPlayer {

    public Bot_0() {
    }

    public Bot_0(IArea area, Color color, IVisual visual, String Name) {
        super(area, color, visual, Name);
    }

    @Override
    public StepLog step() {
        ChessScanner scanner = new ChessScanner(this.Area);
        if (scanner.isKingDead(this.Color)) {
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
}
