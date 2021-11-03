package fr.swansky.advancedsurvivalplugin.market.listeners;

import fr.swansky.advancedsurvivalplugin.market.CustomMarketVillagerManager;
import fr.swansky.advancedsurvivalplugin.market.models.CustomMarketVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Optional;

public class CustomMarketVillagerClickEvent implements Listener {
    private final CustomMarketVillagerManager customMarketVillagerManager;

    public CustomMarketVillagerClickEvent(CustomMarketVillagerManager customMarketVillagerManager) {
        this.customMarketVillagerManager = customMarketVillagerManager;
    }

    @EventHandler
    public void onClickOnMarketVillager(PlayerInteractEntityEvent interactEvent) {
        Entity entity = interactEvent.getRightClicked();
        if (entity instanceof Villager) {
            Optional<CustomMarketVillager> customVillager = customMarketVillagerManager.findCustomVillager(entity.getUniqueId());
            if (customVillager.isPresent()) {
                customVillager.get().openMarket(interactEvent.getPlayer());
                interactEvent.setCancelled(true);
            }
        }
    }
}
