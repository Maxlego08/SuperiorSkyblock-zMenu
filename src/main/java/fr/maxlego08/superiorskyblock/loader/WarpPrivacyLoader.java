package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskyblock.buttons.warps.WarpManagePrivacyButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class WarpPrivacyLoader extends SuperiorButtonLoader {

    public WarpPrivacyLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "WARP_MANAGE_PRIVACY");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        var publicMessage = configuration.getString(path + "public", "Public Warp");
        var privateMessage = configuration.getString(path + "private", "Private Warp");
        return new WarpManagePrivacyButton(plugin, publicMessage, privateMessage);
    }
}
