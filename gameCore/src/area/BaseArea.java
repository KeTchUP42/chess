package area;

import area.src.Abstract.AbstractArea;
import area.src.Interfaces.IArea;
import objects.src.Interfaces.IObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public abstract class BaseArea extends AbstractArea implements IArea {

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
    public boolean setObject(@NotNull IObject object) {
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

    /**
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @param objectColor        Цвет объекта
     * @return Возвращает удачно ли прошло движение
     */
    @Override
    public boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor) {

        return this.isValidNumber(ObjectSquareNumber) && this.isValidNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].getColor() == objectColor &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, this);
    }
}
