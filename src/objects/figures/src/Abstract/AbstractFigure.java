package objects.figures.src.Abstract;

import area.Interfaces.IArea;
import objects.Abstract.AbstractObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractFigure extends AbstractObject {

    /**
     * Стартовая позиция фигуры
     */
    protected int startPosition;

    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractFigure(int squareNumber, Color color) {
        super(squareNumber, color);
        this.startPosition = squareNumber;
    }

    /**
     * @param SquareNumber Куда объект хочет ударить/попасть
     * @param area         Область где стоит объект
     * @return boolean
     */
    public boolean isInRange(int SquareNumber, @NotNull IArea area) {
        return super.isInRange(SquareNumber, area) ||
                area.getObjectFromList(SquareNumber).getColor() != this.getColor();
    }
}
