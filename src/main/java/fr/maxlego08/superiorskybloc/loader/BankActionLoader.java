package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.bank.BankActionButton;
import org.bukkit.configuration.file.YamlConfiguration;

import java.math.BigDecimal;
import java.util.List;

public class BankActionLoader extends SuperiorButtonLoader {

    public BankActionLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "BANK_ACTION");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        BankActionButton.BankAction bankAction = BankActionButton.BankAction.valueOf(configuration.getString(path + "bank-action", BankActionButton.BankAction.DEPOSIT.name()));
        double value = configuration.getDouble(path + "percentage", 0);
        List<String> withdrawCommands = configuration.getStringList(path + "bank-action.withdraw");
        return new BankActionButton(plugin, new BigDecimal(value), bankAction, withdrawCommands);
    }
}
