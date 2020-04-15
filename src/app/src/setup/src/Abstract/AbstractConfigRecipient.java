package app.src.setup.src.Abstract;

import app.src.setup.src.Interfaces.IConfigRecipient;
import org.jetbrains.annotations.NotNull;
import visual.src.Interfaces.IVisual;

/**
 * @author Roman
 */
public abstract class AbstractConfigRecipient implements IConfigRecipient {

    protected IVisual visual;

    /**
     * @param visual IVisual
     */
    public AbstractConfigRecipient(IVisual visual) {
        this.visual = visual;
    }

    /**
     * Метод реализует логику получения информации от игрока
     *
     * @return Массив для loadSettings
     */
    public abstract String[] getPlayerConfigs(@NotNull String[] configItems);
}