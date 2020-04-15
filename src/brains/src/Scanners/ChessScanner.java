package brains.src.Scanners;

import area.Interfaces.IArea;
import objects.figures.King;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

/**
 * Класс содержит функционал для скана доски на определенные комбинации
 *
 * @author Roman
 */
public class ChessScanner {

    protected final IArea Area;

    /**
     * Конструктор
     *
     * @param area рабочая область
     */
    public ChessScanner(IArea area) {
        this.Area = area;
    }

    /**
     * Метод проверяет наличие короля на области
     *
     * @return Жив ли
     */
    public boolean isKingDead(Color color) {
        for (int index = 0; index < this.Area.getMaxSquareNumber(); index++) {
            if (this.Area.getObjectFromList(index) instanceof King && this.Area.getObjectFromList(index).getColor() == color)
                return false;
        }
        return true;
    }

    /**
     * Метод проверяет является ход безопасным для королей
     * Если нет то ход отменяется
     *
     * @param color Color
     * @return boolean
     */
    public boolean isKingUnderAttack(Color color) {
        for (King king : this.searchKings(color)) {
            for (int index = 0; index < this.Area.getMaxSquareNumber(); index++) {
                if (this.Area.getObjectFromList(index) != null &&
                        this.Area.getObjectFromList(index).isInRange(king.getSquareNumber(), this.Area)) {
                    this.Area.undoStep(1);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод ищет всех королей с заданным цветом и возвращает ArrayList<King>
     *
     * @param color Color
     * @return ArrayList<King>
     */
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
