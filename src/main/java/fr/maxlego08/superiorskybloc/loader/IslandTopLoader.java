package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.MenuItemStack;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.top.IslandTopButton;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class IslandTopLoader extends SuperiorButtonLoader {

    public IslandTopLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "TOP");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {

        File file = new File(plugin.getDataFolder(), "inventories/settings.yml");

        List<Integer> positions = configuration.getIntegerList(path + "positions");
        var manager = this.menuManager.getInventoryManager();
        MenuItemStack menuItemStackIsland = manager.loadItemStack(configuration, path + "island.", file);
        MenuItemStack menuItemStackNoIsland = manager.loadItemStack(configuration, path + "no-island.", file);

        return new IslandTopButton(plugin, menuItemStackIsland, menuItemStackNoIsland, positions);
    }
}
