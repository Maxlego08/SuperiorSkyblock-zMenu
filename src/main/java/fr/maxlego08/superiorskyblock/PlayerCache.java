package fr.maxlego08.superiorskyblock;

import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.SortingType;
import com.bgsoftware.superiorskyblock.api.island.bank.BankTransaction;
import com.bgsoftware.superiorskyblock.api.island.warps.IslandWarp;
import com.bgsoftware.superiorskyblock.api.island.warps.WarpCategory;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.core.itemstack.ItemBuilder;
import com.bgsoftware.superiorskyblock.island.top.SortingTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.UUID;

public class PlayerCache {

    private final Player player;
    private String islandName;
    private SuperiorPlayer targetPlayer;
    private SortingType sortingType;
    private Comparator<BankTransaction> bankSorting;
    private UUID filteredPlayer;
    private Island island;
    private WarpCategory warpCategory;
    private IslandWarp islandWarp;
    private ItemBuilder editableBuilder = new ItemBuilder(Material.STONE);

    public PlayerCache(Player player) {
        this.player = player;
    }

    public String getIslandName() {
        return islandName;
    }

    public void setIslandName(String islandName) {
        this.islandName = islandName;
    }

    public Player getPlayer() {
        return player;
    }

    public SuperiorPlayer getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(SuperiorPlayer targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public SortingType getSortingType() {
        return this.sortingType == null ? SortingTypes.BY_WORTH : this.sortingType;
    }

    public void setSortingType(SortingType sortingType) {
        this.sortingType = sortingType;
    }

    public Comparator<BankTransaction> getBankSorting() {
        return bankSorting;
    }

    public void setBankSorting(Comparator<BankTransaction> bankSorting) {
        this.bankSorting = bankSorting;
    }

    public UUID getFilteredPlayer() {
        return filteredPlayer;
    }

    public void setFilteredPlayer(UUID filteredPlayer) {
        this.filteredPlayer = filteredPlayer;
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

    public WarpCategory getWarpCategory() {
        return warpCategory;
    }

    public void setWarpCategory(WarpCategory warpCategory) {
        this.warpCategory = warpCategory;
    }

    public IslandWarp getIslandWarp() {
        return islandWarp;
    }

    public void setIslandWarp(IslandWarp islandWarp) {
        this.islandWarp = islandWarp;
    }

    public ItemBuilder getEditableBuilder() {
        return editableBuilder;
    }

    public void setEditableBuilder(ItemBuilder editableBuilder) {
        this.editableBuilder = editableBuilder;
    }
}
