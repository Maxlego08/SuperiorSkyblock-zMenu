package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.GlobalWarpsButton;
import fr.maxlego08.superiorskybloc.buttons.bank.BankLogsSortButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class GlobalWarpsLoader extends SuperiorButtonLoader {

    public GlobalWarpsLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "GLOBAL_WARPS");
    }

    @Override
    public Class<? extends Button> getButton() {
        return BankLogsSortButton.class;
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        boolean visitorWarps = configuration.getBoolean("visitor-warps");
        return new GlobalWarpsButton(this.plugin, visitorWarps);
    }
}
