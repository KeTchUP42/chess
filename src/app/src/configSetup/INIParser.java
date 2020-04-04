package app.src.configSetup;

import objects.src.colors.GameColors;
import org.ini4j.Wini;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class INIParser {
    private Wini ini;

    public INIParser(String filepath) throws IOException {
        this.ini = new Wini(new File(filepath));
    }

    /**
     * Метод возвращает массив значений полученных из конфига
     *
     * @return Возвращает массив значений из ини файла
     */
    public String[] getConfig() {
        return new String[]{
                this.ini.get("defaultSettings", "areaType", String.class),
                this.ini.get("defaultSettings", "firstBrainColor", String.class),
                this.ini.get("defaultSettings", "firstBrainType", String.class),
                this.ini.get("defaultSettings", "secondBrainType", String.class),
                this.ini.get("defaultSettings", "secondBrainNickName", String.class),
                this.ini.get("defaultSettings", "firstBrainNickName", String.class),
        };
    }

    /**
     * Метод настройки базовых цветов в GameColors
     */
    public void setColorConfig() {
        try {
            Color firstColor = (Color) Color.class.getField(
                    this.ini.get("colorSettings", "FirstColor", String.class)).get(null);
            Color secondColor = (Color) Color.class.getField(
                    this.ini.get("colorSettings", "SecondColor", String.class)).get(null);
            if (firstColor == secondColor) return;
            GameColors.firstColor = firstColor;
            GameColors.secondColor = secondColor;
        } catch (Exception ignored) {
        }
    }

}
