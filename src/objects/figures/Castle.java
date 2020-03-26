package objects.figures;

import area.src.Interfaces.IArea;
import objects.figures.src.Abstract.AbstractRemoteAttackerFigure;
import objects.src.Interfaces.IObject;

import java.awt.*;

public class Castle extends AbstractRemoteAttackerFigure implements IObject {
    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public Castle(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, IArea Board) {
        return super.isWayFreePerpendicular(SquareNumber, Board) &&
                super.isInRange(SquareNumber, Board);
    }

}
