package area;

import objects.IObject;

/**
 * @author Roman
 */
abstract class AbstractAreaBase {

    protected int maxSquareNumber;

    protected int areaWidth;

    protected int areaHeight;

    protected IObject[] ObjectList;

    public AbstractAreaBase(int areaWidth, int areaHeight) {
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        this.maxSquareNumber = this.areaWidth * this.areaHeight;
        this.ObjectList = new IObject[this.maxSquareNumber];
    }

    public boolean isValidSquareNumber(int SquareNumber) {
        return SquareNumber >= 0 && SquareNumber < this.maxSquareNumber;
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

    public IObject getObjectFromList(int ObjectSquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) ? this.ObjectList[ObjectSquareNumber] : null;
    }

    public int getXCoordinate(int SquareNumber) {
        return SquareNumber % this.getAreaWidth();
    }

    public int getYCoordinate(int SquareNumber) {
        return SquareNumber / this.getAreaWidth();
    }

    public int getSquareNumber(int XCoordinate, int YCoordinate) {
        return YCoordinate * this.getAreaWidth() + XCoordinate;
    }

    public boolean moveObject(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, (IArea) this);
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
}
