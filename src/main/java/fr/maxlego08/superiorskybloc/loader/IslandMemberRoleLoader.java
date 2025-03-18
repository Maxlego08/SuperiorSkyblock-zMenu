package fr.maxlego08.superiorskybloc.loader;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.PlayerRole;
import com.bgsoftware.superiorskyblock.core.logging.Log;
import com.bgsoftware.superiorskyblock.island.role.SPlayerRole;
import fr.maxlego08.menu.api.button.Button;
import fr.maxlego08.menu.api.button.DefaultButtonValue;
import fr.maxlego08.superiorskybloc.buttons.IslandBiomeButton;
import fr.maxlego08.superiorskybloc.buttons.members.IslandMemberRoleButton;
import org.bukkit.configuration.file.YamlConfiguration;

public class IslandMemberRoleLoader extends SuperiorButtonLoader {
    public IslandMemberRoleLoader(SuperiorSkyblockPlugin plugin) {
        super(plugin, "MEMBER_ROLE");
    }

    @Override
    public Class<? extends Button> getButton() {
        return IslandBiomeButton.class;
    }

    @Override
    public Button load(YamlConfiguration configuration, String path, DefaultButtonValue defaultButtonValue) {

        Object roleObject = configuration.get(path + "role");

        PlayerRole playerRole = null;

        if (roleObject instanceof String) {
            try {
                playerRole = SPlayerRole.of((String) roleObject);
            } catch (IllegalArgumentException error) {
                Log.warnFromFile("member-role.yml", "Invalid role name: ", roleObject);
            }
        } else if (roleObject instanceof Integer) {
            playerRole = SPlayerRole.of((Integer) roleObject);
            if (playerRole == null) {
                Log.warnFromFile("member-role.yml", "&cInvalid role id: ", roleObject);
            }
        }

        return new IslandMemberRoleButton(plugin, playerRole);

    }
}
