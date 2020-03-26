package game.core.objects.src.Interfaces;

import game.core.area.src.Interfaces.IArea;

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


}
