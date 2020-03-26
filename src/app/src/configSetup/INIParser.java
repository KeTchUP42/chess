package app.src.configSetup;

import objects.figures.src.colors.GameColors;
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
                this.ini.get("defaultSettings", "firstPlayerColor", String.class),
                this.ini.get("defaultSettings", "firstPlayerType", String.class),
                this.ini.get("defaultSettings", "secondPlayerType", String.class),
                this.ini.get("defaultSettings", "secondPlayerNickName", String.class),
                this.ini.get("defaultSettings", "firstPlayerNickName", String.class),
        };
    }

    /**
     * Метод настройки базовых цветов в GameColors
     */
    public boolean setColorConfig() {
        try {
            GameColors.firstColor = (Color) Color.class.getField(
                    this.ini.get("colorSettings", "FirstColor", String.class)).get(null);
            GameColors.secondColor = (Color) Color.class.getField(
                    this.ini.get("colorSettings", "SecondColor", String.class)).get(null);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
