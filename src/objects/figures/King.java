package objects.figures;

import area.Interfaces.IArea;
import objects.figures.src.Abstract.AbstractFigure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class King extends AbstractFigure {
    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public King(int squareNumber, Color color) {
        super(squareNumber, color);
    }


    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return this.kingStepValid(SquareNumber, Board) &&
                super.isInRange(SquareNumber, Board);
    }

    /**
     * Метод проверки валидности хода для короля
     *
     * @param SquareNumber Номер клетки куда нужно походить
     * @param Board        Доска
     * @return Возможно ли это
     */
    private boolean kingStepValid(int SquareNumber, @NotNull IArea Board) {
        int yRange = Math.abs(Board.getYCoordinate(this.getSquareNumber()) - Board.getYCoordinate(SquareNumber));
        int xRange = Math.abs(Board.getXCoordinate(this.getSquareNumber()) - Board.getXCoordinate(SquareNumber));
        return (yRange == 1 && xRange == 1) || (yRange == 1 && xRange == 0) || (yRange == 0 && xRange == 1);
    }
}
