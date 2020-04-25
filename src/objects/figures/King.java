package objects.figures;

import area.IArea;
import objects.figures.src.AbstractFigure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public class King extends AbstractFigure {

    public King(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return this.kingStepValid(SquareNumber, Board) &&
                super.isInRange(SquareNumber, Board);
    }

    private boolean kingStepValid(int SquareNumber, @NotNull IArea Board) {
        int yRange = Math.abs(Board.getYCoordinate(this.getSquareNumber()) - Board.getYCoordinate(SquareNumber));
        int xRange = Math.abs(Board.getXCoordinate(this.getSquareNumber()) - Board.getXCoordinate(SquareNumber));
        return (yRange == 1 && xRange == 1) || (yRange == 1 && xRange == 0) || (yRange == 0 && xRange == 1);
    }
}
