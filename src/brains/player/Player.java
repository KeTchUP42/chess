package brains.player;

import area.src.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Interfaces.IPlayer;
import visual.src.Interfaces.IVisual;

public class Player extends AbstractPlayer implements IPlayer {


    public Player(IArea boardArea, java.awt.Color color, IVisual visual, String nickName) {
        super(boardArea, color, visual, nickName);
    }

    /**
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        if (this.isKingDead()) {
            this.visual.sendMessage(this.nickName + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return 1;
        }

        int squareNumber;
        int figureSquareNumber;
        try {
            this.visual.sendMessage("Очередь " + this.nickName, false, false);
            String input = this.visual.sendMessage(
                    "Введи номер клетки на которой вы хотите двинуть фигуру.", true, false);

            if (input.toUpperCase().equals("EXIT") || input.toUpperCase().equals("DIE")) return 1;
            if (input.toUpperCase().equals("RECALL")) {
                this.boardArea.recallLastStep(2);
                return 2;
            }
            squareNumber = Integer.parseInt(input);
            //
            input = this.visual.sendMessage(
                    "Введи номер клетки на которую вы хотите двинуть фигуру.", true, false);

            if (input.toUpperCase().equals("EXIT") || input.toUpperCase().equals("DIE")) return 1;
            if (input.toUpperCase().equals("RECALL")) {
                this.boardArea.recallLastStep(2);
                return 2;
            }
            figureSquareNumber = Integer.parseInt(input);
            //
        } catch (NumberFormatException e) {
            return 2;
        }
        //Движение фигуры
        boolean isValidStep = this.boardArea.moveObjectSafe(squareNumber, figureSquareNumber, this.Color);
        if (isValidStep) this.visual.sendMessage(this.nickName + " сделал ход", false, false);
        return isValidStep ? this.sleepReturn(0, 0) : this.sleepReturn(2, 0);
    }
}
