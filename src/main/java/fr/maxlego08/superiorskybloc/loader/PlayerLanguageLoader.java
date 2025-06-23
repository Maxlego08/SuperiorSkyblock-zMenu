package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.core.logging.Log;
import com.bgsoftware.superiorskyblock.player.PlayerLocales;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.PlayerLanguageButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerLanguageLoader extends SuperiorButtonLoader {

    public PlayerLanguageLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "LANGUAGE");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {

        String languageName = configuration.getString(path + "language", "en-US");

        java.util.Locale locale = null;

        try {
            locale = PlayerLocales.getLocale(languageName);
            if (!PlayerLocales.isValidLocale(locale)) locale = null;
        } catch (IllegalArgumentException ignored) {
        }

        if (locale == null) {
            Log.warnFromFile("player-language.yml", "The language ", languageName, " is not valid.");
        }

        return new PlayerLanguageButton(plugin, locale);
    }
}
