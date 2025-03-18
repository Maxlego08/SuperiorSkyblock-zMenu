package fr.maxlego08.superiorskybloc;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandKickEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandQuitEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandUncoopPlayerEvent;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import fr.maxlego08.superiorskybloc.buttons.BannedPlayersButton;
import fr.maxlego08.superiorskybloc.buttons.CoopsButton;
import fr.maxlego08.superiorskybloc.buttons.CountsButton;
import fr.maxlego08.superiorskybloc.buttons.IslandChestButton;
import fr.maxlego08.superiorskybloc.buttons.RatingsButton;
import fr.maxlego08.superiorskybloc.buttons.TargetShowButton;
import fr.maxlego08.superiorskybloc.buttons.UniqueVisitorsButton;
import fr.maxlego08.superiorskybloc.buttons.VisitorsButton;
import fr.maxlego08.superiorskybloc.buttons.bank.BankLogsButton;
import fr.maxlego08.superiorskybloc.buttons.confirm.ButtonConfirmBan;
import fr.maxlego08.superiorskybloc.buttons.confirm.ButtonConfirmDisband;
import fr.maxlego08.superiorskybloc.buttons.confirm.ButtonConfirmKick;
import fr.maxlego08.superiorskybloc.buttons.confirm.ButtonConfirmLeave;
import fr.maxlego08.superiorskybloc.buttons.members.IslandMemberBanButton;
import fr.maxlego08.superiorskybloc.buttons.members.IslandMemberInfoButton;
import fr.maxlego08.superiorskybloc.buttons.members.IslandMemberKickButton;
import fr.maxlego08.superiorskybloc.buttons.members.IslandMembersButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpIconConfirmButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpIconDisplayButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpIconLoreButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpIconNameButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpIconTypeButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpManageIconButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpManageLocationButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpManagePrivacyButton;
import fr.maxlego08.superiorskybloc.buttons.warps.WarpManageRenameButton;
import fr.maxlego08.superiorskybloc.loader.BankActionLoader;
import fr.maxlego08.superiorskybloc.loader.BankLogsSortLoader;
import fr.maxlego08.superiorskybloc.loader.BlockValueLoader;
import fr.maxlego08.superiorskybloc.loader.BorderColorLoader;
import fr.maxlego08.superiorskybloc.loader.BorderToggleLoader;
import fr.maxlego08.superiorskybloc.loader.GlobalWarpsLoader;
import fr.maxlego08.superiorskybloc.loader.IslandBiomeLoader;
import fr.maxlego08.superiorskybloc.loader.IslandCreationLoader;
import fr.maxlego08.superiorskybloc.loader.IslandMemberRoleLoader;
import fr.maxlego08.superiorskybloc.loader.IslandPermissionLoader;
import fr.maxlego08.superiorskybloc.loader.IslandSettingsLoader;
import fr.maxlego08.superiorskybloc.loader.IslandTopLoader;
import fr.maxlego08.superiorskybloc.loader.IslandTopSortLoader;
import fr.maxlego08.superiorskybloc.loader.PlayerLanguageLoader;
import fr.maxlego08.superiorskybloc.loader.RateLoader;
import fr.maxlego08.superiorskybloc.loader.UpgradeLoader;
import fr.maxlego08.superiorskybloc.loader.WarpsLoader;
import fr.maxlego08.menu.api.ButtonManager;
import fr.maxlego08.menu.api.Inventory;
import fr.maxlego08.menu.api.InventoryManager;
import fr.maxlego08.menu.button.loader.NoneLoader;
import fr.maxlego08.menu.exceptions.InventoryException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ZMenuManager implements Listener {

    private final SuperiorSkyblockPlugin plugin;
    public static InventoryManager inventoryManager;
    private final ButtonManager buttonManager;
    private final Map<Player, PlayerCache> caches = new HashMap<>();
    private final EnumMap<MenuType, Inventory> inventories = new EnumMap<>(MenuType.class);

    public ZMenuManager(SuperiorSkyblockPlugin plugin) {
        this.plugin = plugin;
        inventoryManager = getProvider(InventoryManager.class);
        this.buttonManager = getProvider(ButtonManager.class);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private <T> T getProvider(Class<T> classz) {
        RegisteredServiceProvider<T> provider = Bukkit.getServer().getServicesManager().getRegistration(classz);
        return provider == null ? null : provider.getProvider() != null ? provider.getProvider() : null;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.caches.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(IslandKickEvent event) {
        clearCache(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(IslandUncoopPlayerEvent event) {
        clearCache(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(IslandQuitEvent event) {
        clearCache(event.getPlayer());
    }

    @EventHandler
    public void onIslandDelete(IslandDisbandEvent event) {
        for (SuperiorPlayer islandMember : event.getIsland().getIslandMembers(true)) {
            clearCache(islandMember);
        }
    }

    private void clearCache(SuperiorPlayer superiorPlayer) {
        if (superiorPlayer.isOnline()) {
            Player player = superiorPlayer.asPlayer();
            this.inventoryManager.getCurrentPlayerInventory(player).ifPresent(i -> player.closeInventory());
            this.caches.remove(player);
        }
    }

    public PlayerCache getCache(Player player) {
        return this.caches.computeIfAbsent(player, PlayerCache::new);
    }

    public PlayerCache getCacheOrNull(Player player) {
        return this.caches.get(player);
    }

    public void registerButtons() {
        this.buttonManager.unregisters(this.plugin);

        this.buttonManager.register(new IslandCreationLoader(this.plugin));
        this.buttonManager.register(new IslandSettingsLoader(this.plugin));
        this.buttonManager.register(new IslandBiomeLoader(this.plugin));
        this.buttonManager.register(new IslandMemberRoleLoader(this.plugin));
        this.buttonManager.register(new IslandPermissionLoader(this.plugin));
        this.buttonManager.register(new IslandTopLoader(this.plugin));
        this.buttonManager.register(new IslandTopSortLoader(this.plugin));
        this.buttonManager.register(new BorderColorLoader(this.plugin));
        this.buttonManager.register(new BorderToggleLoader(this.plugin));
        this.buttonManager.register(new PlayerLanguageLoader(this.plugin));
        this.buttonManager.register(new BlockValueLoader(this.plugin));
        this.buttonManager.register(new BankLogsSortLoader(this.plugin));
        this.buttonManager.register(new GlobalWarpsLoader(this.plugin));
        this.buttonManager.register(new BankActionLoader(this.plugin));
        this.buttonManager.register(new RateLoader(this.plugin));
        this.buttonManager.register(new UpgradeLoader(this.plugin));
        this.buttonManager.register(new WarpsLoader(this.plugin));

        this.buttonManager.register(new NoneLoader(this.plugin, IslandMembersButton.class, "SUPERIORSKYBLOCK_MEMBERS"));
        this.buttonManager.register(new NoneLoader(this.plugin, IslandMemberInfoButton.class, "SUPERIORSKYBLOCK_MEMBER_INFO"));
        this.buttonManager.register(new NoneLoader(this.plugin, IslandMemberBanButton.class, "SUPERIORSKYBLOCK_MEMBER_BAN"));
        this.buttonManager.register(new NoneLoader(this.plugin, IslandMemberKickButton.class, "SUPERIORSKYBLOCK_MEMBER_KICK"));
        this.buttonManager.register(new NoneLoader(this.plugin, ButtonConfirmBan.class, "SUPERIORSKYBLOCK_CONFIRM_BAN"));
        this.buttonManager.register(new NoneLoader(this.plugin, ButtonConfirmDisband.class, "SUPERIORSKYBLOCK_CONFIRM_DISBAND"));
        this.buttonManager.register(new NoneLoader(this.plugin, ButtonConfirmKick.class, "SUPERIORSKYBLOCK_CONFIRM_KICK"));
        this.buttonManager.register(new NoneLoader(this.plugin, ButtonConfirmLeave.class, "SUPERIORSKYBLOCK_CONFIRM_LEAVE"));
        this.buttonManager.register(new NoneLoader(this.plugin, TargetShowButton.class, "SUPERIORSKYBLOCK_TARGET_SHOW"));
        this.buttonManager.register(new NoneLoader(this.plugin, BankLogsButton.class, "SUPERIORSKYBLOCK_BANK_LOGS"));
        this.buttonManager.register(new NoneLoader(this.plugin, BannedPlayersButton.class, "SUPERIORSKYBLOCK_BANNED_PLAYERS"));
        this.buttonManager.register(new NoneLoader(this.plugin, CoopsButton.class, "SUPERIORSKYBLOCK_COOPS"));
        this.buttonManager.register(new NoneLoader(this.plugin, CountsButton.class, "SUPERIORSKYBLOCK_COUNTS"));
        this.buttonManager.register(new NoneLoader(this.plugin, RatingsButton.class, "SUPERIORSKYBLOCK_RATINGS"));
        this.buttonManager.register(new NoneLoader(this.plugin, IslandChestButton.class, "SUPERIORSKYBLOCK_CHESTS"));
        this.buttonManager.register(new NoneLoader(this.plugin, UniqueVisitorsButton.class, "SUPERIORSKYBLOCK_UNIQUE_VISITORS"));
        this.buttonManager.register(new NoneLoader(this.plugin, VisitorsButton.class, "SUPERIORSKYBLOCK_VISITORS"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpManagePrivacyButton.class, "SUPERIORSKYBLOCK_WARP_MANAGE_PRIVACY"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpManageLocationButton.class, "SUPERIORSKYBLOCK_WARP_MANAGE_LOCATION"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpManageRenameButton.class, "SUPERIORSKYBLOCK_WARP_MANAGE_RENAME"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpManageIconButton.class, "SUPERIORSKYBLOCK_WARP_MANAGE_ICON"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpIconConfirmButton.class, "SUPERIORSKYBLOCK_WARP_ICON_CONFIRM"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpIconNameButton.class, "SUPERIORSKYBLOCK_WARP_ICON_NAME"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpIconLoreButton.class, "SUPERIORSKYBLOCK_WARP_ICON_LORE"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpIconTypeButton.class, "SUPERIORSKYBLOCK_WARP_ICON_TYPE"));
        this.buttonManager.register(new NoneLoader(this.plugin, WarpIconDisplayButton.class, "SUPERIORSKYBLOCK_WARP_ICON_DISPLAY"));
    }

    public void loadInventories() {

        File folder = new File(plugin.getDataFolder(), "inventories");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Save inventories files
        List<String> inventories = Arrays.asList("island-creation", "settings", "biomes", "members", "member-manage", "member-role", "permissions", "control-panel", "top-islands", "border-color", "confirm-ban", "confirm-disband", "confirm-kick", "confirm-leave", "warps", "player-language", "values", "bank-logs", "banned-players", "coops", "counts", "visitors", "upgrades", "warp-manage", "warp-icon-edit", "global-warps", "island-bank", "island-ratings", "island-rate", "island-chests", "unique-visitors");

        inventories.forEach(inventoryName -> {
            if (!new File(plugin.getDataFolder(), "inventories/" + inventoryName + ".yml").exists()) {
                this.plugin.saveResource("inventories/" + inventoryName + ".yml", false);
            }
        });

        this.inventoryManager.deleteInventories(this.plugin);

        this.files(folder, file -> {
            try {
                Inventory inventory = this.inventoryManager.loadInventory(this.plugin, file);
                MenuType menuType = MenuType.valueOf(
                        inventory.getFileName().toUpperCase(Locale.ENGLISH).replace("-", "_"));
                ZMenuManager.this.inventories.put(menuType, inventory);
            } catch (InventoryException exception) {
                exception.printStackTrace();
            }
        });
    }

    private void files(File folder, Consumer<File> consumer) {
        try (Stream<Path> s = Files.walk(Paths.get(folder.getPath()))) {
            s.skip(1).map(Path::toFile).filter(File::isFile).filter(e -> e.getName().endsWith(".yml")).forEach(consumer);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void cache(SuperiorPlayer superiorPlayer, Consumer<PlayerCache> consumer) {
        this.cache(superiorPlayer.asPlayer(), consumer);
    }

    public void cache(Player player, Consumer<PlayerCache> consumer) {
        consumer.accept(getCache(player));
    }

    public void openInventory(SuperiorPlayer superiorPlayer, String inventoryName, Consumer<PlayerCache> consumer) {
        this.openInventory(superiorPlayer.asPlayer(), inventoryName, consumer);
    }

    public void openInventory(Player player, String inventoryName, Consumer<PlayerCache> consumer) {
        this.cache(player, consumer);
        this.openInventory(player, inventoryName);
    }

    public void openInventory(SuperiorPlayer superiorPlayer, String inventoryName) {
        this.openInventory(superiorPlayer.asPlayer(), inventoryName);
    }

    public void openInventory(Player player, String inventoryName) {
        List<Inventory> inventories = new ArrayList<>();
        this.inventoryManager.getCurrentPlayerInventory(player).ifPresent(inventories::add);
        Optional<Inventory> optional = this.inventoryManager.getInventory(plugin, inventoryName);
        if (optional.isPresent()) {
            this.inventoryManager.openInventory(player, optional.get(), 1, inventories);
        } else {
            player.sendMessage(ChatColor.RED + "Impossible to find the inventory " + inventoryName + " !");
        }
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public Inventory getInventory(MenuType menuType) {
        return this.inventories.get(menuType);
    }

}
