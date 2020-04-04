package objects.figures;

import area.src.Interfaces.IArea;
import objects.figures.src.Abstract.AbstractFigure;
import objects.src.colors.GameColors;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class Pawn extends AbstractFigure {
    /**
     * Стартовая позиция пешки
     */
    private int startPosition;

    /**
     * @param squareNumber Номер клетки куда поставить фигуру
     * @param color        Цвет фигуры
     */
    public Pawn(int squareNumber, Color color) {
        super(squareNumber, color);
        this.startPosition = squareNumber;
    }


    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return this.pawnStepValid(SquareNumber, Board) &&
                super.isInRange(SquareNumber, Board);
    }

    /**
     * Метод проверки валадности хода для пешки
     *
     * @param SquareNumber Номер клетки куда нужно походить
     * @param Board        Доска
     * @return Возможно ли это
     */
    private boolean pawnStepValid(int SquareNumber, @NotNull IArea Board) {
        //Множитель решающий в каком направлении идет пешка
        int multiplier = this.color == GameColors.firstColor ? 1 : -1;
        //Один стандартный шаг
        int step = multiplier * Board.getAreaSize();
        //Номер клетки при стандартном шаге
        int ordStep = this.getSquareNumber() + step;
        //Может ли быть атакована запрашиваемая по номеру клетка
        boolean attackValid = Board.getObjectFromList(SquareNumber) != null && Board.getObjectFromList(SquareNumber).getColor() != this.getColor();
        //Проверка пустоты клетки
        boolean nullSquare = Board.getObjectFromList(SquareNumber) == null;

        return ((SquareNumber == this.getSquareNumber() + step * 2) && this.getSquareNumber() == this.startPosition && nullSquare && this.isWayFreePerpendicular(SquareNumber, Board)) ||
                (attackValid && SquareNumber == ordStep + 1 && Board.getXCoordinate(ordStep + 1) == Board.getXCoordinate(this.squareNumber) + 1) ||
                (attackValid && SquareNumber == ordStep - 1 && Board.getXCoordinate(ordStep - 1) == Board.getXCoordinate(this.squareNumber) - 1) ||
                (SquareNumber == ordStep && nullSquare);

    }
}
