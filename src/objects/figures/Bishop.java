package objects.figures;

import area.Interfaces.IArea;
import objects.figures.src.Abstract.AbstractFigure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public class Bishop extends AbstractFigure {
    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public Bishop(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return super.isWayFreeDiagonal(SquareNumber, Board) &&
                super.isInRange(SquareNumber, Board);
    }

}
