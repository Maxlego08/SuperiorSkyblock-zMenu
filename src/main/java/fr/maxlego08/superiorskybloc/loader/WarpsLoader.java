package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.MenuItemStack;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.menu.exceptions.InventoryException;
import fr.maxlego08.menu.loader.MenuItemStackLoader;
import fr.maxlego08.menu.zcore.utils.loader.Loader;
import fr.maxlego08.superiorskybloc.buttons.UpgradeButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpsButton;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WarpsLoader extends SuperiorButtonLoader {
    public WarpsLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "WARPS");
    }

    @Override
    public Class<? extends Button> getButton() {
        return UpgradeButton.class;
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {

        File file = new File(this.plugin.getDataFolder(), "inventories/warps.yml");
        Loader<MenuItemStack> loader = new MenuItemStackLoader(this.menuManager.getInventoryManager());

        MenuItemStack editItemStack = null;
        try {
            editItemStack = loader.load(configuration, path + "edit-item.", file);
        } catch (InventoryException exception) {
            exception.printStackTrace();
        }

        return new WarpsButton(plugin, editItemStack);
    }
}
