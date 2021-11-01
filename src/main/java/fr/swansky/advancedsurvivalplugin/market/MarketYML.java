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
        ConfigurationSection marketsSection = config.createSection("markets");
        ConfigurationSection marketSection;
        ConfigurationSection itemsMarketSection;
        ConfigurationSection itemMarketSection;
        for (String key : marketsSection.getKeys(false)) {
            marketSection = marketsSection.getConfigurationSection(key);
            itemsMarketSection = marketSection.getConfigurationSection("marketItems");
            String marketTitle = itemsMarketSection.getString("marketTitle");
            Market market = new Market(key, marketTitle);
            for (String itemsMarketSectionKey : itemsMarketSection.getKeys(false)) {

                itemMarketSection = itemsMarketSection.getConfigurationSection(itemsMarketSectionKey);
                String displayName = itemMarketSection.getString("displayName");
                ItemStack itemForMarket = itemMarketSection.getItemStack("itemForMarket");
                ItemStack icon = itemMarketSection.getItemStack("icon");
                Double sellPrice = itemMarketSection.getDouble("sellPrice");
                Double purchasePrice = itemMarketSection.getDouble("purchasePrice");
                MarketItem marketItem = new MarketItem(itemsMarketSectionKey, displayName, icon, itemForMarket, sellPrice, purchasePrice);
                market.addMarketItem(marketItem);
            }
            markets.add(market);
        }
        return markets;
    }

    @Override
    public void write(Market market) {
        ConfigurationSection markets = config.createSection("markets." + market.getMarketID());
        markets.set("marketTitle", market.getMarketTitle());
        ConfigurationSection marketItems = markets.createSection("marketItems");
        ConfigurationSection marketItem;
        for (MarketItem item : market.getMarketItems()) {
            marketItems.set(item.getIdentificationName(), item);
            marketItem = marketItems.createSection(item.getIdentificationName());
            marketItem.set("displayName", item.getDisplayName());
            marketItem.set("icon", item.getIcon());
            marketItem.set("itemForMarket", item.getItemForMarket());
            marketItem.set("sellPrice", item.getSellPrice());
            marketItem.set("purchasePrice", item.getPurchasePrice());
        }
        save();
    }

    @Override
    protected String getSavePath() {
        return "";
    }
}
