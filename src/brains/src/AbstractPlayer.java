package brains.src;

import area.IArea;
import brains.src.rep.StepLog;
import logger.Logger;
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

    protected StepLog finalize(int squareNumber, int targetSquareNumber, int timeMilSecs) {
        Logger.globalLogger.info(squareNumber + " => " + targetSquareNumber); //TODO
        try {
            Thread.sleep(timeMilSecs);
        } catch (Exception e) {
            this.Visual.showMessage(e.getMessage(), true, true);
        }
        this.stepNumber++;
        return StepLog.NORMAL;
    }
}
