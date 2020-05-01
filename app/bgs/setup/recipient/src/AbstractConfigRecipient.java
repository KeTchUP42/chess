package bgs.setup.recipient.src;

import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public abstract class AbstractConfigRecipient implements IConfigRecipient {

    protected IVisual visual;

    public AbstractConfigRecipient(IVisual visual) {
        this.visual = visual;
    }

    public abstract String[] findOutPlayersConfig(@NotNull String[] configItems);
}
