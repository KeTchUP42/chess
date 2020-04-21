package objects.figures;

import area.IArea;
import objects.figures.src.AbstractFigure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public class Queen extends AbstractFigure {
    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public Queen(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return (super.isWayFreePerpendicular(SquareNumber, Board) || super.isWayFreeDiagonal(SquareNumber, Board)) &&
                super.isInRange(SquareNumber, Board);
    }
}
