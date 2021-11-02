package fr.swansky.advancedsurvivalplugin.economy.commands;

import fr.swansky.advancedsurvivalplugin.economy.Wallet;
import fr.swansky.advancedsurvivalplugin.economy.WalletManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import static fr.swansky.advancedsurvivalplugin.Constants.MONEY_UNITY;

public class MoneyCommand implements CommandExecutor {
    private final WalletManager walletManager;

    public MoneyCommand(WalletManager walletManager) {
        this.walletManager = walletManager;
    }

    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player ohterPlayer = Bukkit.getPlayer(args[0]);
                    Wallet otherPlayerWallet = walletManager.findWalletByPlayerUUID(ohterPlayer.getUniqueId());
                    BigDecimal balance = otherPlayerWallet.getBalance();
                    player.sendMessage(ChatColor.GRAY + "Il a : " + ChatColor.RED + balance.toString() + MONEY_UNITY);
                } else {
                    player.sendMessage(ChatColor.GRAY + "Ce joueur n'existe pas ou n'est pas connecté");
                }
            } else {
                Wallet walletByPlayerUUID = this.walletManager.findWalletByPlayerUUID(player.getUniqueId());
                BigDecimal balance = walletByPlayerUUID.getBalance();
                player.sendMessage(ChatColor.GRAY + "Vous avez: " + ChatColor.RED + balance.toString() + " " + ChatColor.BOLD + MONEY_UNITY);
            }
        }
        return true;
    }
}
