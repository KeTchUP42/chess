package bgs.brains.scanners;

import bgs.area.IArea;
import bgs.objects.figures.King;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Roman
 */
public class ChessScanner {

    protected final IArea Area;

    public ChessScanner(IArea area) {
        this.Area = area;
    }

    public boolean isKingDead(Color color) {
        for (int index = 0; index < this.Area.getMaxSquareNumber(); index++) {
            if (this.Area.getObjectFromList(index) instanceof King && this.Area.getObjectFromList(index).getColor() == color)
                return false;
        }
        return true;
    }

    public boolean isKingUnderAttack(Color color) {
        for (King king : this.searchKings(color)) {
            for (int index = 0; index < this.Area.getMaxSquareNumber(); index++) {
                if (this.Area.getObjectFromList(index) != null &&
                        this.Area.getObjectFromList(index).isInRange(king.getSquareNumber(), this.Area)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected ArrayList<King> searchKings(@NotNull Color color) {
        ArrayList<King> kings = new ArrayList<>();
        for (int index = 0; index < this.Area.getMaxSquareNumber(); index++) {
            if (this.Area.getObjectFromList(index) != null && this.Area.getObjectFromList(index) instanceof King &&
                    this.Area.getObjectFromList(index).getColor() == color) {
                kings.add((King) this.Area.getObjectFromList(index));
            }
        }
        return kings;
    }
}
