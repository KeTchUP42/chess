package bgs.objects.figures;

import bgs.area.IArea;
import bgs.objects.figures.src.AbstractFigure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public class Knight extends AbstractFigure {

    public Knight(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return super.isInRange(SquareNumber, Board) &&
                this.knightStepValid(SquareNumber, Board);
    }

    private boolean knightStepValid(int SquareNumber, @NotNull IArea Board) {
        int absY = Math.abs(Board.getYCoordinate(this.getSquareNumber()) - Board.getYCoordinate(SquareNumber));
        int absX = Math.abs(Board.getXCoordinate(this.getSquareNumber()) - Board.getXCoordinate(SquareNumber));
        return ((absY == 2) && (absX == 1)) || ((absX == 2) && (absY == 1));
    }
}
