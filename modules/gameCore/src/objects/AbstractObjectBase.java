package objects;

import area.IArea;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
abstract class AbstractObjectBase {

    protected int lastPosition;

    protected int squareNumber;

    protected Color color;

    public AbstractObjectBase(int squareNumber, Color color) {
        this.squareNumber = squareNumber;
        this.color = color;
    }

    public boolean isInRange(int SquareNumber, @NotNull IArea area) {
        return area.getObjectFromList(SquareNumber) == null;
    }

    public int getSquareNumber() {
        return this.squareNumber;
    }

    public Color getColor() {
        return this.color;
    }

    public int getLastPosition() {
        return this.lastPosition;
    }
}
