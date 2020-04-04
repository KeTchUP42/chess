package brains.src.Abstract;

import area.Interfaces.IArea;
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
     * @param timeMilSecs Кол-во миллисекунд
     */
    protected int returnZero(int timeMilSecs) {
        try {
            Thread.sleep(timeMilSecs);
        } catch (Exception e) {
            this.visual.sendMessage(e.getMessage(), true, true);
        }
        this.stepNumber++;
        return 0;
    }
}
