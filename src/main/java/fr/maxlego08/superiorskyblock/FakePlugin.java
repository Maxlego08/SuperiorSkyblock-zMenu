package fr.maxlego08.superiorskyblock;

import org.bukkit.plugin.java.JavaPlugin;

public class FakePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().severe("SuperiorSkyblock-zMenu is not a plugin!");
    }
}
