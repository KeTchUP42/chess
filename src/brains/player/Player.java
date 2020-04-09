package brains.player;

import area.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Scanners.ChessScanner;
import visual.src.Interfaces.IVisual;

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
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        if (this.scanner.isKingDead(this.Color)) {
            this.Visual.sendMessage(this.Name + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return 1;
        }

        int squareNumber;
        int figureSquareNumber;
        try {
            this.Visual.sendMessage("Очередь " + this.Name, false, false);
            String input = this.Visual.sendMessage(
                    "Введи номер клетки на которой вы хотите двинуть фигуру.", true, false);

            if (input.toLowerCase().equals("recall")) {
                this.Area.recallStep(2);
                return 2;
            }
            squareNumber = Integer.parseInt(input);

            input = this.Visual.sendMessage(
                    "Введи номер клетки на которую вы хотите двинуть фигуру.", true, false);

            if (input.toLowerCase().equals("recall")) {
                this.Area.recallStep(2);
                return 2;
            }
            figureSquareNumber = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            return 2;
        }
        //Движение фигуры
        boolean isValidStep = this.Area.moveObjectSafe(squareNumber, figureSquareNumber, this.Color);
        if (isValidStep) this.Visual.sendMessage(this.Name + " сделал ход", false, false);
        return isValidStep ? this.returnZero(0) : 2;
    }
}
