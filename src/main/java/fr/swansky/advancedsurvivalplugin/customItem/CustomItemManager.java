package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomItemManager {

    private final Map<String, CustomItem> customItemMap = new HashMap<>();

    public CustomItemManager() {
        initCustomItems();
    }

    private void initCustomItems() {
        BedrockBreakerItem bedrockBreakerItem = new BedrockBreakerItem();
        customItemMap.put(bedrockBreakerItem.getCustomItemID(), bedrockBreakerItem);

        GoldenLassoItem goldenLassoItem = new GoldenLassoItem();
        customItemMap.put(goldenLassoItem.getCustomItemID(), goldenLassoItem);

        DiggerItem diggerItem = new DiggerItem();
        customItemMap.put(diggerItem.getCustomItemID(), diggerItem);

    }

    public Optional<CustomItem> findCustomItemByID(String s) {
        return Optional.ofNullable(customItemMap.get(s));
    }
}
