package fr.maxlego08.superiorskybloc.buttons.warps;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.warps.IslandWarp;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.core.events.args.PluginEventArgs;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEvent;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEventsFactory;
import com.bgsoftware.superiorskyblock.core.messages.Message;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.superiorskybloc.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class WarpIconConfirmButton extends SuperiorButton {
    public WarpIconConfirmButton(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        IslandWarp islandWarp = getCache(player).getIslandWarp();
        SuperiorPlayer superiorPlayer = getSuperiorPlayer(player);

        PluginEvent<PluginEventArgs.IslandChangeWarpIcon> changeWarpIconEvent = PluginEventsFactory.callIslandChangeWarpIconEvent(islandWarp.getIsland(), superiorPlayer, islandWarp, getCache(player).getEditableBuilder().build());

        if (changeWarpIconEvent.isCancelled()) return;

        player.closeInventory();

        Message.WARP_ICON_UPDATED.send(superiorPlayer);
        islandWarp.setIcon(changeWarpIconEvent.getArgs().icon);
    }
}
