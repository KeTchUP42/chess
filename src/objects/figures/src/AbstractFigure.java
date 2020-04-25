package objects.figures.src;

import area.IArea;
import objects.AbstractObject;
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
        return super.isInRange(SquareNumber, area) ||
                area.getObjectFromList(SquareNumber).getColor() != this.getColor();
    }
}
