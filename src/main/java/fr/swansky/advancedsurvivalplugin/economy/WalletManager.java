package fr.swansky.advancedsurvivalplugin.economy;

import fr.swansky.advancedsurvivalplugin.data.Manager;
import fr.swansky.advancedsurvivalplugin.economy.exceptions.WalletCreationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WalletManager implements Manager<Wallet> {
    private final WalletYML walletYML;
    private final Map<UUID, Wallet> walletMap = new HashMap<>();

    public WalletManager() {
        this.walletYML = new WalletYML();
        load();
    }

    public Wallet findWalletByPlayerUUID(UUID playerUUID) {
        if (this.walletMap.get(playerUUID) != null) {
            return this.walletMap.get(playerUUID);
        }
        Wallet wallet = new Wallet(playerUUID);
        try {
            add(wallet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wallet;
    }

    @Override
    public void save() {
        for (Wallet value : walletMap.values()) {
            walletYML.write(value);
        }
        walletYML.save();
    }

    @Override
    public void add(Wallet wallet) throws Exception {
        if (this.walletMap.containsKey(wallet.getPlayerUUID())) {
            throw new WalletCreationException("wallet Already exist");
        }
        this.walletMap.put(wallet.getPlayerUUID(), wallet);
    }

    @Override
    public void delete(Wallet wallet) {
    }

    @Override
    public void load() {
        List<Wallet> read = walletYML.read();
        for (Wallet wallet : read) {
            walletMap.put(wallet.getPlayerUUID(), wallet);
        }
    }

    public Map<UUID, Wallet> getWalletMap() {
        return walletMap;
    }
}
