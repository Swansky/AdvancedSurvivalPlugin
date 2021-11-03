package fr.swansky.advancedsurvivalplugin.utilsGameplay.listeners;

import fr.swansky.advancedsurvivalplugin.utilsGameplay.BackManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {

    private final BackManager backManager;

    public TeleportListener(BackManager backManager) {
        this.backManager = backManager;
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        backManager.addOldLocation(event.getPlayer().getUniqueId(), event.getFrom());
    }
}
