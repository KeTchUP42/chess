package chess.figures.src.Abstract;

import game.core.area.src.Interfaces.IArea;
import game.core.objects.src.Abstract.AbstractRemoteAttacker;

import java.awt.*;


public abstract class AbstractRemoteAttackerFigure extends AbstractRemoteAttacker {

    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractRemoteAttackerFigure(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    /**
     * @param SquareNumber Куда объект хочет ударить или попасть
     * @param area         Область где стоит объект
     * @return boolean
     */
    public boolean isInRange(int SquareNumber, IArea area) {
        return super.isInRange(SquareNumber, area) ||
                area.getObjectFromList(SquareNumber).getColor() != this.getColor();
    }
}
