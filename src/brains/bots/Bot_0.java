package brains.bots;

import area.src.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Interfaces.IPlayer;
import visual.src.Interfaces.IVisual;

public class Bot_0 extends AbstractPlayer implements IPlayer {


    public Bot_0(IArea boardArea, java.awt.Color color, IVisual visual, String nickName) {
        super(boardArea, color, visual, nickName);
    }

    /**
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        if (this.isKingDead()) {
            this.visual.sendMessage(this.nickName + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return 1;
        }
        while (true) {
            if (this.boardArea.moveObjectSafe((int) (Math.random() * this.boardArea.getMaxSquareNumber())
                    , (int) (Math.random() * this.boardArea.getMaxSquareNumber()), this.Color))
                return super.sleepReturn(0, 1000);
        }
    }
}
