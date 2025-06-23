package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.upgrades.Upgrade;
import com.bgsoftware.superiorskyblock.island.upgrade.SUpgradeLevel;
import fr.maxlego08.menu.api.MenuItemStack;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskyblock.buttons.UpgradeButton;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class UpgradeLoader extends SuperiorButtonLoader {
    public UpgradeLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "UPGRADE");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {

        File file = new File(this.plugin.getDataFolder(), "inventories/upgrades.yml");
        String upgradeName = configuration.getString(path + "upgrade");
        int level = configuration.getInt(path + "level", 1);

        Upgrade upgrade = plugin.getUpgrades().getUpgrade(upgradeName);
        if (upgrade == null) {
            this.plugin.getLogger().severe("Upgrade " + upgradeName + " was not found !");
            return null;
        }

        SUpgradeLevel upgradeLevel = (SUpgradeLevel) upgrade.getUpgradeLevel(level);
        if (upgradeLevel == null) {
            this.plugin.getLogger().severe("Upgrade " + upgradeName + " with level " + level + "was not found !");
            return null;
        }

        MenuItemStack errorItemStack = this.menuManager.getInventoryManager().loadItemStack(configuration, path + "error-item.", file);

        return new UpgradeButton(plugin, upgradeLevel, upgrade, errorItemStack);
    }
}
