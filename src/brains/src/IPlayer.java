package brains.src;

import brains.src.rep.StepLog;

import java.awt.*;

/**
 * @author Roman
 */
public interface IPlayer {

    /**
     * Players step
     *
     * @return StepLog
     */
    StepLog step();

    Color getColor();
}
