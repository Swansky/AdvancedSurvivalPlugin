package fr.swansky.advancedsurvivalplugin.economy;

import fr.swansky.advancedsurvivalplugin.data.Yml;
import org.bukkit.configuration.ConfigurationSection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WalletYML extends Yml<Wallet> {
    private static final String FILE_NAME = "wallets";

    public WalletYML() {
        super(FILE_NAME);
    }

    @Override
    public List<Wallet> read() {
        final List<Wallet> wallets = new ArrayList<>();
        ConfigurationSection walletsSection = config.getConfigurationSection("wallets");
        if(walletsSection == null) return wallets;
        UUID playerUUID;
        BigDecimal balance;
        Wallet wallet;
        ConfigurationSection walletSection;
        for (String key : walletsSection.getKeys(false)) {
            walletSection = walletsSection.getConfigurationSection(key);
            playerUUID = UUID.fromString(key);
            balance = new BigDecimal(walletSection.getString("balance"));
            wallet = new Wallet(playerUUID, balance);
            wallets.add(wallet);
        }
        return wallets;
    }

    @Override
    public void write(Wallet wallet) {
        ConfigurationSection wallets = config.createSection("wallets." + wallet.getPlayerUUID().toString());
        wallets.set("balance", wallet.getBalance().toString());
    }

    @Override
    protected String getSavePath() {
        return "";
    }
}
