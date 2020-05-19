package bgs.brains.src;

import bgs.area.IArea;
import bgs.brains.src.repo.StepLog;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public interface IPlayer {

    /**
     * Method contains player's step logic
     */
    StepLog step();

    /**
     * Method reconfigures this or builds a new player
     */
    IPlayer rebuild(@NotNull IArea area, @NotNull Color color, @NotNull IVisual visual, String name);

    Color getColor();
}
