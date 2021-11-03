package fr.swansky.advancedsurvivalplugin.home;

import fr.swansky.advancedsurvivalplugin.data.Manager;

import java.util.*;

public class HomeManager implements Manager<Home> {
    private final Map<UUID, List<Home>> homeMap = new HashMap<>();
    private final HomeYML homeYML;

    public HomeManager() {
        homeYML = new HomeYML();
        load();
    }

    public List<Home> getPlayerHomesByPlayerUUID(UUID uuid) {
        if (homeMap.containsKey(uuid)) {
            return homeMap.get(uuid);
        } else {
            return new ArrayList<>();
        }
    }

    public Optional<Home> getHomeByName(UUID uuid, String homeName) {
        for (Home home : getPlayerHomesByPlayerUUID(uuid)) {
            if (home.getHomeName().equalsIgnoreCase(homeName)) {
                return Optional.of(home);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save() {
        homeYML.clean();
        for (List<Home> value : homeMap.values()) {
            for (Home home : value) {
                homeYML.write(home);
            }
        }
        homeYML.save();
    }

    @Override
    public void add(Home home) throws Exception {
        if (homeMap.containsKey(home.getPlayerUUID())) {
            homeMap.get(home.getPlayerUUID()).add(home);
        } else {
            List<Home> homes = new ArrayList<>();
            homes.add(home);
            homeMap.put(home.getPlayerUUID(), homes);
        }
        homeYML.save();
    }

    @Override
    public void delete(Home home) {
        if (homeMap.containsKey(home.getPlayerUUID())) {
            homeMap.get(home.getPlayerUUID()).remove(home);
        }
        homeYML.save();
    }

    @Override
    public void load() {
        for (Home home : homeYML.read()) {
            if (homeMap.containsKey(home.getPlayerUUID())) {
                homeMap.get(home.getPlayerUUID()).add(home);
            } else {
                List<Home> homes = new ArrayList<>();
                homes.add(home);
                homeMap.put(home.getPlayerUUID(), homes);
            }
        }
    }

    public Map<UUID, List<Home>> getHomeMap() {
        return homeMap;
    }

    public boolean homeExist(UUID uniqueId, String arg) {
        List<Home> playerHomes = getPlayerHomesByPlayerUUID(uniqueId);
        for (Home playerHome : playerHomes) {
            if (playerHome.getHomeName().equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }
}
