package fr.maxlego08.superiorskyblock.buttons.warps;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.core.itemstack.ItemBuilder;
import fr.maxlego08.superiorskyblock.buttons.SuperiorButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class WarpIconDisplayButton extends SuperiorButton {
    public WarpIconDisplayButton(Plugin plugin) {
        super((SuperiorSkyblockPlugin) plugin);
    }

    @Override
    public ItemStack getCustomItemStack(Player player) {
        ItemBuilder itemBuilder = getCache(player).getEditableBuilder();
        return itemBuilder != null ? itemBuilder.build() : super.getCustomItemStack(player);
    }
}
