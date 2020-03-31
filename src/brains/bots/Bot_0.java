package brains.bots;

import area.src.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Interfaces.IPlayer;
import visual.Interfaces.IVisual;

import java.awt.*;

public class Bot_0 extends AbstractPlayer implements IPlayer {

    public Bot_0(IArea boardArea, Color color, IVisual visual) {
        super(boardArea, color, visual);
    }

    /**
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        if (!this.isKingAlive("Bot_0")) return 1;

        while (true) {
            if (this.boardArea.moveObjectSafe((int) (Math.random() * this.boardArea.getMaxSquareNumber())
                    , (int) (Math.random() * this.boardArea.getMaxSquareNumber()), this.Color))
                return this.sleepReturn(0, 2000);
        }
    }

    /**
     * @param value       То что нужно вернуть после задержки
     * @param timeMilSecs Кол во миллисекунд
     * @return value
     */
    private int sleepReturn(int value, int timeMilSecs) {
        try {
            Thread.sleep(timeMilSecs);
        } catch (Exception e) {
        }
        this.stepNumber++;
        return value;
    }
}
