package game.core.area;

import game.core.area.src.Abstract.AbstractArea;
import game.core.area.src.Interfaces.IArea;
import game.core.objects.src.Interfaces.IObject;

public class BaseArea extends AbstractArea implements IArea {

    /**
     * @param areaSize Размер области
     */
    public BaseArea(int areaSize) {
        super(areaSize);
    }

    /**
     * @param object Объект
     * @return boolean
     */
    public boolean setObject(IObject object) {
        boolean isValid = this.isValidNumber(object.getSquareNumber()) &&
                this.getObjectFromList(object.getSquareNumber()) == null;
        if (isValid)
            this.ObjectList[object.getSquareNumber()] = object;
        return isValid;
    }

    /**
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @return Возвращает удачно ли прошло движение
     */
    @Override
    public boolean moveObject(int ObjectSquareNumber, int SquareNumber) {

        return this.isValidNumber(ObjectSquareNumber) && this.isValidNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, this);
    }


}
