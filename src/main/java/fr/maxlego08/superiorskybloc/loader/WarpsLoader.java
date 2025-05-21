package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.MenuItemStack;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpsButton;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WarpsLoader extends SuperiorButtonLoader {
    public WarpsLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "WARPS");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {

        File file = new File(this.plugin.getDataFolder(), "inventories/warps.yml");

        MenuItemStack editItemStack = this.menuManager.getInventoryManager().loadItemStack(configuration, path + "edit-item.", file);

        return new WarpsButton(plugin, editItemStack);
    }
}
