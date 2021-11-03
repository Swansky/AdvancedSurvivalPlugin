package fr.swansky.advancedsurvivalplugin.home;

import fr.swansky.advancedsurvivalplugin.data.Yml;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeYML extends Yml<Home> {
    private static final String FILE_NAME = "homes";

    public HomeYML() {
        super(FILE_NAME);
    }

    @Override
    public List<Home> read() {
        final List<Home> homes = new ArrayList<>();
        final ConfigurationSection homesFileSection = config.getConfigurationSection("homes");
        if(homesFileSection == null) return homes;
        UUID playerUUID;
        ConfigurationSection homesSection;
        ConfigurationSection homeSection;
        Home home;
        Location location;
        for (String key : homesFileSection.getKeys(false)) {
            homesSection = homesFileSection.getConfigurationSection(key);
            playerUUID = UUID.fromString(key);
            for (String homeSectionKey : homesSection.getKeys(false)) {
                homeSection = homesSection.getConfigurationSection(homeSectionKey);
                location = homeSection.getLocation("location");
                home = new Home(homeSectionKey, location, playerUUID);
                homes.add(home);
            }
        }
        return homes;
    }

    @Override
    public void write(Home home) {
        ConfigurationSection homes = config.createSection("homes." + home.getPlayerUUID().toString() + "." + home.getHomeName());
        homes.set("location", home.getLocation());
        save();
    }

    @Override
    protected String getSavePath() {
        return "";
    }

}
