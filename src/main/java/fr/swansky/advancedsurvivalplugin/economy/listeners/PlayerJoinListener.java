package fr.swansky.advancedsurvivalplugin.economy.listeners;

import fr.swansky.advancedsurvivalplugin.economy.WalletManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.swansky.advancedsurvivalplugin.Constants.MONEY_UNITY;

public class PlayerJoinListener implements Listener {
    private final WalletManager walletManager;

    public PlayerJoinListener(WalletManager walletManager) {
        this.walletManager = walletManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        String balance = walletManager.findWalletByPlayerUUID(player.getUniqueId()).getBalance().toString();
        player.sendMessage(ChatColor.GRAY + "Vous avez : " + ChatColor.RED + balance +
                ChatColor.GOLD + ChatColor.BOLD + MONEY_UNITY);
    }

}
