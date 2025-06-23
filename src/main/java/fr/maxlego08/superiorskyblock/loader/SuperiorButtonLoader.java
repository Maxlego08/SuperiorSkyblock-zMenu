package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.loader.ButtonLoader;
import fr.maxlego08.superiorskyblock.ZMenuManager;
import fr.maxlego08.superiorskyblock.ZMenusProvider;

public abstract class SuperiorButtonLoader extends ButtonLoader {

    protected final SuperiorSkyblockPlugin plugin;
    protected final ZMenuManager menuManager;

    public SuperiorButtonLoader(SuperiorSkyblockPlugin plugin, String buttonName) {
        super(plugin, "SUPERIORSKYBLOCK_" + buttonName);
        this.plugin = plugin;
        this.menuManager = ((ZMenusProvider) plugin.getProviders().getMenusProvider()).getMenuManager();
    }
}
