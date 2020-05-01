package bgs.setup.INI;

import bgs.visual.src.GameColors;
import org.ini4j.Wini;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Roman
 */
public abstract class AbstractINIParser {

    protected final Wini ini;

    public AbstractINIParser(String filepath) throws IOException {
        this.ini = new Wini(new File(filepath));
    }

    /**
     * Method gets data from ini file
     *
     * @return data from ini file
     */
    public abstract String[] loadConfig(String @NotNull [] configFields);

    /**
     * Method apply configs for GameColors
     */
    protected void applyColorConfig() {
        try {
            Color firstColor = (Color) Color.class.getField(
                    this.ini.get("ColorSettings", "FirstColor", String.class)).get(null);
            Color secondColor = (Color) Color.class.getField(
                    this.ini.get("ColorSettings", "SecondColor", String.class)).get(null);
            if (firstColor == secondColor) return;
            GameColors.firstColor = firstColor;
            GameColors.secondColor = secondColor;
        } catch (Exception ignored) {
        }
    }
}
