package objects.Interfaces;

import area.Interfaces.IArea;

import java.awt.*;

/**
 * @author Roman
 */
public interface IObject {

    /**
     * Получаем номер клетки где должна стоять фигура
     *
     * @return SquareNumber
     */
    int getSquareNumber();

    /**
     * @return Colors
     */
    Color getColor();

    /**
     * Получаем номер клетки последней позиции
     *
     * @return SquareNumber
     */
    int getLastPosition();

    /**
     * Получаем стартовую позицию объекта
     *
     * @return SquareNumber
     */
    int getStartPosition();

    /**
     * Метод отвечает за перемещение объектов на области, работает в связке с this.isInRange
     *
     * @param SquareNumber Куда объект хочет ударить/попасть
     * @param Area         Область где стоит объект
     * @return Возвращает возможно ли это
     */
    boolean move(int SquareNumber, IArea Area);

    /**
     * Метод реализует уникальную логику движения каждого объект
     *
     * @param SquareNumber Куда объект хочет ударить
     * @param Board        Область где стоит объект
     * @return Возвращает возможно ли это
     */
    boolean isInRange(int SquareNumber, IArea Board);

    /**
     * Метод клонирования
     *
     * @return IObject
     * @throws CloneNotSupportedException Ошибка
     */
    IObject clone() throws CloneNotSupportedException;
}
