package fr.swansky.advancedsurvivalplugin.utilsGameplay.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CoordinateOnDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        Player entity = playerDeathEvent.getEntity();
        Location location = entity.getLocation();
        entity.sendMessage(ChatColor.GRAY + "Vous etes mort en : x:" +
                location.getBlockX() +
                " y:" + location.getBlockY() +
                " z:" + location.getBlockZ());
    }
}
