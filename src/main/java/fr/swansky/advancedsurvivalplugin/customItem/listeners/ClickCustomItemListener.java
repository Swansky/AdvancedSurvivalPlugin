package fr.swansky.advancedsurvivalplugin.customItem.listeners;

import fr.swansky.advancedsurvivalplugin.AdvancedSurvivalPlugin;
import fr.swansky.advancedsurvivalplugin.customItem.CustomItemManager;
import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Optional;

public class ClickCustomItemListener implements Listener {
    private final CustomItemManager customItemManager;

    public ClickCustomItemListener(CustomItemManager customItemManager) {
        this.customItemManager = customItemManager;
    }

    @EventHandler
    private void playerInteractEvent(PlayerInteractEvent event) {
        transfer(event.getItem(), event, event.getAction());
    }

    @EventHandler
    private void playerInteractEntityEvent(PlayerInteractEntityEvent event) {
        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        transfer(itemInMainHand, event, Action.RIGHT_CLICK_BLOCK);
    }


    private void transfer(ItemStack item, Event event, Action action) {
        if (item != null) {
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta != null) {
                if (itemMeta.getPersistentDataContainer().has(AdvancedSurvivalPlugin.NAMESPACE_KEY, PersistentDataType.STRING)) {
                    String s = itemMeta.getPersistentDataContainer().get(AdvancedSurvivalPlugin.NAMESPACE_KEY, PersistentDataType.STRING);
                    Optional<CustomItem> customItemByID = customItemManager.findCustomItemByID(s);
                    if (customItemByID.isPresent()) {
                        CustomItem customItem = customItemByID.get();
                        if (action.isLeftClick()) {
                            customItem.leftClick(event);
                        } else if (action.isRightClick()) {
                            customItem.rightClick(event);
                        }
                    }
                }

            }
        }
    }

    @EventHandler
    private void breakBLockEvent(BlockBreakEvent event) {
        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        transfer(itemInMainHand, event, Action.LEFT_CLICK_BLOCK);
    }

}
