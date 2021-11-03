package fr.swansky.advancedsurvivalplugin.economy;

import fr.swansky.advancedsurvivalplugin.economy.exceptions.WalletWithdrawException;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {
    private final UUID playerUUID;
    private BigDecimal balance;

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

    public void withdraw(Double amount) throws WalletWithdrawException {
        BigDecimal bigDecimal = new BigDecimal(amount);
        if (balance.subtract(bigDecimal).compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = balance.subtract(bigDecimal);
        } else {
            throw new WalletWithdrawException("");
        }
    }

    public void deposit(Double amount) {
        balance = balance.add(new BigDecimal(amount));
    }

    public void clear() {
        balance = new BigDecimal(0);
    }

    public void setAccount(Double amount) {
        balance = new BigDecimal(amount);
    }
}
