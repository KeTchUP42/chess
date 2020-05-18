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
     * Player step
     *
     * @return
     */
    StepLog step();

    /**
     * Method reconfigures or builds new player
     *
     * @param area
     * @param color
     * @param visual
     * @param name
     * @return
     */
    IPlayer rebuild(@NotNull IArea area, @NotNull Color color, @NotNull IVisual visual, String name);

    Color getColor();
}
