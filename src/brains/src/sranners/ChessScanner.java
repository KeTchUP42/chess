package brains.src.sranners;

import area.Interfaces.IArea;
import objects.figures.King;

import java.awt.*;
import java.util.ArrayList;

/**
 * Класс содержит функционал для скана доски на определенные комбинации
 */
public class ChessScanner {

    protected IArea Area;

    /**
     * Конструктор
     *
     * @param area рабочая область
     */
    public ChessScanner(IArea area) {
        this.Area = area;
    }


    /**
     * Проверка короля
     *
     * @return Жив ли
     */
    public boolean isKingDead(Color color) {
        //Проверка на проигрыш
        boolean isKingDead = true;
        for (int index = 0; index < this.Area.getMaxSquareNumber(); index++) {
            if (this.Area.getObjectFromList(index) instanceof King && this.Area.getObjectFromList(index).getColor() == color)
                isKingDead = false;
        }
        return isKingDead;
    }

    /**
     * @param color Color
     * @return boolean
     */
    public boolean kingUnderAttack(Color color) {
        for (King king : this.searchKings(color)) {
            for (int i = 0; i < this.Area.getMaxSquareNumber(); i++) {
                if (this.Area.getObjectFromList(i) != null &&
                        this.Area.getObjectFromList(i).isInRange(king.getSquareNumber(), this.Area)) {
                    this.Area.recallStep(1);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param color Color
     * @return ArrayList<King>
     */
    protected ArrayList<King> searchKings(Color color) {
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
