package fr.swansky.advancedsurvivalplugin.economy;

import fr.swansky.advancedsurvivalplugin.economy.exceptions.WalletWithdrawException;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {
    private final UUID playerUUID;
    private final BigDecimal balance;

    public Wallet(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.balance = new BigDecimal(0);
    }

    public Wallet(UUID playerUUID, BigDecimal balance) {
        this.playerUUID = playerUUID;
        this.balance = balance;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(Long amount) throws WalletWithdrawException {
        //TODO add logic to withdraw
    }

    public void deposit(Long amount)
    {
        //TODO add logic to deposit
    }
}
