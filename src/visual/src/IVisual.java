package visual.src;

import area.IArea;

/**
 * @author Roman
 */
public interface IVisual {

    /**
     * Method draws(redraws) area with objects
     */
    void Draw(IArea area);

    /**
     * Method have to communicate with players
     *
     * @param message   Message
     * @param getAnswer get answer
     * @return answer
     */
    String showMessage(String message, boolean getAnswer);
}
