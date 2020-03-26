package brains.src.Abstract;

import area.src.Interfaces.IArea;
import visual.Interfaces.IVisual;

import java.awt.*;

public abstract class AbstractPlayer {
    protected IArea Area;
    protected Color Color;
    protected IVisual visual;

    public AbstractPlayer(IArea area, Color color, IVisual visual) {
        this.Area = area;
        this.Color = color;
        this.visual = visual;
    }

    public abstract int step();

    /**
     * Метод возвращает цвет
     *
     * @return Тип Color
     */
    public Color getColor() {
        return this.Color;
    }
}
