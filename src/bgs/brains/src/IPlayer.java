package bgs.brains.src;

import bgs.brains.vars.StepLog;

import java.awt.*;

/**
 * @author Roman
 */
public interface IPlayer {

    /**
     * Method contains player's step logic
     */
    StepLog step();

    Color getColor();
}
