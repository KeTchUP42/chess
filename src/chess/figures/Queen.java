package chess.figures;

import chess.figures.src.Abstract.AbstractRemoteAttackerFigure;
import game.core.area.src.Interfaces.IArea;
import game.core.objects.src.Interfaces.IObject;

import java.awt.*;

public class Queen extends AbstractRemoteAttackerFigure implements IObject {
    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public Queen(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, IArea Board) {
        return (super.isWayFreePerpendicular(SquareNumber, Board) || super.isWayFreeDiagonal(SquareNumber, Board)) &&
                super.isInRange(SquareNumber, Board);
    }
}
