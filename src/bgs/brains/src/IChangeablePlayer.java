package bgs.brains.src;

import bgs.area.IArea;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface IChangeablePlayer extends IPlayer {

    IChangeablePlayer setArea(@NotNull IArea area);

    IChangeablePlayer setVisual(@NotNull IVisual visual);

    IChangeablePlayer setColor(@NotNull Color color);

    IChangeablePlayer setName(String name);
}
