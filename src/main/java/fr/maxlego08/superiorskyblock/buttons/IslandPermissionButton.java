package fr.maxlego08.superiorskyblock.buttons;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import com.bgsoftware.superiorskyblock.api.island.PlayerRole;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEventsFactory;
import com.bgsoftware.superiorskyblock.island.role.SPlayerRole;
import fr.maxlego08.menu.api.MenuItemStack;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.menu.api.engine.Pagination;
import fr.maxlego08.menu.api.utils.MetaUpdater;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.superiorskyblock.utils.Permission;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IslandPermissionButton extends SuperiorPaginateButton {

    private final String noRolePermission;
    private final String exactRolePermission;
    private final String higherRolePermission;
    private final List<Permission> permissions;

    public IslandPermissionButton(SuperiorSkyblockPlugin plugin, String noRolePermission, String exactRolePermission, String higherRolePermission, List<Permission> permissions) {
        super(plugin);
        this.noRolePermission = noRolePermission;
        this.exactRolePermission = exactRolePermission;
        this.higherRolePermission = higherRolePermission;
        this.permissions = permissions;
    }

    @Override
    public boolean hasSpecialRender() {
        return true;
    }

    @Override
    public void onRender(Player player, InventoryEngine inventory) {

        SuperiorPlayer superiorPlayer = getSuperiorPlayer(player);
        Island island = superiorPlayer.getIsland();

        Pagination<Permission> pagination = new Pagination<>();
        List<Permission> permissions = pagination.paginate(this.permissions, this.slots.size(), inventory.getPage());

        MetaUpdater updater = this.menuManager.getInventoryManager().getMeta();

        for (int i = 0; i != Math.min(permissions.size(), this.slots.size()); i++) {
            int slot = slots.get(i);
            Permission permission = permissions.get(i);

            PlayerRole requiredRole = permission.getIslandPrivilege() == null ? null : island.getRequiredPlayerRole(permission.getIslandPrivilege());

            Placeholders placeholders = new Placeholders();
            placeholders.register("role", requiredRole == null ? "" : requiredRole.getName());

            MenuItemStack menuItemStack = permission.getItemStackPermission();

            ItemStack itemStack = menuItemStack.build(player, false, placeholders);
            ItemMeta itemMeta = itemStack.getItemMeta();

            List<String> strings = new ArrayList<>(menuItemStack.getLore().stream().map(placeholders::parse).collect(Collectors.toList()));
            int roleWeight = requiredRole == null ? Integer.MAX_VALUE : requiredRole.getWeight();

            PlayerRole currentRole;
            for (int j = -2; (currentRole = SPlayerRole.of(j)) != null; j++) {
                if (!plugin.getSettings().isCoopMembers() && currentRole == SPlayerRole.coopRole()) continue;

                if (j < roleWeight) {
                    strings.add(this.noRolePermission.replace("%role%", String.valueOf(currentRole)));
                } else if (j == roleWeight) {
                    strings.add(this.exactRolePermission.replace("%role%", String.valueOf(currentRole)));
                } else {
                    strings.add(this.higherRolePermission.replace("%role%", String.valueOf(currentRole)));
                }
            }

            updater.updateLore(itemMeta, PlaceholderAPI.setPlaceholders(player, strings), player);
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(slot, itemStack).setClick(event -> {

                if (!(event.getWhoClicked() instanceof Player)) return;

                SuperiorPlayer clickedPlayer = getSuperiorPlayer((Player) event.getWhoClicked());

                onRoleButtonClick(island, clickedPlayer, event, permission, inventory);
            });
        }
    }

    private void onRoleButtonClick(Island island, SuperiorPlayer clickedPlayer, InventoryClickEvent clickEvent, Permission permission, InventoryEngine inventoryDefault) {
        IslandPrivilege islandPrivilege = permission.getIslandPrivilege();

        if (islandPrivilege == null) return;

        PlayerRole currentRole = island.getRequiredPlayerRole(islandPrivilege);

        if (clickedPlayer.getPlayerRole().isLessThan(currentRole)) return;

        PlayerRole newRole = null;

        if (clickEvent.getClick().isLeftClick()) {
            newRole = SPlayerRole.of(currentRole.getWeight() - 1);

            if (!plugin.getSettings().isCoopMembers() && newRole == SPlayerRole.coopRole()) {
                assert newRole != null;
                newRole = SPlayerRole.of(newRole.getWeight() - 1);
            }

            if (newRole == null) newRole = clickedPlayer.getPlayerRole();
        } else {
            if (clickedPlayer.getPlayerRole().isHigherThan(currentRole)) {
                newRole = SPlayerRole.of(currentRole.getWeight() + 1);
            }

            if (!plugin.getSettings().isCoopMembers() && newRole == SPlayerRole.coopRole()) {
                assert newRole != null;
                newRole = SPlayerRole.of(newRole.getWeight() + 1);
            }

            if (newRole == null) newRole = SPlayerRole.guestRole();
        }

        if (PluginEventsFactory.callIslandChangeRolePrivilegeEvent(island, clickedPlayer, newRole)) {
            island.setPermission(newRole, islandPrivilege);
            onRender(clickedPlayer.asPlayer(), inventoryDefault);
        }
    }

    @Override
    public int getPaginationSize(Player player) {
        return this.permissions.size();
    }

    @Override
    public boolean isPermanent() {
        return true;
    }
}
