package brains.bots;

import area.Interfaces.IArea;
import brains.src.Abstract.AbstractPlayer;
import brains.src.Scanners.ChessScanner;
import visual.src.Interfaces.IVisual;

import java.awt.*;

/**
 * @author Roman
 */
public class Bot_0 extends AbstractPlayer {

    /**
     * Сканер области
     */
    protected ChessScanner scanner = new ChessScanner(this.Area);

    public Bot_0(IArea area, Color color, IVisual visual, String Name) {
        super(area, color, visual, Name);
    }

    /**
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        if (this.scanner.isKingDead(this.Color)) {
            this.Visual.sendMessage(this.Name + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return 1;
        }
        int index = 0;
        do {
            if (index == 1000) {
                this.Visual.sendMessage(this.Name + " проиграл на " + this.stepNumber + " ходе.", false, false);
                return 1;
            } else index++;

            while (true) {
                if (this.Area.moveObjectSafe((int) (Math.random() * this.Area.getMaxSquareNumber()),
                        (int) (Math.random() * this.Area.getMaxSquareNumber()),
                        this.Color)) break;
            }
        } while (this.scanner.isKingUnderAttack(this.Color));
        return this.returnZero(2500);
    }
}
