package fr.swansky.advancedsurvivalplugin.market.listeners;

import fr.swansky.advancedsurvivalplugin.market.models.Clickable;
import fr.swansky.advancedsurvivalplugin.market.models.CustomInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof CustomInventory) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                ItemStack itemStack = event.getCurrentItem();
                if (event.getWhoClicked() instanceof Player) {
                    Player player = (Player) event.getWhoClicked();
                    if (itemStack.getType() != Material.AIR) {
                        CustomInventory customHolder = (CustomInventory) event.getView().getTopInventory().getHolder();
                        Clickable clickable = customHolder.getClickableMap().get(event.getRawSlot());
                        if (clickable != null) {
                            clickable.click(player, event.getClick());
                        }
                    }
                }

                if (itemStack.getItemMeta() != null) {
                    if (itemStack.getItemMeta().hasLocalizedName()) {
                        if (itemStack.getItemMeta().getLocalizedName().equals("NOT_MOVABLE_ITEM")) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
