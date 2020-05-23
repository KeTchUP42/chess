package bgs.setup.Ini.configList;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public final class InIConfigList implements IConfigList {

    public String @NotNull [] getList() {
        return new String[]{
                "area_type",
                "step_order",
                "log_file_path",
                "first_player_type", "first_player_name",
                "second_player_type", "second_player_name",
        };
    }

    @Contract(value = " -> new", pure = true)
    @Override
    public String @NotNull [] @NotNull [] getFullList() {
        return new String[][]{
                new String[]{"AreaSettings", "area_type"},
                new String[]{"StepOrderSettings", "step_order"},
                new String[]{"LogSettings", "log_file_path"},
                new String[]{"FirstPlayerSettings", "first_player_type"},
                new String[]{"FirstPlayerSettings", "first_player_name"},
                new String[]{"SecondPlayerSettings", "second_player_type"},
                new String[]{"SecondPlayerSettings", "second_player_name"},
        };
    }

    @Contract(value = " -> new", pure = true)
    public String @NotNull [][] getColorList() {
        return new String[][]{
                new String[]{"ColorSettings", "first_color"},
                new String[]{"ColorSettings", "second_color"}
        };
    }
}
