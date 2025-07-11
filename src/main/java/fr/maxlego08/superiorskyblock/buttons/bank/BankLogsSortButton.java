package fr.maxlego08.superiorskyblock.buttons.bank;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.bank.BankTransaction;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import fr.maxlego08.superiorskyblock.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Comparator;

public class BankLogsSortButton extends SuperiorButton {

    private final String sortType;

    public BankLogsSortButton(SuperiorSkyblockPlugin plugin, String sortType) {
        super(plugin);
        this.sortType = sortType;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        menuManager.openInventory(player, "bank-logs", cache -> cache.setBankSorting(sortType.equalsIgnoreCase("TIME") ? Comparator.comparingLong(BankTransaction::getTime) : (o1, o2) -> o2.getAmount().compareTo(o1.getAmount())));
    }
}
