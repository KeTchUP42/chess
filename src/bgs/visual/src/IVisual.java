package bgs.visual.src;

import bgs.area.IArea;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public interface IVisual {

    /**
     * Method draws area
     */
    void Draw(@NotNull IArea area);

    /**
     * Method have to communicate with players
     */
    String showMessage(String message, boolean getAnswer);
}
