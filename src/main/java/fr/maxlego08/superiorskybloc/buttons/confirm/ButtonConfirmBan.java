package fr.maxlego08.superiorskybloc.buttons.confirm;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.island.IslandUtils;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.superiorskybloc.PlayerCache;
import fr.maxlego08.superiorskybloc.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class ButtonConfirmBan extends SuperiorButton {

    public ButtonConfirmBan(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);
        PlayerCache playerCache = getCache(player);
        SuperiorPlayer superiorPlayer = getSuperiorPlayer(player);
        IslandUtils.handleBanPlayer(superiorPlayer, superiorPlayer.getIsland(), playerCache.getTargetPlayer());
    }
}
