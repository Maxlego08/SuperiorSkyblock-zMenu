package fr.maxlego08.superiorskyblock.utils;

import fr.maxlego08.menu.api.MenuItemStack;

import java.util.List;

public class Setting {

    private final String name;
    private final MenuItemStack itemStackEnabled;
    private final MenuItemStack itemStackDisabled;
    private final int position;
    private final List<SettingOtherButton> settingOtherButtons;

    public Setting(String name, MenuItemStack itemStackEnabled, MenuItemStack itemStackDisabled, int position, List<SettingOtherButton> settingOtherButtons) {
        this.name = name;
        this.itemStackEnabled = itemStackEnabled;
        this.itemStackDisabled = itemStackDisabled;
        this.position = position;
        this.settingOtherButtons = settingOtherButtons;
    }

    public String getName() {
        return name;
    }

    public MenuItemStack getItemStackEnabled() {
        return itemStackEnabled;
    }

    public MenuItemStack getItemStackDisabled() {
        return itemStackDisabled;
    }

    public int getPosition() {
        return position;
    }

    public List<SettingOtherButton> getSettingOtherButtons() {
        return settingOtherButtons;
    }
}
