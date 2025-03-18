package fr.maxlego08.superiorskybloc.buttons.members;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.PlayerRole;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.inventory.inventories.InventoryDefault;
import fr.maxlego08.superiorskybloc.PlayerCache;
import fr.maxlego08.superiorskybloc.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IslandMemberRoleButton extends SuperiorButton {

    private final PlayerRole playerRole;

    public IslandMemberRoleButton(SuperiorSkyblockPlugin plugin, PlayerRole playerRole) {
        super(plugin);
        this.playerRole = playerRole;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryDefault inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        PlayerCache playerCache = getCache(player);
        SuperiorPlayer targetPlayer = playerCache.getTargetPlayer();

        if (playerRole.isLastRole()) {
            plugin.getCommands().dispatchSubCommand(player, "transfer", targetPlayer.getName());
        } else {
            plugin.getCommands().dispatchSubCommand(player, "setrole", targetPlayer.getName() + " " + playerRole);
        }
    }
}
