package fr.maxlego08.superiorskyblock.buttons.warps;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.warps.WarpCategory;
import com.bgsoftware.superiorskyblock.core.messages.Message;
import com.bgsoftware.superiorskyblock.player.chat.PlayerChat;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.superiorskyblock.buttons.SuperiorButton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class WarpCategoryManageRenameButton extends SuperiorButton {

    public WarpCategoryManageRenameButton(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public void onInventoryOpen(Player player, InventoryEngine inventory, Placeholders placeholders) {
        super.onInventoryOpen(player, inventory, placeholders);

        WarpCategory category = getCache(player).getWarpCategory();
        if (category != null) {
            placeholders.register("name", category.getName() == null ? "" : category.getName());
        }
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot,
            Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        WarpCategory category = getCache(player).getWarpCategory();
        if (category == null)
            return;

        Message.WARP_CATEGORY_RENAME.send(player);
        player.closeInventory();

        PlayerChat.listen(player, message -> {
            if (!message.equalsIgnoreCase("-cancel")) {
                category.setName(message);
            }

            PlayerChat.remove(player);
            Bukkit.getScheduler().runTask(plugin, () -> menuManager.openInventory(player, "warp-category-manage",
                    cache -> cache.setWarpCategory(category)));
            return true;
        });
    }
}
