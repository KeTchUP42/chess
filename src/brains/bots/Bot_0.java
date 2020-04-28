package brains.bots;

import area.IArea;
import brains.scanners.ChessScanner;
import brains.src.AbstractPlayer;
import brains.src.repo.StepLog;
import brains.src.repo.TimeSpan;
import visual.src.IVisual;

import java.awt.*;

/**
 * @author Roman
 */
public class Bot_0 extends AbstractPlayer {

    protected ChessScanner scanner = new ChessScanner(this.Area);

    public Bot_0(IArea area, Color color, IVisual visual, String Name) {
        super(area, color, visual, Name);
    }

    @Override
    public StepLog step() {
        if (this.scanner.isKingDead(this.Color)) {
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
