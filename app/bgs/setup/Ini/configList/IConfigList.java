package bgs.setup.Ini.configList;

import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public interface IConfigList {

    String @NotNull [] getList();

    String @NotNull [][] getFullList();

    String @NotNull [][] getColorList();
}
