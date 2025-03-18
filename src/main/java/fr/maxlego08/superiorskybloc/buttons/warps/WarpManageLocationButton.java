package fr.maxlego08.superiorskybloc.buttons.warps;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.warps.IslandWarp;
import com.bgsoftware.superiorskyblock.core.ChunkPosition;
import com.bgsoftware.superiorskyblock.core.events.args.PluginEventArgs;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEvent;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEventsFactory;
import com.bgsoftware.superiorskyblock.core.messages.Message;
import com.bgsoftware.superiorskyblock.island.warp.SignWarp;
import com.bgsoftware.superiorskyblock.world.chunk.ChunkLoadReason;
import com.bgsoftware.superiorskyblock.world.chunk.ChunksProvider;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.inventory.inventories.InventoryDefault;
import fr.maxlego08.superiorskybloc.buttons.SuperiorButton;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class WarpManageLocationButton extends SuperiorButton {
    public WarpManageLocationButton(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryDefault inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        IslandWarp islandWarp = getCache(player).getIslandWarp();

        Location playerLocation = player.getLocation();

        if (!islandWarp.getIsland().isInsideRange(playerLocation)) {
            Message.SET_WARP_OUTSIDE.send(player);
            return;
        }

        PluginEvent<PluginEventArgs.IslandChangeWarpLocation> changeWarpLocationEvent = PluginEventsFactory.callIslandChangeWarpLocationEvent(islandWarp.getIsland(), player, islandWarp, playerLocation);

        if (changeWarpLocationEvent.isCancelled()) return;


        Message.WARP_LOCATION_UPDATE.send(player);

        Location warpLocation = islandWarp.getLocation();

        if (!warpLocation.equals(changeWarpLocationEvent.getArgs().location)) {
            ChunksProvider.loadChunk(ChunkPosition.of(warpLocation), ChunkLoadReason.WARP_SIGN_BREAK, chunk -> {
                SignWarp.trySignWarpBreak(islandWarp, player);
            });
        }

        islandWarp.setLocation(changeWarpLocationEvent.getArgs().location);
    }
}
