package fr.swansky.advancedsurvivalplugin.market;

import fr.swansky.advancedsurvivalplugin.data.Controller;
import fr.swansky.advancedsurvivalplugin.market.models.Market;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MarketManager implements Controller<Market> {
    private final MarketYML marketYML;
    private final Map<String, Market> marketMap = new HashMap<>();

    public MarketManager(MarketYML marketYML) {
        this.marketYML = marketYML;
    }

    public Optional<Market> getMarketByIdentificationName(String id) {
        return Optional.ofNullable(marketMap.get(id));
    }


    @Override
    public void save(Market market) {
        marketYML.clean();
        for (Market marketMap : this.marketMap.values()) {
            marketYML.write(marketMap);
        }
        marketYML.save();
    }

    @Override
    public void add(Market market) {
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

    public Map<String, Market> getMarketMap() {
        return marketMap;
    }
}
