package bgs.setup.recipient;

import bgs.setup.recipient.src.AbstractConfigRecipient;
import org.jetbrains.annotations.NotNull;
import visual.src.IVisual;

/**
 * @author Roman
 */
public class ConfigRecipient extends AbstractConfigRecipient {

    public ConfigRecipient(IVisual visual) {
        super(visual);
    }

    @Override
    public String[] findOutPlayersConfig(@NotNull String[] configItems) {
        String[] configData = new String[configItems.length];
        for (int index = 0; index < configItems.length; index++) {
            configData[index] = this.visual.showMessage("Please, write field: " + configItems[index], true).trim();
        }
        return configData;
    }
}
