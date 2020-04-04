package brains.player;

import area.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Interfaces.IPlayer;
import brains.src.sranners.ChessScanner;
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
        ChessScanner chessScanner = new ChessScanner(this.boardArea);
        if (chessScanner.isKingDead(this.Color)) {
            this.visual.sendMessage(this.nickName + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return 1;
        }

        int squareNumber;
        int figureSquareNumber;
        try {
            this.visual.sendMessage("Очередь " + this.nickName, false, false);
            String input = this.visual.sendMessage(
                    "Введи номер клетки на которой вы хотите двинуть фигуру.", true, false);

            if (input.toLowerCase().equals("recall")) {
                this.boardArea.recallStep(2);
                return 2;
            }
            squareNumber = Integer.parseInt(input);
            //
            input = this.visual.sendMessage(
                    "Введи номер клетки на которую вы хотите двинуть фигуру.", true, false);

            if (input.toLowerCase().equals("recall")) {
                this.boardArea.recallStep(2);
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
        return isValidStep ? this.returnZero(0) : 2;
    }
}
