package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.key.Key;
import com.bgsoftware.superiorskyblock.core.key.Keys;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.BlockValueButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class BlockValueLoader extends SuperiorButtonLoader {

    public BlockValueLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "BLOCK_VALUE");
    }

    @Override
    public Class<? extends Button> getButton() {
        return BlockValueButton.class;
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {
        String block = configuration.getString(path + "block", "STONE");
        Key blockKey = Keys.ofMaterialAndData(block);

        return new BlockValueButton(plugin, blockKey);
    }
}
