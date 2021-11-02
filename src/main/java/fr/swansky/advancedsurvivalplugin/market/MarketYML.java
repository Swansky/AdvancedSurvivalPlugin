package fr.swansky.advancedsurvivalplugin.market;

import fr.swansky.advancedsurvivalplugin.data.Yml;
import fr.swansky.advancedsurvivalplugin.market.models.Market;
import fr.swansky.advancedsurvivalplugin.market.models.MarketItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MarketYML extends Yml<Market> {
    private static final String FILE_NAME = "markets";


    public MarketYML() {
        super(FILE_NAME);
    }

    @Override
    public List<Market> read() {
        List<Market> markets = new ArrayList<>();
        ConfigurationSection marketsSection = config.getConfigurationSection("markets");
        if (marketsSection == null)
            return markets;
        for (String marketID : marketsSection.getKeys(false)) {
            ConfigurationSection marketSection = marketsSection.getConfigurationSection(marketID);
            if(marketSection == null) return markets;
            String marketTitle = marketSection.getString("title");
            int row = marketSection.getInt("row");
            Market market = new Market(marketID, marketTitle, row);

            ConfigurationSection marketItemsSection = marketSection.getConfigurationSection("marketItems");
            for (String marketItemKey : marketItemsSection.getKeys(false)) {
                ConfigurationSection marketItemSection = marketItemsSection.getConfigurationSection(marketItemKey);
                String displayName = marketItemSection.getString("displayName");
                ItemStack icon = marketItemSection.getItemStack("icon");
                ItemStack itemForMarket = marketItemSection.getItemStack("itemForMarket");
                Double sellPrice = marketItemSection.getDouble("sellPrice");
                Double purchasePrice = marketItemSection.getDouble("purchasePrice");
                int rowPosition = marketItemSection.getInt("row");
                int columPosition = marketItemSection.getInt("column");
                boolean sellable = marketItemSection.getBoolean("sellable");
                boolean purchasable = marketItemSection.getBoolean("purchasable");
                MarketItem marketItem = new MarketItem(marketItemKey, displayName, icon, itemForMarket, sellPrice, purchasePrice, rowPosition, columPosition);
                marketItem.setPurchasable(purchasable);
                marketItem.setSellable(sellable);
                market.addClickableItem(marketItem);
            }
            markets.add(market);
        }

        return markets;
    }

    @Override
    public void write(Market market) {
        ConfigurationSection marketConfigSection = config.createSection("markets." + market.getMarketID());
        marketConfigSection.set("title", market.getInventoryName());
        marketConfigSection.set("row", market.getRow());
        ConfigurationSection marketItemsSection = marketConfigSection.createSection("marketItems");
        for (Integer value : market.getClickableMap().keySet()) {
            MarketItem marketItem = (MarketItem) market.getClickableMap().get(value);
            ConfigurationSection marketItemSection = marketItemsSection.createSection(marketItem.getIdentificationName());
            marketItemSection.set("displayName", marketItem.getDisplayName());
            marketItemSection.set("itemForMarket", marketItem.getItemForMarket());
            marketItemSection.set("icon", marketItem.getIcon());
            marketItemSection.set("sellPrice", marketItem.getSellPrice());
            marketItemSection.set("purchasePrice", marketItem.getPurchasePrice());
            marketItemSection.set("row", marketItem.getRowPosition());
            marketItemSection.set("column", marketItem.getColumnPosition());
            marketItemSection.set("sellable", marketItem.isSellable());
            marketItemSection.set("purchasable", marketItem.isPurchasable());

        }
        save();
    }

    @Override
    protected String getSavePath() {
        return "";
    }
}
