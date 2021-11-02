package fr.swansky.advancedsurvivalplugin.market;

import fr.swansky.advancedsurvivalplugin.data.Controller;
import fr.swansky.advancedsurvivalplugin.market.models.Market;

import java.util.*;

public class MarketManager implements Controller<Market> {
    private final MarketYML marketYML;
    private final Map<String, Market> marketMap = new HashMap<>();

    public MarketManager(MarketYML marketYML) {
        this.marketYML = marketYML;
    }

    public Optional<Market> getMarketByIdentificationName(String id) {
        //TODO add code for return market by id name
        return Optional.empty();
    }

    public List<Market> getAllMarket() {
        List<Market> markets = new ArrayList<>();
        //TODO add code for get all markets
        return markets;
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
}
