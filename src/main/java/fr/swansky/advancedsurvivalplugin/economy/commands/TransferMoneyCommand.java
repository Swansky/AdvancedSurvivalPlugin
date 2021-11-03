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

import static fr.swansky.advancedsurvivalplugin.utils.ParseUtils.IsDouble;

public class TransferMoneyCommand implements CommandExecutor {
    private final WalletManager walletManager;

    public TransferMoneyCommand(WalletManager walletManager) {
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
                    Player playerToTransfer = Bukkit.getPlayer(args[0]);
                    if (playerToTransfer.getUniqueId() == player.getUniqueId()) {
                        player.sendMessage(ChatColor.GRAY + "Vous ne pouvez pas vous donner de l'argent a vous meme !");
                        return true;
                    }
                    Wallet playerToTransferWallet = walletManager.findWalletByPlayerUUID(playerToTransfer.getUniqueId());
                    Wallet playerWallet = walletManager.findWalletByPlayerUUID(player.getUniqueId());
                    if (IsDouble(args[1])) {
                        double amount = Double.parseDouble(args[1]);
                        if (amount > 0) {
                            BigDecimal playerBalance = playerWallet.getBalance();

                            if (playerBalance.compareTo(new BigDecimal(amount)) >= 0) {
                                Double oldBalancePlayer = playerBalance.doubleValue();
                                Double oldBalancePlayerToTransfer = playerToTransferWallet.getBalance().doubleValue();
                                try {
                                    playerWallet.withdraw(amount);
                                    playerToTransferWallet.deposit(amount);
                                    walletManager.save();
                                    player.sendMessage(ChatColor.GRAY + "La transaction a bien été executé");
                                    playerToTransfer.sendMessage(ChatColor.GRAY + " Vous avez recu " + ChatColor.RED + amount +
                                            ChatColor.GRAY + " de la part de " + ChatColor.RED + player.getName());
                                } catch (Exception e) {
                                    playerWallet.setAccount(oldBalancePlayer);
                                    playerToTransferWallet.setAccount(oldBalancePlayerToTransfer);
                                    walletManager.save();
                                    player.sendMessage(ChatColor.GRAY + " Un probleme a eu lieu la transaction a été annulé !");
                                    playerToTransfer.sendMessage(ChatColor.GRAY + " Un probleme a eu lieu la transaction a été annulé !");
                                }
                            } else {
                                player.sendMessage(ChatColor.GRAY + "Le montant spécifié n'est pas valide");
                            }
                        } else {
                            player.sendMessage(ChatColor.GRAY + "Vous devez transferer un montant positif");
                        }
                    } else {
                        player.sendMessage(ChatColor.GRAY + "La valeur n'est pas valide");
                    }
                } else {
                    player.sendMessage(ChatColor.GRAY + "Le joueur specifié n'existe pas ou n'est pas connecté");
                }
            } else {
                player.sendMessage(ChatColor.GRAY + "transfer money: /transferMoney <Player> <Amount>");
            }
        }
        return true;
    }
}
