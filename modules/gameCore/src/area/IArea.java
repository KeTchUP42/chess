package area;


import objects.IObject;

import java.awt.*;

/**
 * @author Roman
 */
public interface IArea {

    /**
     * Method checks number of square
     *
     * @param SquareNumber square number
     * @return Result
     */
    boolean isValidSquareNumber(int SquareNumber);

    void deleteObject(int ObjectSquareNumber);

    /**
     * Method puts object on the area
     *
     * @param object object
     */
    void putObject(IObject object);

    /**
     * UnSafe moving on the area
     *
     * @param ObjectSquareNumber Object square number
     * @param SquareNumber       Target square number
     * @return Result
     */
    boolean moveObject(int ObjectSquareNumber, int SquareNumber);

    /**
     * Safe moving on the area with color comparison
     *
     * @param ObjectSquareNumber ObjectSquareNumber
     * @param SquareNumber       Target square number
     * @param objectColor        object color
     * @return Result
     */
    boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor);

    /**
     * Method runs object action
     *
     * @param ObjectSquareNumber Object square number
     * @param SquareNumber       Target square number
     * @return Result
     */
    boolean runObjectAction(int ObjectSquareNumber, int SquareNumber);

    int getAreaWidth();

    int getAreaHeight();

    int getMaxSquareNumber();

    /**
     * @param ObjectSquareNumber ObjectSquareNumber
     * @return Result or null
     */
    IObject getObjectFromList(int ObjectSquareNumber);

    int getXCoordinate(int SquareNumber);

    int getYCoordinate(int SquareNumber);

    int getSquareNumber(int XCoordinate, int YCoordinate);
}
