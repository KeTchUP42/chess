package area.src.Abstract;

import area.src.Interfaces.IArea;
import objects.src.Interfaces.IObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Roman
 */
public abstract class AbstractArea extends AbstractAreaCore implements IArea {

    // Работают в связке чтобы восстанавливать последний ход
    protected ArrayList<IObject> lastMovedObjectArrayList = new ArrayList<>();

    protected ArrayList<IObject> lastKilledObjectArrayList = new ArrayList<>();

    /**
     * @param areaSize Размер области
     */
    public AbstractArea(int areaSize) {
        super(areaSize);
    }

    /**
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @param objectColor        Цвет объекта
     * @return Возвращает удачно ли прошло движение
     */
    public boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor) {

        return this.isValidNumber(ObjectSquareNumber) && this.isValidNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].getColor() == objectColor &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, this);
    }


    /**
     * Задаем последний объект который двигался удаленный объект
     *
     * @param SquareNumber номер клетки в которой будет удален объект
     */
    public boolean setLastMovedObject(int SquareNumber) {
        if (this.isValidNumber(SquareNumber))
            try {
                this.lastMovedObjectArrayList.add(this.getObjectFromList(SquareNumber).clone());
            } catch (CloneNotSupportedException e) {
                return false;
            }
        return this.isValidNumber(SquareNumber);
    }

    /**
     * Получаем итератор последних объектов которых двигали
     *
     * @return IObject
     */
    public Iterator<IObject> getLastMovedObjectIterator() {
        return this.lastMovedObjectArrayList.iterator();
    }

    /**
     * Задаем последний объект который двигался удаленный объект
     *
     * @param SquareNumber номер клетки в которой будет удален объект
     */
    public boolean setLastKilledObject(int SquareNumber) {
        if (this.isValidNumber(SquareNumber))
            try {
                this.lastKilledObjectArrayList.add(this.getObjectFromList(SquareNumber) != null ? this.getObjectFromList(SquareNumber).clone() : null);
            } catch (CloneNotSupportedException e) {
                return false;
            }
        return this.isValidNumber(SquareNumber);
    }

    /**
     * Получаем итератор последних объектов которых убили
     *
     * @return IObject
     */
    public Iterator<IObject> getLastKilledObjectIterator() {
        return lastKilledObjectArrayList.iterator();
    }


    /**
     * Восстанавливает несколько последних ходов
     */
    public void recallLastStep(int loopTimes) {
        for (int loopT = 0; loopT < loopTimes; loopT++) {
            //Проверка списков
            if (this.lastKilledObjectArrayList.size() == 0 || this.lastMovedObjectArrayList.size() == 0) {
                this.lastKilledObjectArrayList.clear();
                this.lastMovedObjectArrayList.clear();
                return;
            }
            //Ищем объект отпечаток
            for (int index = 0; index < this.maxSquareNumber; index++) {
                if (this.getObjectFromList(index) != null &&
                        this.lastMovedObjectArrayList.get((this.lastMovedObjectArrayList.size() - 1)).getSquareNumber() == this.getObjectFromList(index).getLastPosition()) {
                    //Соответствующие передвижения на доске
                    this.setObject(this.lastMovedObjectArrayList.get((this.lastMovedObjectArrayList.size() - 1)));
                    this.deleteObject(this.getObjectFromList(index).getSquareNumber());
                    this.setObject(this.lastKilledObjectArrayList.get(this.lastKilledObjectArrayList.size() - 1));
                    //Очищаем списки отпечатков
                    this.lastMovedObjectArrayList.remove(this.lastMovedObjectArrayList.size() - 1);
                    this.lastKilledObjectArrayList.remove(this.lastKilledObjectArrayList.size() - 1);

                    break;
                }
            }
        }
    }
}
