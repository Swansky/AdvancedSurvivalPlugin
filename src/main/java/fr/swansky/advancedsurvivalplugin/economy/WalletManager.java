package fr.swansky.advancedsurvivalplugin.economy;

import fr.swansky.advancedsurvivalplugin.data.Manager;
import fr.swansky.advancedsurvivalplugin.economy.exceptions.WalletCreationException;

import java.util.*;

public class WalletManager implements Manager<Wallet> {
    private final WalletYML walletYML;
    private final Map<UUID, Wallet> walletMap = new HashMap<>();

    public WalletManager(WalletYML walletYML) {
        this.walletYML = walletYML;
        load();
    }

    public Optional<Wallet> findWalletByPlayerUUID(UUID playerUUID) {
        return Optional.ofNullable(this.walletMap.get(playerUUID));
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
