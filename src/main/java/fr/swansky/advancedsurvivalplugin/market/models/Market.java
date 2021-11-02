package fr.swansky.advancedsurvivalplugin.market.models;

import org.bukkit.Material;

public class Market extends CustomInventory {
    private final String marketID;

    public Market(String marketID, String marketTitle, int row) {
        super(marketTitle, row);
        this.marketID = marketID;
        addBorder(Material.BLACK_STAINED_GLASS_PANE,1);

    }


    public String getMarketID() {
        return marketID;
    }

}
