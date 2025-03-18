package fr.maxlego08.superiorskybloc;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblock;
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.commands.SuperiorCommand;
import com.bgsoftware.superiorskyblock.api.modules.PluginModule;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public final class ZMenuModule extends PluginModule {

    private ZMenusProvider menusProvider;

    public ZMenuModule() {
        super("zMenu", "Maxlego08");
    }

    @Override
    public void onEnable(SuperiorSkyblock superiorSkyblock) {
        System.out.println("Oui je load bien le module zMenu !");
        SuperiorSkyblockPlugin plugin = (SuperiorSkyblockPlugin) Bukkit.getPluginManager().getPlugin("SuperiorSkyblock2");
        System.out.println("Plugin : " + plugin);
        SuperiorSkyblockAPI.getProviders().setMenusProvider(this.menusProvider = new ZMenusProvider(plugin));
    }

    @Override
    public void onReload(SuperiorSkyblock superiorSkyblock) {
        System.out.println("Oui je reload bien le module zMenu !");
    }

    @Override
    public void onDisable(SuperiorSkyblock superiorSkyblock) {
        System.out.println("Oui je disable bien le module zMenu !");
    }

    @Override
    public Listener[] getModuleListeners(SuperiorSkyblock superiorSkyblock) {
        return new Listener[0];
    }

    @Override
    public SuperiorCommand[] getSuperiorCommands(SuperiorSkyblock superiorSkyblock) {
        return new SuperiorCommand[0];
    }

    @Override
    public SuperiorCommand[] getSuperiorAdminCommands(SuperiorSkyblock superiorSkyblock) {
        return new SuperiorCommand[0];
    }
}
