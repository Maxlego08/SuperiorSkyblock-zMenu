package fr.maxlego08.superiorskybloc.buttons;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.core.events.args.PluginEventArgs;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEvent;
import com.bgsoftware.superiorskyblock.core.events.plugin.PluginEventsFactory;
import com.bgsoftware.superiorskyblock.core.messages.Message;
import fr.maxlego08.menu.api.utils.Placeholders;
import fr.maxlego08.menu.api.engine.InventoryEngine;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Locale;

public class IslandBiomeButton extends SuperiorButton {

    private final Biome biome;
    private final boolean shouldCurrentBiomeGlow;

    public IslandBiomeButton(SuperiorSkyblockPlugin plugin, Biome biome, boolean shouldCurrentBiomeGlow) {
        super(plugin);
        this.biome = biome;
        this.shouldCurrentBiomeGlow = shouldCurrentBiomeGlow;
    }

    @Override
    public ItemStack getCustomItemStack(Player player) {
        ItemStack itemStack = super.getCustomItemStack(player);

        SuperiorPlayer superiorPlayer = getSuperiorPlayer(player);
        Island island = superiorPlayer.getIsland();

        if (island == null || island.getBiome() != this.biome) return itemStack;

        if (shouldCurrentBiomeGlow) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            plugin.getNMSAlgorithms().makeItemGlow(itemMeta);
        }
        return itemStack;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event, InventoryEngine inventory, int slot, Placeholders placeholders) {
        super.onClick(player, event, inventory, slot, placeholders);

        SuperiorPlayer inventoryViewer = getSuperiorPlayer(player);
        Island island = inventoryViewer.getIsland();

        PluginEvent<PluginEventArgs.IslandBiomeChange> biomeChangeEvent = PluginEventsFactory.callIslandBiomeChangeEvent(island, inventoryViewer, this.biome);

        if (biomeChangeEvent.isCancelled()) return;

        Biome biome = biomeChangeEvent.getArgs().biome;

        island.setBiome(biome);
        Message.CHANGED_BIOME.send(inventoryViewer, biome.name().toLowerCase(Locale.ENGLISH));

        player.closeInventory();
    }
}
