package bgs.brains.src;

import bgs.area.IArea;
import bgs.brains.src.repo.StepLog;
import bgs.tools.logger.Logger;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractPlayer implements IPlayer {

    protected IArea Area;

    protected Color Color;

    protected IVisual Visual;

    protected int stepNumber = 0;

    protected String Name;

    public AbstractPlayer(@NotNull IArea Area, @NotNull Color Color, @NotNull IVisual Visual, String Name) {
        this.Area = Area;
        this.Color = Color;
        this.Visual = Visual;
        this.Name = Name;
    }

    public AbstractPlayer() {
    }

    /**
     * Some step logic
     *
     * @return
     */
    public abstract StepLog step();

    /**
     * Method reconfigures or builds new player
     *
     * @param area
     * @param color
     * @param visual
     * @param name
     * @return
     */
    public IPlayer rebuild(@NotNull IArea area, @NotNull Color color, @NotNull IVisual visual, String name) {
        this.Area = area;
        this.Color = color;
        this.Visual = visual;
        this.Name = name;
        return this;
    }

    /**
     * Correct step finalize
     *
     * @param squareNumber
     * @param targetSquareNumber
     * @param timeMilSecs
     * @return
     */
    protected StepLog finalize(final int squareNumber, final int targetSquareNumber, int timeMilSecs) {
        try {
            Logger.getGlobalLogger().info(squareNumber + " => " + targetSquareNumber);
            Thread.sleep(timeMilSecs);
        } catch (Exception e) {
            this.Visual.showMessage(e.getMessage(), true);
        }
        this.stepNumber++;
        return StepLog.NORMAL;
    }

    /**
     * @return
     */
    public Color getColor() {
        return this.Color;
    }
}
