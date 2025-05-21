package fr.maxlego08.superiorskybloc.utils;

import fr.maxlego08.menu.api.MenuItemStack;

public class SettingOtherButton {

    private final MenuItemStack itemStack;
    private final int slot;

    public SettingOtherButton(MenuItemStack itemStack, int slot) {
        this.itemStack = itemStack;
        this.slot = slot;
    }

    public MenuItemStack getItemStack() {
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }
}
