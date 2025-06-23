package fr.maxlego08.superiorskyblock.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.SortingType;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskyblock.buttons.top.IslandTopSortButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class IslandTopSortLoader extends SuperiorButtonLoader {

    public IslandTopSortLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "TOP_SORT");
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {

        String sortingTypeString = configuration.getString(path + "sorting-type", "WORTH");
        SortingType sortingType = SortingType.getByName(sortingTypeString);

        return new IslandTopSortButton(plugin, sortingType);
    }
}
