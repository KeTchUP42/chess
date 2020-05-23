package bgs.setup.Ini;

import bgs.setup.Ini.configList.ConfigFields;
import bgs.setup.Ini.configList.IConfigList;
import bgs.visual.src.GameColors;
import org.ini4j.Wini;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Roman
 */
public abstract class AbstractIniParser {

    protected final Wini ini;

    public AbstractIniParser(String filepath) throws IOException {
        this.ini = new Wini(new File(filepath));
    }

    /**
     * Method read configs from ini file
     */
    public abstract String[] loadConfig(@NotNull IConfigList configList);

    /**
     * Method applies configs for GameColors
     */
    protected void applyColorConfig(String[][] colorConfigList) {
        try {
            Color firstColor = (Color) Color.class.getField(
                    this.ini.get(colorConfigList[ConfigFields.FIRST_COLOR][0], colorConfigList[ConfigFields.FIRST_COLOR][1], String.class)).get(null);
            Color secondColor = (Color) Color.class.getField(
                    this.ini.get(colorConfigList[ConfigFields.SECOND_COLOR][0], colorConfigList[ConfigFields.SECOND_COLOR][1], String.class)).get(null);
            if (firstColor == secondColor) return;
            GameColors.firstColor = firstColor;
            GameColors.secondColor = secondColor;
        } catch (Exception ignored) {
        }
    }
}
