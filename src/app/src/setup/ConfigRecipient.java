package app.src.setup;

import app.src.setup.src.Abstract.AbstractConfigRecipient;
import org.jetbrains.annotations.NotNull;
import visual.src.Interfaces.IVisual;

/**
 * @author Roman
 */
public class ConfigRecipient extends AbstractConfigRecipient {

    /**
     * @param visual IVisual
     */
    public ConfigRecipient(IVisual visual) {
        super(visual);
    }

    /**
     * Метод реализует логику получения информации от игрока
     *
     * @return Массив для loadSettings
     */
    public String[] getPlayerConfigs(@NotNull String[] configItems) {
        String[] configData = new String[configItems.length];
        for (int index = 0; index < configItems.length; index++) {
            configData[index] = this.visual.showMessage("Введи параметр " + configItems[index], true, false).trim();
        }
        return configData;
    }
}
