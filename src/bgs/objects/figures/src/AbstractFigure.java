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
                area.getObjectFromList(SquareNumber).getColor() != this.color);
    }

    /**
     * The method scans the area for the possibility of diagonal movement
     *
     * @param SquareNumber
     * @param area
     * @return Result
     */
    protected boolean isWayFreeDiagonal(int SquareNumber, @NotNull IArea area) {
        //Base step check
        if (!(Math.abs(area.getYCoordinate(this.squareNumber) - area.getYCoordinate(SquareNumber)) ==
                Math.abs(area.getXCoordinate(this.squareNumber) - area.getXCoordinate(SquareNumber)))) return false;
        //Gets object coordinates
        int objectX = area.getXCoordinate(this.squareNumber);
        int objectY = area.getYCoordinate(this.squareNumber);
        //Gets target coordinates
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Gets multipliers
        int multiplier = (targetX > objectX) ? 1 : -1;
        int sizeMp = (targetY > objectY) ? 1 : -1;

        for (int index = this.squareNumber + sizeMp * area.getAreaWidth() + multiplier;
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
     * The method scans the area for the possibility of vertical or horizontal movement
     *
     * @param SquareNumber
     * @param area
     * @return Result
     */
    protected boolean isWayFreePerpendicular(int SquareNumber, @NotNull IArea area) {
        //Base step check
        if (!(area.getYCoordinate(this.squareNumber) == area.getYCoordinate(SquareNumber)
                || area.getXCoordinate(this.squareNumber) == area.getXCoordinate(SquareNumber))) return false;
        //Gets object coordinates
        int objectX = area.getXCoordinate(this.squareNumber);
        int objectY = area.getYCoordinate(this.squareNumber);
        //Gets target coordinates
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Gets multiplier
        int multiplier = 0;
        boolean numAlg = (objectX == targetX);
        if (objectX == targetX)
            multiplier = (targetY > objectY) ? 1 : -1;
        else if (objectY == targetY)
            multiplier = (targetX > objectX) ? 1 : -1;

        for (int index = numAlg ?
                this.squareNumber + multiplier * area.getAreaWidth() :
                this.squareNumber + multiplier;
             index != SquareNumber;
             index += numAlg ? multiplier * area.getAreaWidth() : multiplier
        ) {
            if (area.getObjectFromList(index) != null) {
                return false;
            }
        }
        return true;
    }
}
