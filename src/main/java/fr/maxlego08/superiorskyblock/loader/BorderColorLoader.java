package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.enums.BorderColor;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskyblock.buttons.border.BorderColorButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class BorderColorLoader extends SuperiorButtonLoader {

    public BorderColorLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "BORDER_COLOR");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        BorderColor borderColor = BorderColor.valueOf(configuration.getString(path + "border-color", BorderColor.BLUE.name()));
        return new BorderColorButton(plugin, borderColor);
    }
}
