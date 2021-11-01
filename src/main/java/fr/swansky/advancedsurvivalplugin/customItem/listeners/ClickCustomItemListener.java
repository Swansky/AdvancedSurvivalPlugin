package fr.swansky.advancedsurvivalplugin.customItem.listeners;

import fr.swansky.advancedsurvivalplugin.AdvancedSurvivalPlugin;
import fr.swansky.advancedsurvivalplugin.customItem.CustomItemManager;
import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    private void onClickItem(PlayerInteractEvent event) {
        //TODO add code for click custom item listener
        if (event.getItem() != null) {

            ItemStack item = event.getItem();
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta.getPersistentDataContainer().has(AdvancedSurvivalPlugin.NAMESPACE_KEY, PersistentDataType.STRING)) {
                String s = itemMeta.getPersistentDataContainer().get(AdvancedSurvivalPlugin.NAMESPACE_KEY, PersistentDataType.STRING);
                Optional<CustomItem> customItemByID = customItemManager.findCustomItemByID(s);
                if (customItemByID.isPresent()) {
                    CustomItem customItem = customItemByID.get();
                    if (event.getAction().isLeftClick()) {
                        customItem.leftClick(event);
                    } else if (event.getAction().isRightClick()) {
                        customItem.rightClick(event);
                    }
                }

            }


        }
    }
}
