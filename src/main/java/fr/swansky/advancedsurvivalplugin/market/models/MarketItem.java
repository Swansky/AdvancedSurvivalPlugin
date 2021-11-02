package fr.swansky.advancedsurvivalplugin.market.models;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class MarketItem implements Clickable {
    private final String identificationName;
    private final String displayName;
    private final ItemStack icon;
    private final ItemStack itemForMarket;
    private final Double sellPrice;
    private final Double purchasePrice;


    public MarketItem(String identificationName, String displayName, ItemStack itemForMarket, Double sellPrice, Double purchasePrice) {
        this.identificationName = identificationName;
        this.displayName = displayName;
        this.itemForMarket = itemForMarket;
        this.icon = itemForMarket.clone();
        this.sellPrice = sellPrice;
        this.purchasePrice = purchasePrice;
    }

    public MarketItem(String identificationName, String displayName, ItemStack icon, ItemStack itemForMarket, Double sellPrice, Double purchasePrice) {
        this.identificationName = identificationName;
        this.displayName = displayName;
        this.icon = icon;
        this.itemForMarket = itemForMarket;
        this.sellPrice = sellPrice;
        this.purchasePrice = purchasePrice;
    }

    public MarketItem(String identificationName, ItemStack itemForMarket, Double sellPrice, Double purchasePrice) {
        this.identificationName = identificationName;
        this.itemForMarket = itemForMarket;
        this.displayName = itemForMarket.displayName().examinableName();
        this.icon = itemForMarket.clone();
        this.sellPrice = sellPrice;
        this.purchasePrice = purchasePrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public ItemStack getItemForMarket() {
        return itemForMarket;
    }

    public String getIdentificationName() {
        return identificationName;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public void click(Player player, ClickType clickType) {
        if (clickType.isLeftClick()) {

        } else if (clickType.isRightClick()) {

        }
    }

}
