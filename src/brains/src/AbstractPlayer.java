package brains.src;

import area.IArea;
import brains.src.repo.StepLog;
import tools.logger.Logger;
import visual.src.IVisual;

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

    public AbstractPlayer(IArea Area, Color Color, IVisual Visual, String Name) {
        this.Area = Area;
        this.Color = Color;
        this.Visual = Visual;
        this.Name = Name;
    }

    public abstract StepLog step();

    public Color getColor() {
        return this.Color;
    }

    /**
     * Correct normal log return
     *
     * @param squareNumber       square number
     * @param targetSquareNumber target square number
     * @param timeMilSecs        time
     * @return StepLog.NORMAL
     */
    protected StepLog finalize(int squareNumber, int targetSquareNumber, int timeMilSecs) {
        Logger.getGlobalLogger().info(squareNumber + " => " + targetSquareNumber); //TODO
        try {
            Thread.sleep(timeMilSecs);
        } catch (Exception e) {
            this.Visual.showMessage(e.getMessage(), true);
        }
        this.stepNumber++;
        return StepLog.NORMAL;
    }
}
