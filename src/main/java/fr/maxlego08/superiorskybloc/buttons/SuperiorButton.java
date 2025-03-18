package fr.maxlego08.superiorskybloc.buttons;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import fr.maxlego08.menu.button.ZButton;
import fr.maxlego08.superiorskybloc.PlayerCache;
import fr.maxlego08.superiorskybloc.ZMenuManager;
import fr.maxlego08.superiorskybloc.ZMenusProvider;
import org.bukkit.entity.Player;

public abstract class SuperiorButton extends ZButton {

    protected final SuperiorSkyblockPlugin plugin;
    protected final ZMenuManager menuManager;

    public SuperiorButton(SuperiorSkyblockPlugin plugin) {
        this.plugin = plugin;
        this.menuManager = ((ZMenusProvider) plugin.getProviders().getMenusProvider()).getMenuManager();
    }

    protected SuperiorPlayer getSuperiorPlayer(Player player) {
        return this.plugin.getPlayers().getSuperiorPlayer(player);
    }

    protected PlayerCache getCache(Player player) {
        return this.menuManager.getCache(player);
    }
}
