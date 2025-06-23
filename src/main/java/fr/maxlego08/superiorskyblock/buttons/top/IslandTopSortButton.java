package fr.maxlego08.superiorskyblock.buttons.top;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.SortingType;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.superiorskyblock.PlayerCache;
import fr.maxlego08.superiorskyblock.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IslandTopSortButton extends SuperiorButton {

    private final SortingType sortingType;

    public IslandTopSortButton(SuperiorSkyblockPlugin plugin, SortingType sortingType) {
        super(plugin);
        this.sortingType = sortingType;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);
        PlayerCache playerCache = getCache(player);
        if (playerCache.getSortingType() != this.sortingType) {
            menuManager.openInventory(player, "top-islands", cache -> cache.setSortingType(this.sortingType));
        }
    }
}
