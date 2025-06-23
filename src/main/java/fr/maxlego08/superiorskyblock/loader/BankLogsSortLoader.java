package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskyblock.buttons.bank.BankLogsSortButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class BankLogsSortLoader extends SuperiorButtonLoader {

    public BankLogsSortLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "BANK_LOGS_SORT");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        String sort = configuration.getString(path + "sort");
        return new BankLogsSortButton(this.plugin, sort);
    }
}
