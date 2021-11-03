package fr.swansky.advancedsurvivalplugin.market;

import fr.swansky.advancedsurvivalplugin.data.Manager;
import fr.swansky.advancedsurvivalplugin.market.models.CustomMarketVillager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CustomMarketVillagerManager implements Manager<CustomMarketVillager> {
    private final Map<UUID, CustomMarketVillager> customMarketVillagerMap = new HashMap<>();
    private final CustomMarketVillagerYML customMarketVillagerYML;

    public CustomMarketVillagerManager(MarketManager marketManager) {
        customMarketVillagerYML = new CustomMarketVillagerYML(marketManager);
        load();
    }

    @Override
    public void save() {
        customMarketVillagerYML.clean();
        for (CustomMarketVillager value : customMarketVillagerMap.values()) {
            customMarketVillagerYML.write(value);
        }
        customMarketVillagerYML.save();
    }

    @Override
    public void add(CustomMarketVillager customMarketVillager) throws Exception {
        customMarketVillagerMap.put(customMarketVillager.getVillagerUUID(), customMarketVillager);
        save();
    }

    @Override
    public void delete(CustomMarketVillager customMarketVillager) {
        customMarketVillagerMap.remove(customMarketVillager.getVillagerUUID());
        customMarketVillager.delete();
        save();
    }

    @Override
    public void load() {
        for (CustomMarketVillager customMarketVillager : customMarketVillagerYML.read()) {
            customMarketVillagerMap.put(customMarketVillager.getVillagerUUID(), customMarketVillager);
        }
    }

    public Optional<CustomMarketVillager> findCustomVillager(UUID uuidVillager) {
        return Optional.ofNullable(customMarketVillagerMap.get(uuidVillager));
    }

    public Map<UUID, CustomMarketVillager> getCustomMarketVillagerMap() {
        return customMarketVillagerMap;
    }
}
