package objects.figures.src.Abstract;

import area.src.Interfaces.IArea;
import objects.src.Abstract.AbstractObject;

import java.awt.*;


public abstract class AbstractFigure extends AbstractObject {


    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractFigure(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    /**
     * @param SquareNumber Куда объект хочет ударить/попасть
     * @param area         Область где стоит объект
     * @return boolean
     */
    public boolean isInRange(int SquareNumber, IArea area) {
        return super.isInRange(SquareNumber, area) ||
                area.getObjectFromList(SquareNumber).getColor() != this.getColor();
    }


}
