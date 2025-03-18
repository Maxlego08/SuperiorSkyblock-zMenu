package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.loader.ButtonLoader;
import fr.maxlego08.superiorskybloc.ZMenuManager;
import fr.maxlego08.superiorskybloc.ZMenusProvider;
import org.bukkit.plugin.Plugin;

public abstract class SuperiorButtonLoader implements ButtonLoader {

    protected final SuperiorSkyblockPlugin plugin;
    protected final ZMenuManager menuManager;
    private final String buttonName;

    public SuperiorButtonLoader(SuperiorSkyblockPlugin plugin, String buttonName) {
        this.plugin = plugin;
        this.menuManager = ((ZMenusProvider) plugin.getProviders().getMenusProvider()).getMenuManager();
        this.buttonName = buttonName;
    }

    @Override
    public Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public String getName() {
        return "SUPERIORSKYBLOCK_" + buttonName;
    }
}
