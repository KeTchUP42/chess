package brains.src.Abstract;

import area.src.Interfaces.IArea;
import objects.figures.King;
import visual.src.Interfaces.IVisual;

import java.awt.*;

public abstract class AbstractPlayer {
    protected IArea boardArea;
    protected Color Color;
    protected IVisual visual;
    protected int stepNumber = 0;
    protected String nickName;

    public AbstractPlayer(IArea boardArea, Color color, IVisual visual, String nickName) {
        this.boardArea = boardArea;
        this.Color = color;
        this.visual = visual;
        this.nickName = nickName;
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
    protected boolean isKingDead() {
        //Проверка на проигрыш
        boolean isKingDead = true;
        for (int index = 0; index < this.boardArea.getMaxSquareNumber(); index++) {
            if (this.boardArea.getObjectFromList(index) instanceof King && this.boardArea.getObjectFromList(index).getColor() == this.Color)
                isKingDead = false;
        }
        return isKingDead;
    }

    /**
     * @param value       То что нужно вернуть после задержки
     * @param timeMilSecs Кол-во миллисекунд
     * @return value
     */
    protected int sleepReturn(int value, int timeMilSecs) {
        try {
            Thread.sleep(timeMilSecs);
        } catch (Exception e) {
            this.visual.sendMessage(e.getMessage(), true, true);
        }
        this.stepNumber++;
        return value;
    }
}
