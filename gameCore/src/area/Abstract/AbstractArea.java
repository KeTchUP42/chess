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

    /**
     * Максимальное кол-во элементов в списках клонов объектов
     * Если значения будут НЕ равны то при достиженни лимита и при вызове recall оба списка будут очищены
     */
    protected static final int MAX_LAST_MOVED_OBJECT_ARRAY_LIST_SIZE = 100;
    protected static final int MAX_LAST_DESTROYED_OBJECT_ARRAY_LIST_SIZE = 100;

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
     * Добавлено дополнительное сравниевание цветов для ограничения управления другими игроками
     *
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @param objectColor        Цвет объекта
     * @return Возвращает удачно ли прошло движение
     */
    public boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor) {
        return this.isValidNumber(ObjectSquareNumber) && this.isValidNumber(SquareNumber)
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
        return this.isValidNumber(ObjectSquareNumber) && this.isValidNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].action(SquareNumber, this);
    }

    /**
     * Задаем последний объект который двигался
     * Удаляем первый при переполнении
     *
     * @param SquareNumber Номер клетки в которой находился сдвинутый объект
     */
    public void setLastMovedObject(int SquareNumber) {
        if (this.isValidNumber(SquareNumber))
            try {
                this.lastMovedObjectArrayList.add(this.getObjectFromList(SquareNumber) != null ?
                        this.getObjectFromList(SquareNumber).clone() : null);
                if (this.getLastMovedObjectArrayListSize() > AbstractArea.MAX_LAST_MOVED_OBJECT_ARRAY_LIST_SIZE)
                    this.lastMovedObjectArrayList.remove(0);
            } catch (CloneNotSupportedException ignored) {
            }
    }

    /**
     * Задаем последний объект который двигался
     * Удаляем первый при переполнении
     *
     * @param object IObject
     */
    public void setLastMovedObject(IObject object) {
        try {
            this.lastMovedObjectArrayList.add(object != null ? object.clone() : null);
            if (this.getLastMovedObjectArrayListSize() > AbstractArea.MAX_LAST_MOVED_OBJECT_ARRAY_LIST_SIZE)
                this.lastMovedObjectArrayList.remove(0);
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
    public IObject getLastMovedObject() {
        return this.lastMovedObjectArrayList.get(this.getLastMovedObjectArrayListSize() - 1);
    }

    /**
     * @return Size
     */
    public int getLastMovedObjectArrayListSize() {
        return this.lastMovedObjectArrayList.size();
    }


    /**
     * Задаем последний уничтоженный объект
     * Удаляем первый при переполнении
     *
     * @param SquareNumber Номер клетки на которой будет удален объект
     */
    public void setLastDestroyedObject(int SquareNumber) {
        if (this.isValidNumber(SquareNumber))
            try {
                this.lastDestroyedObjectArrayList.add(this.getObjectFromList(SquareNumber) != null ?
                        this.getObjectFromList(SquareNumber).clone() : null);
                if (this.getLastDestroyedObjectArrayListSize() > AbstractArea.MAX_LAST_DESTROYED_OBJECT_ARRAY_LIST_SIZE)
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
            if (this.getLastDestroyedObjectArrayListSize() > AbstractArea.MAX_LAST_DESTROYED_OBJECT_ARRAY_LIST_SIZE)
                this.lastDestroyedObjectArrayList.remove(0);
        } catch (CloneNotSupportedException ignored) {
        }
    }

    /**
     * Получаем итератор последних уничтоженных объектов
     *
     * @return IObject
     */
    public Iterator<IObject> getLastDestroyedObjectIterator() {
        return lastDestroyedObjectArrayList.iterator();
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
     * @return Size
     */
    public int getLastDestroyedObjectArrayListSize() {
        return this.lastDestroyedObjectArrayList.size();
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
                        this.getLastMovedObject().getObjectId() == this.getObjectFromList(index).getObjectId()) {
                    //Соответствующие передвижения на доске
                    this.deleteObject(this.getObjectFromList(index).getSquareNumber());
                    this.setObject(this.getLastMovedObject());
                    this.setObject(this.getLastDestroyedObject());
                    //Очищаем списки последних отпечатков
                    this.lastDestroyedObjectDelete();
                    this.lastMovedObjectDelete();
                    break;
                }
            }
        }
    }

    /**
     * Метод удалает последнюю полноценную запись клонов
     */
    public void lastDestroyedObjectDelete() {
        this.lastDestroyedObjectArrayList.remove(this.getLastDestroyedObjectArrayListSize() - 1);
    }

    /**
     * Метод удалает последнюю полноценную запись клонов
     */
    public void lastMovedObjectDelete() {
        this.lastMovedObjectArrayList.remove(this.getLastMovedObjectArrayListSize() - 1);
    }

    /**
     * Метод очищает ArrayLists с клонами объектов
     */
    public void lastObjectArrayListsClear() {
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
            this.lastObjectArrayListsClear();
            return false;
        }
        return true;
    }
}

