package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.enums.Rating;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskyblock.buttons.RateButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class RateLoader extends SuperiorButtonLoader {

    public RateLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "RATE");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        Rating rating = Rating.valueOf(configuration.getString(path + "rate", Rating.UNKNOWN.name()).toUpperCase());
        return new RateButton(this.plugin, rating);
    }
}
