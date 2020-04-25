package setup.INI;

import org.ini4j.Wini;
import org.jetbrains.annotations.NotNull;
import src.GameColors;

import java.awt.*;
import java.io.File;
import java.io.IOException;

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
     * Method apply configs for  GameColors
     */
    protected void applyColorConfig() {
        try {
            Color firstColor = (Color) Color.class.getField(
                    this.ini.get("ColorSettings", "FirstColor", String.class)).get(null);
            Color secondColor = (Color) Color.class.getField(
                    this.ini.get("ColorSettings", "SecondColor", String.class)).get(null);
            if (firstColor == secondColor) return;
            GameColors.firstStepColor = firstColor;
            GameColors.secondStepColor = secondColor;
        } catch (Exception ignored) {
        }
    }
}
