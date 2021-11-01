package fr.swansky.advancedsurvivalplugin.market.models;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Market implements InventoryHolder {
    private final String marketID;
    private final Inventory inventory;
    private final String marketTitle;
    private final Set<MarketItem> marketItems = new HashSet<>();

    public Market(String marketID, String marketTitle) {
        this.marketID = marketID;
        this.marketTitle = marketTitle;
        this.inventory = Bukkit.createInventory(this, 3 * 9, Component.text(this.marketTitle));
    }

    /**
     * Get the object's inventory.
     *
     * @return The inventory.
     */
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public String getMarketTitle() {
        return marketTitle;
    }

    public String getMarketID() {
        return marketID;
    }

    public Set<MarketItem> getMarketItems() {
        return marketItems;
    }

    public void addMarketItem(MarketItem marketItem) {
        this.marketItems.add(marketItem);
    }
}
