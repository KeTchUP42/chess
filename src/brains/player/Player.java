package brains.player;

import area.IArea;
import brains.scanners.ChessScanner;
import brains.src.AbstractPlayer;
import brains.src.rep.StepLog;
import brains.src.rep.TimeSpan;
import visual.src.IVisual;

import java.awt.*;

/**
 * @author Roman
 */
public class Player extends AbstractPlayer {

    protected ChessScanner scanner = new ChessScanner(this.Area);

    public Player(IArea area, Color color, IVisual visual, String Name) {
        super(area, color, visual, Name);
    }

    @Override
    public StepLog step() {
        if (this.scanner.isKingDead(this.Color)) {
            this.Visual.showMessage(this.Name + " defeat on " + this.stepNumber + " step.", false);
            return StepLog.DEFEAT;
        }
        int squareNumber, figureSquareNumber;
        try {
            this.Visual.showMessage(this.Name + " turn.", false);
            String input = this.Visual.showMessage(
                    "Please, write object square number.", true);

            squareNumber = Integer.parseInt(input);

            input = this.Visual.showMessage(
                    "Please, write target square number.", true);

            figureSquareNumber = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            return StepLog.STEP_IS_IMPOSSIBLE;
        }
        return this.Area.moveObjectSafe(squareNumber, figureSquareNumber, this.Color)
                ? this.finalize(squareNumber, figureSquareNumber, TimeSpan.NO_TIME_SPAN)
                : StepLog.STEP_IS_IMPOSSIBLE;
    }
}
