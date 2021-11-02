package fr.swansky.advancedsurvivalplugin.market.models;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public interface Clickable extends TableElement {
    void click(Player player, ClickType clickType);
    ItemStack getIcon();
}
