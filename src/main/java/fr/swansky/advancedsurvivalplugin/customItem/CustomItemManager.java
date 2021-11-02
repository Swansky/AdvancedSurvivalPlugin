package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import fr.swansky.advancedsurvivalplugin.data.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomItemManager implements Controller<CustomItem> {
    private final Map<String, CustomItem> customItemMap = new HashMap<>();

    public CustomItemManager() {
        initCustomItems();

    }

    private void initCustomItems() {
        BedrockBreakerItem bedrockBreakerItem = new BedrockBreakerItem();
        customItemMap.put(bedrockBreakerItem.getCustomItemID(),bedrockBreakerItem);
    }

    public Optional<CustomItem> findCustomItemByID(String id) {
        return Optional.ofNullable(customItemMap.get(id));
    }


    @Override
    public void save(CustomItem customItem) {

    }

    @Override
    public void add(CustomItem customItem) {

    }

    @Override
    public void delete(CustomItem customItem) {

    }

    @Override
    public void load() {

    }
}
