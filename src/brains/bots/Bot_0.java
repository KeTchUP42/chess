package brains.bots;

import area.src.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Interfaces.IPlayer;
import visual.Interfaces.IVisual;

import java.awt.*;

public class Bot_0 extends AbstractPlayer implements IPlayer {

    public Bot_0(IArea area, Color color, IVisual visual) {
        super(area, color, visual);
    }

    /**
     * @return // 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        //TODO
        this.visual.sendMessage("Bot_0 сделал ход", false, false);
        return 0;
    }
}
