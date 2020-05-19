package bgs.brains.players;

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
public class Player extends AbstractPlayer {
    public Player(IArea area, Color color, IVisual visual, String name) {
        super(area, color, visual, name);
    }

    public Player() {
    }

    @Override
    public StepLog step() {
        if (new ChessScanner(this.Area).isKingDead(this.Color)) {
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

    @Override
    public IPlayer rebuild(@NotNull IArea area, @NotNull Color color, @NotNull IVisual visual, String name) {
        return new Player(area, color, visual, name);
    }
}
