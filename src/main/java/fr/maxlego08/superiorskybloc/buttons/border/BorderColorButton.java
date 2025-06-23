package fr.maxlego08.superiorskybloc.buttons.border;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.enums.BorderColor;
import com.bgsoftware.superiorskyblock.island.IslandUtils;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.superiorskybloc.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BorderColorButton extends SuperiorButton {

    private final BorderColor borderColor;

    public BorderColorButton(SuperiorSkyblockPlugin plugin, BorderColor borderColor) {
        super(plugin);
        this.borderColor = borderColor;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        if (IslandUtils.handleBorderColorUpdate(getSuperiorPlayer(player), this.borderColor)) {
            super.onClick(player, event, inventory, slot, placeholders);
        }
    }
}
