package brains.bots;

import area.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Interfaces.IPlayer;
import brains.src.sranners.ChessScanner;
import visual.src.Interfaces.IVisual;

public class Bot_0 extends AbstractPlayer implements IPlayer {

    /**
     * Сканер области, помощник бота
     */
    ChessScanner scanner = new ChessScanner(this.boardArea);

    public Bot_0(IArea boardArea, java.awt.Color color, IVisual visual, String nickName) {
        super(boardArea, color, visual, nickName);
    }

    /**
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        if (this.scanner.isKingDead(this.Color)) {
            this.visual.sendMessage(this.nickName + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return 1;
        }
        int index = 0;
        do {
            if (index == 1000) {
                this.visual.sendMessage(this.nickName + " проиграл на " + this.stepNumber + " ходе.", false, false);
                return 1;
            } else index++;

            while (true) {
                if (this.boardArea.moveObjectSafe((int) (Math.random() * this.boardArea.getMaxSquareNumber()),
                        (int) (Math.random() * this.boardArea.getMaxSquareNumber()),
                        this.Color)) break;
            }
        } while (this.scanner.kingUnderAttack(this.Color));
        return this.returnZero(1500);
    }
}
