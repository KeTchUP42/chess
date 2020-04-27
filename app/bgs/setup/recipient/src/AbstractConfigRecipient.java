package bgs.setup.recipient.src;

import org.jetbrains.annotations.NotNull;
import visual.src.IVisual;

/**
 * @author Roman
 */
public abstract class AbstractConfigRecipient implements IConfigRecipient {

    protected IVisual visual;

    public AbstractConfigRecipient(IVisual visual) {
        this.visual = visual;
    }

    public abstract String[] getPlayerConfigs(@NotNull String[] configItems);
}
