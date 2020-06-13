package bgs;

import bgs.setup.Ini.IniParser;
import bgs.setup.Ini.configList.IConfigList;
import bgs.setup.Ini.configList.InIConfigList;
import bgs.setup.recipient.ConfigRecipient;
import bgs.src.AbstractApplication;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

/**
 * @author Roman
 */
public final class Application extends AbstractApplication {

    public Application(IVisual Visual) {
        super(Visual);
    }

    public void run(String configFilePath) {
        try {
            this.loadSettings(configFilePath, new InIConfigList());
            this.start();
        } catch (Exception | Error exception) {
            this.Visual.showMessage(exception.getMessage(), false);
            System.exit(-1);
        } finally {
            this.Visual.showMessage("Exiting...", false);
        }
    }

    /**
     * Method gets settings from ini file or console
     */
    private void loadSettings(String configPath, @NotNull IConfigList configList) throws Exception {
        if (this.configPathValidation(configPath)) {
            this.applySettings(new IniParser(configPath).loadConfig(configList));
        } else {
            this.applySettings(new ConfigRecipient(this.Visual).findOutPlayersConfig(configList.getList()));
        }
    }

    private boolean configPathValidation(String configPath) {
        return configPath != null && Files.exists(Paths.get(configPath), LinkOption.NOFOLLOW_LINKS);
    }
}