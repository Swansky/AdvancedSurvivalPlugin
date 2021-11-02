package fr.swansky.advancedsurvivalplugin.market.models;

public class Market extends CustomInventory {
    private final String marketID;

    public Market(String marketID, String marketTitle, int row) {
        super(marketTitle, row);
        this.marketID = marketID;
    }


    public String getMarketID() {
        return marketID;
    }

}
