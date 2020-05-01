package bgs.objects.figures;

import bgs.area.IArea;
import bgs.objects.figures.src.AbstractFigure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public class Bishop extends AbstractFigure {

    public Bishop(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    @Override
    public boolean isInRange(int SquareNumber, @NotNull IArea Board) {
        return super.isInRange(SquareNumber, Board) &&
                super.isWayFreeDiagonal(SquareNumber, Board);
    }

}
