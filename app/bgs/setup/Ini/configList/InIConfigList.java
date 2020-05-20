package bgs.setup.Ini.configList;

import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public final class InIConfigList implements IConfigList {

    public String @NotNull [] getList() {
        return new String[]{
                "area_type",
                "step_order",
                "first_player_type", "first_player_name",
                "second_player_type", "second_player_name",
                "log_file_path"
        };
    }

    public String @NotNull [] getColorList() {
        return new String[]{"first_color", "second_color"};
    }
}

