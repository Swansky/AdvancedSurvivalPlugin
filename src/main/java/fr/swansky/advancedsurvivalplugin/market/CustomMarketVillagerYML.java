package fr.swansky.advancedsurvivalplugin.market;

import fr.swansky.advancedsurvivalplugin.data.Yml;
import fr.swansky.advancedsurvivalplugin.market.models.CustomMarketVillager;
import fr.swansky.advancedsurvivalplugin.market.models.Market;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomMarketVillagerYML extends Yml<CustomMarketVillager> {

    private static final String FILE_NAME = "customMarketVillager";
    private final MarketManager marketManager;

    public CustomMarketVillagerYML(MarketManager marketManager) {
        super(FILE_NAME);
        this.marketManager = marketManager;

    }

    @Override
    public List<CustomMarketVillager> read() {
        List<CustomMarketVillager> customMarketVillagers = new ArrayList<>();
        ConfigurationSection villagersSection = this.config.getConfigurationSection("customVillagers");
        if (villagersSection == null) return customMarketVillagers;
        for (String villagerUUID : villagersSection.getKeys(false)) {
            ConfigurationSection villagerSection = villagersSection.getConfigurationSection(villagerUUID);
            String marketID = villagerSection.getString("marketID");
            CustomMarketVillager customMarketVillager;
            Optional<Market> marketOptional = marketManager.getMarketByIdentificationName(marketID);
            customMarketVillager = marketOptional.map(market -> new CustomMarketVillager(UUID.fromString(villagerUUID), market)).orElseGet(() -> new CustomMarketVillager(UUID.fromString(villagerUUID), null));
            customMarketVillagers.add(customMarketVillager);
        }

        return customMarketVillagers;
    }

    @Override
    public void write(CustomMarketVillager customMarketVillager) {
        ConfigurationSection villagersSection = this.config.createSection("customVillagers." + customMarketVillager.getVillagerUUID());
        villagersSection.set("marketID", customMarketVillager.getMarket().getMarketID());
    }

    @Override
    protected String getSavePath() {
        return "";
    }
}
