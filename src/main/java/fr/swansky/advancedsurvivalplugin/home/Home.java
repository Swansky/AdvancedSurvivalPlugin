package fr.swansky.advancedsurvivalplugin.home;

import org.bukkit.Location;
import org.bukkit.entity.Player;


import java.util.UUID;

public class Home {
    private final String homeName;
    private final Location location;
    private final UUID playerUUID;

    public Home(String homeName, Location location, Player player) {
        this.homeName = homeName;
        this.location = location;
        this.playerUUID = player.getUniqueId();
    }

    public Home(String homeName, Location location, UUID playerUUID) {
        this.homeName = homeName;
        this.location = location;
        this.playerUUID = playerUUID;
    }

    public String getHomeName() {
        return homeName;
    }

    public Location getLocation() {
        return location;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }
}
