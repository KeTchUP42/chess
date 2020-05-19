package bgs.area.factory.src;

import bgs.area.IArea;

/**
 * @author Roman
 */
public interface IAreaFactory {

    /**
     * Method creates standard configured area
     */
    IArea createStandardArea();

    /**
     * Method creates test area
     */
    IArea createTestArea();
}
