package objects.figures;

import area.IArea;
import objects.figures.src.AbstractFigure;
import org.jetbrains.annotations.NotNull;
import src.GameColors;

import java.awt.*;

/**
 * @author Roman
 */
public class Pawn extends AbstractFigure {

    public Pawn(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return this.pawnStepValid(SquareNumber, Board) &&
                super.isInRange(SquareNumber, Board);
    }

    private boolean pawnStepValid(int SquareNumber, @NotNull IArea Board) {
        //Множитель решающий в каком направлении идет пешка
        int multiplier = this.color == GameColors.firstStepColor ? 1 : -1;
        //Один стандартный шаг
        int step = multiplier * Board.getAreaWidth();
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
