package fr.swansky.advancedsurvivalplugin.utilsGameplay;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BackManager {
    private final Map<UUID, Location> oldLocationMap = new HashMap<>();


    public Optional<Location> getOldLocation(UUID playerUUID) {
        return Optional.ofNullable(oldLocationMap.get(playerUUID));
    }

    public void addOldLocation(UUID playerUUID, Location location) {
        oldLocationMap.put(playerUUID, location);
    }

}
