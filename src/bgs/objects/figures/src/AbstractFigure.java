package bgs.objects.figures.src;

import bgs.area.IArea;
import bgs.objects.AbstractObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractFigure extends AbstractObject {

    protected int startPosition;

    public AbstractFigure(int squareNumber, Color color) {
        super(squareNumber, color);
        this.startPosition = squareNumber;
    }

    public boolean isInRange(int SquareNumber, @NotNull IArea area) {
        return area.isValidSquareNumber(SquareNumber) && (super.isInRange(SquareNumber, area) ||
                area.getObjectFromList(SquareNumber).getColor() != this.getColor());
    }
}
