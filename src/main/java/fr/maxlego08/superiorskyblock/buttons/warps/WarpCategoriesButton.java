package fr.maxlego08.superiorskyblock.buttons.warps;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.warps.WarpCategory;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.core.SequentialListBuilder;
import com.bgsoftware.superiorskyblock.core.menu.MenuActions;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.menu.api.engine.ItemButton;
import fr.maxlego08.menu.api.engine.Pagination;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.superiorskyblock.PlayerCache;
import fr.maxlego08.superiorskyblock.buttons.SuperiorPaginateButton;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class WarpCategoriesButton extends SuperiorPaginateButton {

    public WarpCategoriesButton(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public boolean hasPermission() {
        return true;
    }

    @Override
    public boolean checkPermission(Player player, InventoryEngine inventory, Placeholders placeholders) {
        return getPaginationSize(player) != 0;
    }

    @Override
    public boolean hasSpecialRender() {
        return true;
    }

    @Override
    public void onRender(Player player, InventoryEngine inventory) {
        Pagination<WarpCategory> pagination = new Pagination<>();
        List<WarpCategory> categories = pagination.paginate(requestObjects(player), this.slots.size(),
                inventory.getPage());
        SuperiorPlayer superiorPlayer = getSuperiorPlayer(player);

        for (int i = 0; i != categories.size(); i++) {
            int slot = slots.get(i);
            WarpCategory category = categories.get(i);

            Placeholders placeholders = new Placeholders();
            placeholders.register("name", category.getName());

            // Get description from icon lore
            org.bukkit.inventory.ItemStack icon = category.getIcon(superiorPlayer);
            String description = "";
            if (icon != null && icon.hasItemMeta() && icon.getItemMeta().hasLore()) {
                description = String.join("\n", icon.getItemMeta().getLore());
            }
            placeholders.register("description", description);
            placeholders.register("material", icon == null ? "CHEST" : icon.getType().name());

            ItemButton button = inventory.addItem(slot, getItemStack().build(player, false, placeholders));

            button.setLeftClick(event -> {
                this.menuManager.openInventory(player, "warps", cache -> cache.setWarpCategory(category));
            });

            button.setRightClick(event -> {
                this.menuManager.openInventory(player, "warp-category-manage",
                        cache -> cache.setWarpCategory(category));
            });
        }
    }

    @Override
    public int getPaginationSize(Player player) {
        return requestObjects(player).size();
    }

    protected List<WarpCategory> requestObjects(Player player) {
        PlayerCache cache = getCache(player);
        return new SequentialListBuilder<WarpCategory>().build(cache.getIsland().getWarpCategories().values());
    }
}
