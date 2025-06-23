package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskyblock.buttons.GlobalWarpsButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class GlobalWarpsLoader extends SuperiorButtonLoader {

    public GlobalWarpsLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "GLOBAL_WARPS");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        boolean visitorWarps = configuration.getBoolean("visitor-warps");
        return new GlobalWarpsButton(this.plugin, visitorWarps);
    }
}
