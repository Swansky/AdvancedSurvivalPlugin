package fr.swansky.advancedsurvivalplugin.market.models;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;

public class CustomMarketVillager {
    private final Location location;
    private final Villager villager;
    private final String villagerName;
    private Market market;

    public CustomMarketVillager(Location location, String villagerName) {
        this.location = location;
        this.villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER, CreatureSpawnEvent.SpawnReason.COMMAND);
        this.villagerName = villagerName;
        initVillager();
    }

    public CustomMarketVillager(UUID uuid, Market market) {
        this.market = market;
        this.villager = (Villager) Bukkit.getEntity(uuid);
        this.villagerName = villager.getCustomName();
        this.location = villager.getLocation();
        initVillager();
    }

    private void initVillager() {
        this.villager.setInvulnerable(true);
        this.villager.setAI(false);
        this.villager.setCollidable(false);
        this.villager.setCanPickupItems(false);
        this.villager.setAgeLock(true);
        this.villager.setGravity(false);
        this.villager.setSilent(true);
        this.villager.setCustomName(this.villagerName);
        this.villager.setCustomNameVisible(true);
        this.villager.setPersistent(true);
        this.villager.setRemoveWhenFarAway(false);
    }


    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public UUID getVillagerUUID() {
        return this.villager.getUniqueId();
    }

    public void openMarket(Player player) {
        if (market != null) {
            player.openInventory(market.getInventory());
        } else {
            player.sendMessage(ChatColor.GRAY + "Ce pnj n'est pas encore disponible");
        }
    }

    public void spawnVillager() {

    }

    public void delete() {
        villager.remove();
    }
}
