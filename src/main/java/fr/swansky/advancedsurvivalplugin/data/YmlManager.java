package fr.swansky.advancedsurvivalplugin.data;

import fr.swansky.advancedsurvivalplugin.economy.WalletYML;
import fr.swansky.advancedsurvivalplugin.home.HomeYML;
import fr.swansky.advancedsurvivalplugin.market.MarketYML;

public class YmlManager {
    private final MarketYML marketYML;
    private final HomeYML homeYML;
    private final WalletYML walletYML;

    public YmlManager() {
        this.homeYML = new HomeYML();
        this.marketYML = new MarketYML();
        this.walletYML = new WalletYML();
    }


    public MarketYML getMarketYML() {
        return marketYML;
    }

    public HomeYML getHomeYML() {
        return homeYML;
    }

    public WalletYML getWalletYML() {
        return walletYML;
    }
}
