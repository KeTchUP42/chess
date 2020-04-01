package objects.figures;

import area.src.Interfaces.IArea;
import objects.figures.src.Abstract.AbstractFigure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class Knight extends AbstractFigure {
    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public Knight(int squareNumber, Color color) {
        super(squareNumber, color);
    }


    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return this.knightStepValid(SquareNumber, Board) &&
                super.isInRange(SquareNumber, Board);
    }

    /**
     * Проверка валидности хода коня
     *
     * @param SquareNumber Номер клетки куда нужно походить
     * @param Board        Доска
     * @return Возможно это или нет
     */
    private boolean knightStepValid(int SquareNumber, @NotNull IArea Board) {
        int absY = Math.abs(Board.getYCoordinate(this.getSquareNumber()) - Board.getYCoordinate(SquareNumber));
        int absX = Math.abs(Board.getXCoordinate(this.getSquareNumber()) - Board.getXCoordinate(SquareNumber));
        return (absY == 2 && absX == 1) || (absX == 2 && absY == 1);
    }
}
