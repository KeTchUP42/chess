package bgs.visual.src;

import bgs.area.IArea;

/**
 * @author Roman
 */
public interface IVisual {

    /**
     * Method draws area
     */
    void Draw(IArea area);

    /**
     * Method have to communicate with players
     *
     * @param message
     * @param getAnswer
     * @return
     */
    String showMessage(String message, boolean getAnswer);
}
