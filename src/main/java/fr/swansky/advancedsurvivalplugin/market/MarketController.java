package fr.swansky.advancedsurvivalplugin.market;

import fr.swansky.advancedsurvivalplugin.data.Controller;
import fr.swansky.advancedsurvivalplugin.market.exceptions.MarketException;
import fr.swansky.advancedsurvivalplugin.market.models.Market;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MarketController implements Controller<Market> {
    private final MarketYML marketYML;
    private final Map<String, Market> marketMap = new HashMap<>();

    public MarketController() {
        this.marketYML = new MarketYML();
        load();
    }

    public Optional<Market> getMarketByIdentificationName(String id) {
        return Optional.ofNullable(marketMap.get(id));
    }


    @Override
    public void save() {
        marketYML.clean();
        for (Market marketMap : this.marketMap.values()) {
            marketYML.write(marketMap);
        }
        marketYML.save();
    }

    @Override
    public void add(Market market) throws Exception {
        if (this.marketMap.containsKey(market.getMarketID())) {
            throw new MarketException("Market With ID '" + market.getMarketID() + "' already exist ! ");
        }
        this.marketMap.put(market.getMarketID(), market);
    }

    @Override
    public void delete(Market market) {
        this.marketMap.remove(market.getMarketID());
    }

    @Override
    public void load() {
        List<Market> read = marketYML.read();
        for (Market market : read) {
            marketMap.put(market.getMarketID(), market);
        }
    }

    public boolean existByID(String id) {
        return this.marketMap.containsKey(id);
    }

    public Market getMarketByID(String id) {
        return this.marketMap.get(id);
    }

    public Map<String, Market> getMarketMap() {
        return marketMap;
    }
}