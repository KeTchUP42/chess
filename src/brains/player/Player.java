package brains.player;

import area.src.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Interfaces.IPlayer;
import visual.Interfaces.IVisual;

import java.awt.*;

public class Player extends AbstractPlayer implements IPlayer {

    private String nickName;

    public Player(IArea area, Color color, IVisual visual, String nickName) {
        super(area, color, visual);
        this.nickName = nickName;
    }

    /**
     * @return // 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {

        int squareNumber;
        int figureSquareNumber;
        try {
            String input = this.visual.sendMessage(
                    "Введи номер клетки на которой вы хотите двинуть фигуру.", true, false);
            if (input.toUpperCase().equals("EXIT") || input.toUpperCase().equals("DIE")) return 1;
            squareNumber = Integer.parseInt(input);

            input = this.visual.sendMessage(
                    "Введи номер клетки на которую вы хотите двинуть фигуру.", true, false);
            if (input.toUpperCase().equals("EXIT") || input.toUpperCase().equals("DIE")) return 1;
            figureSquareNumber = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            this.visual.sendMessage("Неправильный ввод!", false, false);
            return 2;
        }
        this.visual.reDraw(this.Area);
        //Движение фигуры
        boolean isValidStep = this.Area.moveObjectSafe(squareNumber, figureSquareNumber, this.Color);
        if (isValidStep) this.visual.sendMessage(this.nickName + " сделал ход", false, false);
        return isValidStep ? 0 : 2; //TODO !!!
    }
}
