package bgs.objects;

import bgs.area.IArea;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractObject implements IObject {

    protected int lastPosition;

    protected int squareNumber;

    protected Color color;

    public AbstractObject(int squareNumber, Color color) {
        this.squareNumber = squareNumber;
        this.color = color;
    }

    public boolean move(int SquareNumber, IArea area) {
        boolean isInRange = this.isInRange(SquareNumber, area);
        if (isInRange) {
            this.lastPosition = this.squareNumber;
            this.squareNumber = SquareNumber;
            area.putObject(this);
            area.deleteObject(this.lastPosition);
        }
        return isInRange;
    }

    public boolean action(int SquareNumber, IArea area) {
        return this.isActionable(SquareNumber, area);
    }

    public boolean isInRange(int SquareNumber, @NotNull IArea area) {
        return area.isValidSquareNumber(SquareNumber) && area.getObjectFromList(SquareNumber) == null;
    }

    public boolean isActionable(int SquareNumber, @NotNull IArea area) {
        return area.isValidSquareNumber(SquareNumber);
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
