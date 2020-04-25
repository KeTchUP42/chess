package area;


import java.awt.*;


/**
 * @author Roman
 */
public abstract class AbstractArea extends AbstractAreaBase implements IArea {

    public AbstractArea(int areaWidth, int areaHeight) {
        super(areaWidth, areaHeight);
    }

    public boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].getColor() == objectColor &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, this);
    }

    public boolean runObjectAction(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].action(SquareNumber, this);
    }
}

