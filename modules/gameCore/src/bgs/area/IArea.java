package bgs.area;


import bgs.objects.IObject;

import java.awt.*;

/**
 * @author Roman
 */
public interface IArea {

    /**
     * Method checks number of square
     *
     * @param SquareNumber
     * @return
     */
    boolean isValidSquareNumber(int SquareNumber);

    /**
     * Method deletes object on the area
     *
     * @param ObjectSquareNumber
     */
    void deleteObject(int ObjectSquareNumber);

    /**
     * Method puts object on the area
     *
     * @param object
     */
    void putObject(IObject object);

    /**
     * UnSafe moving on the area
     *
     * @param ObjectSquareNumber
     * @param SquareNumber
     * @return Result
     */
    boolean moveObject(int ObjectSquareNumber, int SquareNumber);

    /**
     * Safe moving on the area with color comparison
     *
     * @param ObjectSquareNumber
     * @param SquareNumber
     * @param objectColor
     * @return Result
     */
    boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor);

    /**
     * Method runs object action
     *
     * @param ObjectSquareNumber
     * @param SquareNumber
     * @return Result
     */
    boolean runObjectAction(int ObjectSquareNumber, int SquareNumber);

    /**
     * @param ObjectSquareNumber
     * @return Result or null
     */
    IObject getObjectFromList(int ObjectSquareNumber);

    int getAreaWidth();

    int getAreaHeight();

    int getMaxSquareNumber();

    int getXCoordinate(int SquareNumber);

    int getYCoordinate(int SquareNumber);

    int getSquareNumber(int XCoordinate, int YCoordinate);
}
