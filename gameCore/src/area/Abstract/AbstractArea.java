package area.Abstract;

import area.Interfaces.IArea;
import objects.Interfaces.IObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Roman
 */
public abstract class AbstractArea extends AbstractAreaCore implements IArea {

    //Работают в связке чтобы восстанавливать последний ход
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
    public void setLastMovedObject(int SquareNumber) {
        if (this.isValidNumber(SquareNumber))
            try {
                this.lastMovedObjectArrayList.add(this.getObjectFromList(SquareNumber).clone());
            } catch (CloneNotSupportedException ignored) {
            }
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
     * Возвращает последний сдвинутый объект
     *
     * @return IObject
     */
    public IObject getLastMoved() {
        return this.lastMovedObjectArrayList.get(this.getLastMovedObjectArrayListSize() - 1);
    }

    /**
     * @return Size
     */
    public int getLastMovedObjectArrayListSize() {
        return this.lastMovedObjectArrayList.size();
    }


    /**
     * Задаем последний объект который двигался удаленный объект
     *
     * @param SquareNumber номер клетки в которой будет удален объект
     */
    public void setLastKilledObject(int SquareNumber) {
        if (this.isValidNumber(SquareNumber))
            try {
                this.lastKilledObjectArrayList.add(this.getObjectFromList(SquareNumber) != null ? this.getObjectFromList(SquareNumber).clone() : null);
            } catch (CloneNotSupportedException ignored) {
            }
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
     * Возвращает последний уничтоженый объект
     *
     * @return IObject
     */
    public IObject getLastKilled() {
        return this.lastKilledObjectArrayList.get(this.getLastKilledObjectArrayListSize() - 1);
    }

    /**
     * @return Size
     */
    public int getLastKilledObjectArrayListSize() {
        return this.lastKilledObjectArrayList.size();
    }

    /**
     * Восстанавливает несколько последних ходов
     */
    public void recallStep(int loopTimes) {
        for (int loopT = 0; loopT < loopTimes; loopT++) {
            //Проверка списков
            if (!this.arrayListsValid()) return;
            //Ищем объект под отпечаток
            for (int index = 0; index < this.maxSquareNumber; index++) {
                if (this.getObjectFromList(index) != null &&
                        this.getLastMoved().getStartPosition() == this.getObjectFromList(index).getStartPosition()) {
                    //TODO допроверить
                    //Соответствующие передвижения на доске
                    this.deleteObject(this.getObjectFromList(index).getSquareNumber());
                    this.setObject(this.getLastMoved());
                    this.setObject(this.getLastKilled());
                    //Очищаем списки последних отпечатков
                    this.lastMovedObjectArrayList.remove(this.getLastMovedObjectArrayListSize() - 1);
                    this.lastKilledObjectArrayList.remove(this.getLastKilledObjectArrayListSize() - 1);

                    break;
                }
            }
        }
    }

    /**
     * @return boolean
     */
    protected boolean arrayListsValid() {
        if (this.getLastKilledObjectArrayListSize() == 0 ||
                this.getLastMovedObjectArrayListSize() == 0 ||
                this.getLastKilledObjectArrayListSize() != this.getLastMovedObjectArrayListSize()) {
            this.lastKilledObjectArrayList.clear();
            this.lastMovedObjectArrayList.clear();
            return false;
        }
        return true;
    }
}

