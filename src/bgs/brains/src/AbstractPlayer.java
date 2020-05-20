package bgs.brains.src;

import bgs.area.IArea;
import bgs.brains.vars.StepLog;
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
     * Method contains player's step logic
     */
    public abstract StepLog step();

    /**
     * Method reconfigures this or builds a new player
     */
    public IPlayer rebuild(@NotNull IArea area, @NotNull Color color, @NotNull IVisual visual, String name) {
        this.Area = area;
        this.Color = color;
        this.Visual = visual;
        this.Name = name;
        return this;
    }

    /**
     * Method have finalize logic to correct system work
     */
    protected StepLog finalize(int timeMilSecs) {
        try {
            Thread.sleep(timeMilSecs);
        } catch (Exception e) {
            this.Visual.showMessage(e.getMessage(), true);
        }
        this.stepNumber++;
        return StepLog.NORMAL;
    }

    public Color getColor() {
        return this.Color;
    }
}
