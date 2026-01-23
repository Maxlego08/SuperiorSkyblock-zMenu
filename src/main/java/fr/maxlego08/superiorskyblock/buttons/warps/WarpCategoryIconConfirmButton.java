package fr.maxlego08.superiorskyblock.buttons.warps;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.warps.WarpCategory;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.superiorskyblock.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class WarpCategoryIconConfirmButton extends SuperiorButton {

    public WarpCategoryIconConfirmButton(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot,
            Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        WarpCategory category = getCache(player).getWarpCategory();
        if (category == null)
            return;

        ItemStack itemStack = getCache(player).getEditableBuilder().build();
        category.setIcon(itemStack);

        // Return to manage menu
        this.menuManager.openInventory(player, "warp-category-manage", cache -> cache.setWarpCategory(category));
    }
}
