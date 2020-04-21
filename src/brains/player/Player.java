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

    /**
     * Сканер области
     */
    protected ChessScanner scanner = new ChessScanner(this.Area);

    public Player(IArea area, Color color, IVisual visual, String Name) {
        super(area, color, visual, Name);
    }

    /**
     * @return StepLog
     */
    @Override
    public StepLog step() {
        if (this.scanner.isKingDead(this.Color)) {
            this.Visual.showMessage(this.Name + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return StepLog.DEFEAT;
        }

        int squareNumber;
        int figureSquareNumber;
        try {
            this.Visual.showMessage("Очередь " + this.Name, false, false);
            String input = this.Visual.showMessage(
                    "Введи номер клетки на которой вы хотите двинуть фигуру.", true, false);

            if (input.toLowerCase().equals("undo")) {
                this.Area.undoStep(2);
                return StepLog.STEP_IS_IMPOSSIBLE;
            }
            squareNumber = Integer.parseInt(input);

            input = this.Visual.showMessage(
                    "Введи номер клетки на которую вы хотите двинуть фигуру.", true, false);

            if (input.toLowerCase().equals("undo")) {
                this.Area.undoStep(2);
                return StepLog.STEP_IS_IMPOSSIBLE;
            }
            figureSquareNumber = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            return StepLog.STEP_IS_IMPOSSIBLE;
        }
        //Движение фигуры
        return this.Area.moveObjectSafe(squareNumber, figureSquareNumber, this.Color)
                ? this.finalize(squareNumber, figureSquareNumber, TimeSpan.NO_TIME_SPAN)
                : StepLog.STEP_IS_IMPOSSIBLE;
    }
}
