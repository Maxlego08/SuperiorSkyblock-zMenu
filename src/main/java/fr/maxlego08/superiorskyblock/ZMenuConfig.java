package fr.maxlego08.superiorskyblock;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.menu.MenuIslandCreationConfig;
import com.bgsoftware.superiorskyblock.api.schematic.Schematic;
import com.bgsoftware.superiorskyblock.api.world.GameSound;
import com.bgsoftware.superiorskyblock.api.wrappers.BlockOffset;
import com.bgsoftware.superiorskyblock.core.EnumHelper;
import fr.maxlego08.superiorskyblock.buttons.IslandCreationButton;
import org.bukkit.block.Biome;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

public class ZMenuConfig {

    private static final SuperiorSkyblockPlugin plugin = SuperiorSkyblockPlugin.getPlugin();

    private ZMenuConfig() {

    }

    public static class IslandCreation implements MenuIslandCreationConfig {

        private final Schematic schematic;
        private final IslandCreationButton button;
        private final Biome biome;

        public IslandCreation(IslandCreationButton button) {
            this(button.getSchematic(), button);
        }

        public IslandCreation(Schematic schematic, IslandCreationButton button) {
            this.schematic = schematic;
            this.button = button;
            this.biome = button == null ? EnumHelper.getEnum(Biome.class, plugin.getSettings().getWorlds().getDimensionConfig(plugin.getSettings().getWorlds().getDefaultWorldDimension()).getBiome()) : button.getBiome();
        }

        @Override
        public Schematic getSchematic() {
            return this.schematic;
        }

        @Override
        public boolean shouldOffsetIslandValue() {
            return this.button != null && this.button.isOffset();
        }

        @Override
        public BigDecimal getBonusWorth() {
            return this.button == null ? BigDecimal.ZERO : this.button.getBonusWorth();
        }

        @Override
        public BigDecimal getBonusLevel() {
            return this.button == null ? BigDecimal.ZERO : this.button.getBonusLevel();
        }

        @Override
        public BlockOffset getSpawnOffset() {
            return this.button == null ? null : this.button.getSpawnOffset();
        }

        @Override
        public Biome getBiome() {
            return this.biome;
        }

        @Override
        public GameSound getSound() {
            return null;
        }

        @Override
        public Collection<String> getCommands() {
            return Collections.emptyList();
        }

    }

}
