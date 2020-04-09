package area.Interfaces;

import objects.Interfaces.IObject;

import java.awt.*;
import java.util.Iterator;

/**
 * @author Roman
 */
public interface IArea {

    /**
     * Метод удаляет объект на заданной клетке
     *
     * @param ObjectSquareNumber Номер клетки на которой нужно "удалить" объект
     */
    void deleteObject(int ObjectSquareNumber);

    /**
     * Помещаем объект на область если клетка пустая
     *
     * @param object Объект
     */
    void setObject(IObject object);

    /**
     * Небезопасное движение объекта по области
     *
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @return Возвращает удачно ли прошло движение
     */
    boolean moveObject(int ObjectSquareNumber, int SquareNumber);

    /**
     * Безопасное движение объекта по области
     * Добавлено дополнительное сравниевание цветов для ограничения управления другими игроками
     *
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @param objectColor        Цвет объекта
     * @return Возвращает удачно ли прошло движение
     */
    boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor);

    /**
     * Метод активирует action объекта на заданной клетке
     *
     * @param ObjectSquareNumber Область где стоит объект
     * @param SquareNumber       Номер клетки для action объекта
     * @return Возвращает возможно ли это
     */
    boolean runObjectAction(int ObjectSquareNumber, int SquareNumber);

    /**
     * Метод возвращает уникальный накопленный номер
     *
     * @return getObjectId
     */
    long getObjectId();

    /**
     * @return Возвращает ширину облатсти
     */
    int getAreaWidth();

    /**
     * @return Возвращает высоту облатсти
     */
    int getAreaHeight();

    /**
     * @return Возвращает макс кол-во клеток
     */
    int getMaxSquareNumber();

    /**
     * @param ObjectSquareNumber Номер клетки на которой находится объект
     * @return Возвращает нужный объект или null
     */
    IObject getObjectFromList(int ObjectSquareNumber);

    /**
     * Возвращает координату X
     *
     * @param SquareNumber Номер клетки
     * @return X
     */
    int getXCoordinate(int SquareNumber);

    /**
     * Возвращает координату Y
     *
     * @param SquareNumber Номер клетки
     * @return Y
     */
    int getYCoordinate(int SquareNumber);

    /**
     * Конвертирует координаты в номер клетки
     *
     * @param XCoordinate X
     * @param YCoordinate Y
     * @return Номер клетки
     */
    int getSquareNumber(int XCoordinate, int YCoordinate);

    /**
     * @return Size
     */
    int getLastMovedObjectArrayListSize();

    /**
     * Метод возвращает последний сдвинутый объект
     *
     * @return IObject
     */
    IObject getLastMovedObject();

    /**
     * Метод возвращает итератор последних объектов которые двигались
     *
     * @return IObject
     */
    Iterator<IObject> getLastMovedObjectIterator();

    /**
     * Метод задает последний объект который двигался
     * Удаляем первый при переполнении
     *
     * @param SquareNumber int
     */
    void setLastMovedObject(int SquareNumber);

    /**
     * Задаем последний объект который двигался
     * Удаляем первый при переполнении
     *
     * @param object IObject
     */
    void setLastMovedObject(IObject object);

    /**
     * @return LastKilledObjectArrayListSize
     */
    int getLastDestroyedObjectArrayListSize();

    /**
     * Метод возвращает последний уничтоженый объект
     *
     * @return IObject
     */
    IObject getLastDestroyedObject();

    /**
     * Метод возвращает итератор последних объектов которых уничтожили
     *
     * @return IObject
     */
    Iterator<IObject> getLastDestroyedObjectIterator();

    /**
     * Метод задает последний объект которого уничтожили
     * Удаляем первый при переполнении
     *
     * @param SquareNumber int
     */
    void setLastDestroyedObject(int SquareNumber);

    /**
     * Метод задает последний объект которого уничтожили
     * Удаляем первый при переполнении
     *
     * @param object int
     */
    void setLastDestroyedObject(IObject object);

    /**
     * Восстанавливает несколько последних ходов
     */
    void recallStep(int loopTimes);

    /**
     * Метод удалает последнюю полноценную запись клонов
     */
    void lastDestroyedObjectDelete();

    /**
     * Метод удалает последнюю полноценную запись клонов
     */
    void lastMovedObjectDelete();

    /**
     * Метод очищает ArrayLists с клонами объектов
     */
    void lastObjectArrayListsClear();
}
