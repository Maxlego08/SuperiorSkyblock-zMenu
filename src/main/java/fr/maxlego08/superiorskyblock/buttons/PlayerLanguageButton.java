package fr.maxlego08.superiorskyblock.buttons;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEventsFactory;
import com.bgsoftware.superiorskyblock.core.messages.Message;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Locale;

public class PlayerLanguageButton extends SuperiorButton {

    private final Locale locale;

    public PlayerLanguageButton(SuperiorSkyblockPlugin plugin, Locale locale) {
        super(plugin);
        this.locale = locale;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        SuperiorPlayer inventoryViewer = getSuperiorPlayer(player);

        if (!PluginEventsFactory.callPlayerChangeLanguageEvent(inventoryViewer, this.locale)) return;

        super.onClick(player, event, inventory, slot, placeholders);
        inventoryViewer.setUserLocale(this.locale);

        Message.CHANGED_LANGUAGE.send(inventoryViewer);
    }
}
