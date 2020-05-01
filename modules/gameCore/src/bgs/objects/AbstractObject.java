package bgs.objects;

import bgs.area.IArea;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractObject extends AbstractObjectBase implements Cloneable, IObject {

    public AbstractObject(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    public boolean move(int SquareNumber, IArea area) {
        boolean isInRange = this.isInRange(SquareNumber, area);
        if (isInRange) {
            this.lastPosition = this.getSquareNumber();
            this.squareNumber = SquareNumber;
            area.putObject(this);
            area.deleteObject(this.lastPosition);
        }
        return isInRange;
    }

    public boolean action(int SquareNumber, IArea area) {
        return this.isActionable(SquareNumber, area);
    }

    /**
     * Method scans area and check is way free diagonal
     *
     * @param SquareNumber Target square number
     * @param area         Game area
     * @return Result
     */
    protected boolean isWayFreeDiagonal(int SquareNumber, @NotNull IArea area) {
        //Base step check
        if (!(Math.abs(area.getYCoordinate(this.getSquareNumber()) - area.getYCoordinate(SquareNumber)) ==
                Math.abs(area.getXCoordinate(this.getSquareNumber()) - area.getXCoordinate(SquareNumber))))
            return false;
        //Gets object coordinates
        int objectX = area.getXCoordinate(this.getSquareNumber());
        int objectY = area.getYCoordinate(this.getSquareNumber());
        //Gets target coordinates
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Gets multipliers
        int multiplier = targetX > objectX ? 1 : -1;
        int sizeMp = targetY > objectY ? 1 : -1;

        for (int index = this.getSquareNumber() + sizeMp * area.getAreaWidth() + multiplier;
             index != SquareNumber;
             index += sizeMp * area.getAreaWidth() + multiplier
        ) {
            if (area.getObjectFromList(index) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method scans area and check is way free perpendicular
     *
     * @param SquareNumber Target square number
     * @param area         Game bgs.area
     * @return Result
     */
    protected boolean isWayFreePerpendicular(int SquareNumber, @NotNull IArea area) {
        //Base step check
        if (!(area.getYCoordinate(this.getSquareNumber()) == area.getYCoordinate(SquareNumber)
                || area.getXCoordinate(this.getSquareNumber()) == area.getXCoordinate(SquareNumber))) return false;
        //Gets object coordinates
        int objectX = area.getXCoordinate(this.getSquareNumber());
        int objectY = area.getYCoordinate(this.getSquareNumber());
        //Gets target coordinates
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Gets multiplier
        int multiplier = 0;
        boolean numAlg = objectX == targetX;
        if (objectX == targetX)
            multiplier = targetY > objectY ? 1 : -1;
        else if (objectY == targetY)
            multiplier = targetX > objectX ? 1 : -1;

        for (int index = numAlg ?
                this.getSquareNumber() + multiplier * area.getAreaWidth() :
                this.getSquareNumber() + multiplier;
             index != SquareNumber;
             index += numAlg ? multiplier * area.getAreaWidth() : multiplier
        ) {
            if (area.getObjectFromList(index) != null) {
                return false;
            }
        }
        return true;
    }

    public IObject clone() throws CloneNotSupportedException {
        return (IObject) super.clone();
    }
}
