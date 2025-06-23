package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.MenuItemStack;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.border.BorderColorToggleButton;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class BorderToggleLoader extends SuperiorButtonLoader {

    public BorderToggleLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "BORDER_TOGGLE");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        File file = new File(plugin.getDataFolder(), "inventories/border-color.yml");

        var inventoryManager = this.menuManager.getInventoryManager();
        MenuItemStack menuItemStackEnabled = inventoryManager.loadItemStack(configuration, path + "enable-border.", file);
        MenuItemStack menuItemStackDisabled = inventoryManager.loadItemStack(configuration, path + "disable-border.", file);

        return new BorderColorToggleButton(plugin, menuItemStackEnabled, menuItemStackDisabled);
    }
}
