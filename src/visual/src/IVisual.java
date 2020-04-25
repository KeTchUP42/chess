package visual.src;

import area.IArea;

/**
 * @author Roman
 */
public interface IVisual {

    /**
     * Method draws the area
     */
    void Draw(IArea area);

    /**
     * Method have to communicate with player
     *
     * @param message   Message
     * @param getAnswer get answer
     * @return answer
     */
    String showMessage(String message, boolean getAnswer, boolean afterClear);
}
