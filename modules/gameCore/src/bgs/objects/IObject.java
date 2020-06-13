package bgs.objects;

import bgs.area.IArea;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public interface IObject {

    /**
     * Method moves object on the area
     *
     * @param SquareNumber
     * @param area
     * @return Result
     */
    boolean move(int SquareNumber, IArea area);

    /**
     * Method can implement any action, works in conjunction with this.isActionable
     * Implementation in another classes
     *
     * @param SquareNumber
     * @param area
     * @return Result
     */
    boolean action(int SquareNumber, IArea area);

    /**
     * Method checks step valid for object
     *
     * @param SquareNumber
     * @param area
     * @return Result
     */
    boolean isInRange(int SquareNumber, IArea area);

    /**
     * Method checks action valid for object
     *
     * @param SquareNumber
     * @param area
     * @return Result
     */
    boolean isActionable(int SquareNumber, @NotNull IArea area);

    int getSquareNumber();

    Color getColor();

    int getLastPosition();
}
