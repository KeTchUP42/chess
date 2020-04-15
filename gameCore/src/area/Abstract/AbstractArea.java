package area.Abstract;

import area.Interfaces.IArea;
import objects.Interfaces.IObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Roman
 */
public abstract class AbstractArea extends AbstractAreaBase implements IArea {

    /**
     * Максимальная длина истории
     */
    public static final int MAX_OBJECT_HISTORY_SIZE = 100;

    /**
     * Работают в связке чтобы восстанавливать последний ход
     */
    protected ArrayList<IObject> lastMovedObjectArrayList = new ArrayList<>();
    protected ArrayList<IObject> lastDestroyedObjectArrayList = new ArrayList<>();

    /**
     * @param areaWidth  Ширина области
     * @param areaHeight Высота области
     */
    public AbstractArea(int areaWidth, int areaHeight) {
        super(areaWidth, areaHeight);
    }

    /**
     * Безопасное движение объекта по области
     * Добавлено дополнительное сравнивание цветов для ограничения управления другими игроками
     *
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @param objectColor        Цвет объекта
     * @return Возвращает удачно ли прошло движение
     */
    public boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].getColor() == objectColor &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, this, true);
    }

    /**
     * Метод активирует action объекта на заданной клетке
     *
     * @param ObjectSquareNumber Область где стоит объект
     * @param SquareNumber       Номер клетки для action объекта
     * @return Возвращает возможно ли это
     */
    public boolean runObjectAction(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].action(SquareNumber, this);
    }

    /**
     * Получаем итератор последних двигаемых объектов
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
    public IObject getLastMovedObject() {
        return this.lastMovedObjectArrayList.get(this.getLastMovedObjectArrayListSize() - 1);
    }

    /**
     * Задаем последний двигаемый объект
     * Удаляем первый при переполнении
     *
     * @param SquareNumber Номер клетки в которой находился сдвинутый объект
     */
    public void setLastMovedObject(int SquareNumber) {
        if (this.isValidSquareNumber(SquareNumber))
            try {
                this.lastMovedObjectArrayList.add(this.getObjectFromList(SquareNumber) != null ?
                        this.getObjectFromList(SquareNumber).clone() : null);
                if (this.getLastMovedObjectArrayListSize() > MAX_OBJECT_HISTORY_SIZE)
                    this.lastMovedObjectArrayList.remove(0);
            } catch (CloneNotSupportedException ignored) {
            }
    }

    /**
     * Задаем последний двигаемый объект
     * Удаляем первый при переполнении
     *
     * @param object IObject
     */
    public void setLastMovedObject(IObject object) {
        try {
            this.lastMovedObjectArrayList.add(object != null ? object.clone() : null);
            if (this.getLastMovedObjectArrayListSize() > MAX_OBJECT_HISTORY_SIZE)
                this.lastMovedObjectArrayList.remove(0);
        } catch (CloneNotSupportedException ignored) {
        }
    }

    /**
     * @return Size
     */
    public int getLastMovedObjectArrayListSize() {
        return this.lastMovedObjectArrayList.size();
    }

    /**
     * Получаем итератор последних уничтоженных объектов
     *
     * @return IObject
     */
    public Iterator<IObject> getLastDestroyedObjectIterator() {
        return this.lastDestroyedObjectArrayList.iterator();
    }

    /**
     * Возвращает последний уничтоженный объект
     *
     * @return IObject
     */
    public IObject getLastDestroyedObject() {
        return this.lastDestroyedObjectArrayList.get(this.getLastDestroyedObjectArrayListSize() - 1);
    }

    /**
     * Задаем последний уничтоженный объект
     * Удаляем первый при переполнении
     *
     * @param SquareNumber Номер клетки на которой будет удален объект
     */
    public void setLastDestroyedObject(int SquareNumber) {
        if (this.isValidSquareNumber(SquareNumber))
            try {
                this.lastDestroyedObjectArrayList.add(this.getObjectFromList(SquareNumber) != null ?
                        this.getObjectFromList(SquareNumber).clone() : null);
                if (this.getLastDestroyedObjectArrayListSize() > MAX_OBJECT_HISTORY_SIZE)
                    this.lastDestroyedObjectArrayList.remove(0);
            } catch (CloneNotSupportedException ignored) {
            }
    }

    /**
     * Задаем последний уничтоженный объект
     * Удаляем первый при переполнении
     *
     * @param object IObject
     */
    public void setLastDestroyedObject(IObject object) {
        try {
            this.lastDestroyedObjectArrayList.add(object != null ? object.clone() : null);
            if (this.getLastDestroyedObjectArrayListSize() > MAX_OBJECT_HISTORY_SIZE)
                this.lastDestroyedObjectArrayList.remove(0);
        } catch (CloneNotSupportedException ignored) {
        }
    }

    /**
     * @return Size
     */
    public int getLastDestroyedObjectArrayListSize() {
        return this.lastDestroyedObjectArrayList.size();
    }

    /**
     * Восстанавливает несколько последних ходов
     */
    public void undoStep(int loopTimes) {
        for (int loopT = 0; loopT < loopTimes; loopT++) {
            //Проверка списков
            if (!this.arrayListsValid()) return;
            //Ищем объект под отпечаток
            for (int index = 0; index < this.maxSquareNumber; index++) {
                if (this.getObjectFromList(index) != null &&
                        this.getLastMovedObject().getObjectId() == this.getObjectFromList(index).getObjectId()) {
                    //Соответствующие передвижения на области
                    this.deleteObject(this.getObjectFromList(index).getSquareNumber());
                    this.putObject(this.getLastMovedObject());
                    this.putObject(this.getLastDestroyedObject());
                    //Очищаем списки последних отпечатков
                    this.deleteLastDestroyedObject();
                    this.deleteLastMovedObject();
                    break;
                }
            }
        }
    }

    /**
     * Метод удаляет последнюю полноценную запись клона
     */
    public void deleteLastDestroyedObject() {
        this.lastDestroyedObjectArrayList.remove(this.getLastDestroyedObjectArrayListSize() - 1);
    }

    /**
     * Метод удаляет последнюю полноценную запись клона
     */
    public void deleteLastMovedObject() {
        this.lastMovedObjectArrayList.remove(this.getLastMovedObjectArrayListSize() - 1);
    }

    /**
     * Метод очищает ArrayLists с клонами объектов
     */
    public void clearLastObjectArrayLists() {
        this.lastDestroyedObjectArrayList.clear();
        this.lastMovedObjectArrayList.clear();
    }

    /**
     * Метод проверяет правильность заполнения списков отпечатков
     *
     * @return boolean
     */
    protected boolean arrayListsValid() {
        if (this.getLastDestroyedObjectArrayListSize() == 0 ||
                this.getLastMovedObjectArrayListSize() == 0 ||
                this.getLastDestroyedObjectArrayListSize() != this.getLastMovedObjectArrayListSize()) {
            this.clearLastObjectArrayLists();
            return false;
        }
        return true;
    }
}

