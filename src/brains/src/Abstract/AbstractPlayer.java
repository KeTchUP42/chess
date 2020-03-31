package brains.src.Abstract;

import area.src.Interfaces.IArea;
import objects.figures.King;
import visual.Interfaces.IVisual;

import java.awt.*;

public abstract class AbstractPlayer {
    protected IArea boardArea;
    protected Color Color;
    protected IVisual visual;
    protected int stepNumber = 0;

    public AbstractPlayer(IArea boardArea, Color color, IVisual visual) {
        this.boardArea = boardArea;
        this.Color = color;
        this.visual = visual;
    }

    /**
     * @return Возвращает числовой лог
     */
    public abstract int step();

    /**
     * Метод возвращает цвет
     *
     * @return Тип Color
     */
    public Color getColor() {
        return this.Color;
    }

    /**
     * Проверка короля
     *
     * @return Жив ли
     */
    protected boolean isKingAlive(String botName) {
        //Проверка на проигрыш
        boolean isGameValid = false;
        for (int i = 0; i < this.boardArea.getMaxSquareNumber(); i++) {
            if (this.boardArea.getObjectFromList(i) instanceof King && this.boardArea.getObjectFromList(i).getColor() == this.Color)
                isGameValid = true;
        }
        if (!isGameValid) this.visual.sendMessage(botName + " проиграл на " + this.stepNumber + " ходе.", false, false);

        return isGameValid;
    }
}
