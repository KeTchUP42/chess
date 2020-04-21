package area;

import area.snapshot.SnapShot;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Roman
 */
public abstract class AbstractArea extends AbstractAreaBase implements IArea {

    /**
     * Максимальная длина истории снимков
     */
    public static final int MAX_SNAPSHOT_HISTORY_SIZE = 100;

    /**
     * Снимки
     */
    protected ArrayList<SnapShot> snapShots = new ArrayList<>();

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
     * Восстанавливает несколько последних ходов
     */
    public void undoStep(int loopTimes) {
        for (int loopT = 0; loopT < loopTimes; loopT++) {
            //Проверка списков
            if (this.getSnapShotHistorySize() == 0) return;
            //Ищем объект под отпечаток
            for (int index = 0; index < this.getMaxSquareNumber(); index++) {
                if (this.getObjectFromList(index) != null &&
                        this.getLastSnapShot().getMovedObject().getObjectId() == this.getObjectFromList(index).getObjectId()) {
                    //Соответствующие передвижения на области
                    this.deleteObject(this.getObjectFromList(index).getSquareNumber());
                    this.putObject(this.getLastSnapShot().getMovedObject());
                    this.putObject(this.getLastSnapShot().getDestroyedObject());

                    //Очищаем последний отпечаток
                    this.removeLastSnapShot();
                    break;
                }
            }
        }
    }

    /**
     * Метод возвращает последний снимок
     *
     * @return SnapShot
     */
    public SnapShot getLastSnapShot() {
        return this.snapShots.get(this.getSnapShotHistorySize() - 1);
    }

    /**
     * Метод очищает ArrayLists с клонами объектов
     */
    public void clearSnapShotHistory() {
        this.snapShots.clear();
    }

    /**
     * Получаем итератор снимков
     *
     * @return Iterator<SnapShot>
     */
    public Iterator<SnapShot> getSnapShotIterator() {
        return this.snapShots.iterator();
    }

    /**
     * Метод записывает новый снимок
     *
     * @param snapShot Новый снимок
     */
    public void setSnapShot(SnapShot snapShot) {
        this.snapShots.add(snapShot);
        if (this.getSnapShotHistorySize() > MAX_SNAPSHOT_HISTORY_SIZE)
            this.snapShots.remove(0);
    }

    /**
     * Метод возвращает количество снимков
     *
     * @return int
     */
    public int getSnapShotHistorySize() {
        return this.snapShots.size();
    }

    /**
     * Метод удаляет последний снимок
     */
    public void removeLastSnapShot() {
        this.snapShots.remove(this.getSnapShotHistorySize() - 1);
    }
}

