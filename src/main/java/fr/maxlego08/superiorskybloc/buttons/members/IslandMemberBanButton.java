package fr.maxlego08.superiorskybloc.buttons.members;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.island.IslandUtils;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.superiorskybloc.PlayerCache;
import fr.maxlego08.superiorskybloc.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class IslandMemberBanButton extends SuperiorButton {
    public IslandMemberBanButton(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        PlayerCache playerCache = getCache(player);
        SuperiorPlayer inventoryViewer = getSuperiorPlayer(player);
        SuperiorPlayer targetPlayer = playerCache.getTargetPlayer();

        if (plugin.getSettings().isBanConfirm()) {
            Island island = inventoryViewer.getIsland();
            if (IslandUtils.checkBanRestrictions(inventoryViewer, island, targetPlayer)) {
                plugin.getMenus().openConfirmBan(inventoryViewer, null, island, targetPlayer);
            }
        } else {
            plugin.getCommands().dispatchSubCommand(player, "ban", targetPlayer.getName());
        }
    }
}
