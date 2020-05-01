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
public abstract class AbstractPlayer implements IPlayer, IChangeablePlayer {

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

    public abstract StepLog step();

    /**
     * Correct normal log return
     *
     * @param squareNumber       square number
     * @param targetSquareNumber target square number
     * @param timeMilSecs        time to wait
     * @return StepLog.NORMAL
     */
    protected StepLog finalize(int squareNumber, int targetSquareNumber, int timeMilSecs) {
        try {
            Logger.getGlobalLogger().info(squareNumber + " => " + targetSquareNumber);
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

    public IChangeablePlayer setArea(@NotNull IArea area) {
        this.Area = area;
        return this;
    }

    public IChangeablePlayer setColor(@NotNull Color color) {
        this.Color = color;
        return this;
    }

    public IChangeablePlayer setVisual(@NotNull IVisual visual) {
        this.Visual = visual;
        return this;
    }

    public IChangeablePlayer setName(String name) {
        this.Name = name;
        return this;
    }
}
