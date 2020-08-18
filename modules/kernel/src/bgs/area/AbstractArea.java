package bgs.area;

import bgs.objects.IObject;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractArea implements IArea {

    protected int maxSquareNumber;

    protected int areaWidth;

    protected int areaHeight;

    protected IObject[] ObjectList;

    public AbstractArea(int areaWidth, int areaHeight) {
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        this.maxSquareNumber = this.areaWidth * this.areaHeight;
        this.ObjectList = new IObject[this.maxSquareNumber];
    }

    public int getAreaWidth() {
        return this.areaWidth;
    }

    public int getAreaHeight() {
        return this.areaHeight;
    }

    public int getMaxSquareNumber() {
        return this.maxSquareNumber;
    }

    public int getXCoordinate(int SquareNumber) {
        return SquareNumber % this.areaWidth;
    }

    public int getYCoordinate(int SquareNumber) {
        return SquareNumber / this.areaWidth;
    }

    public int getSquareNumber(int XCoordinate, int YCoordinate) {
        return YCoordinate * this.areaWidth + XCoordinate;
    }

    public boolean isValidSquareNumber(int SquareNumber) {
        return SquareNumber >= 0 && SquareNumber < this.maxSquareNumber;
    }

    public boolean moveObject(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, this);
    }

    public void putObject(IObject object) {
        if (object != null && this.isValidSquareNumber(object.getSquareNumber())) {
            this.ObjectList[object.getSquareNumber()] = object;
        }
    }

    public void deleteObject(int ObjectSquareNumber) {
        if (this.isValidSquareNumber(ObjectSquareNumber)) {
            this.ObjectList[ObjectSquareNumber] = null;
        }
    }

    public boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].getColor() == objectColor &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, this);
    }

    public boolean runObjectAction(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null
                && this.ObjectList[ObjectSquareNumber].action(SquareNumber, this);
    }

    public IObject getObjectFromList(int ObjectSquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) ? this.ObjectList[ObjectSquareNumber] : null;
    }
}
