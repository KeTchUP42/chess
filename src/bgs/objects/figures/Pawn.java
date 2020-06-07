package bgs.objects.figures;

import bgs.area.IArea;
import bgs.objects.figures.src.AbstractFigure;
import bgs.visual.src.GameColors;
import org.jetbrains.annotations.NotNull;

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
        return super.isInRange(SquareNumber, Board) &&
                this.pawnStepValid(SquareNumber, Board);
    }

    private boolean pawnStepValid(int SquareNumber, @NotNull IArea Board) {
        //Multiplier which decides pawn's step direction
        int multiplier = this.color == GameColors.firstColor ? 1 : -1;
        //One standard step
        int step = multiplier * Board.getAreaWidth();
        //Step number with standard step
        int ordStep = this.getSquareNumber() + step;
        //Attack valid check
        boolean attackValid = Board.getObjectFromList(SquareNumber) != null && Board.getObjectFromList(SquareNumber).getColor() != this.getColor();
        //Empty square check
        boolean nullSquare = Board.getObjectFromList(SquareNumber) == null;

        return ((SquareNumber == this.getSquareNumber() + step * 2) && this.getSquareNumber() == this.startPosition && nullSquare && this.isWayFreePerpendicular(SquareNumber, Board)) ||
                (attackValid && SquareNumber == ordStep + 1 && Board.getXCoordinate(ordStep + 1) == Board.getXCoordinate(this.squareNumber) + 1) ||
                (attackValid && SquareNumber == ordStep - 1 && Board.getXCoordinate(ordStep - 1) == Board.getXCoordinate(this.squareNumber) - 1) ||
                (SquareNumber == ordStep && nullSquare);
    }
}
