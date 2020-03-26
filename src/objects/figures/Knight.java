package objects.figures;

import area.src.Interfaces.IArea;
import objects.figures.src.Abstract.AbstractFigure;
import objects.src.Interfaces.IObject;

import java.awt.*;


public class Knight extends AbstractFigure implements IObject {
    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public Knight(int squareNumber, Color color) {
        super(squareNumber, color);
    }


    @Override
    public boolean isInRange(int SquareNumber, IArea Board) {
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
    private boolean knightStepValid(int SquareNumber, IArea Board) {
        int absY = Math.abs(Board.getYCoordinate(this.getSquareNumber()) - Board.getYCoordinate(SquareNumber));
        int absX = Math.abs(Board.getXCoordinate(this.getSquareNumber()) - Board.getXCoordinate(SquareNumber));
        return absY + absX == 3;
    }
}
