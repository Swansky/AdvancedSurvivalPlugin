package fr.swansky.advancedsurvivalplugin.economy.commands;

import fr.swansky.advancedsurvivalplugin.Rank;
import fr.swansky.advancedsurvivalplugin.economy.Wallet;
import fr.swansky.advancedsurvivalplugin.economy.WalletManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static fr.swansky.advancedsurvivalplugin.utils.ParseUtils.IsDouble;

public class GiveMoneyCommand implements CommandExecutor {
    private final WalletManager walletManager;

    public GiveMoneyCommand(WalletManager walletManager) {
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
            if (!player.hasPermission(Rank.ADMIN.getPermission())) {
                player.sendMessage(Rank.NO_PERMISSION_MESSAGE);
                return true;
            }
            if (args.length == 1) {
                Wallet walletByPlayerUUID = this.walletManager.findWalletByPlayerUUID(player.getUniqueId());
                if (IsDouble(args[0])) {
                    Double amount = Double.parseDouble(args[0]);
                    walletByPlayerUUID.deposit(amount);
                    walletManager.save();
                } else {
                    player.sendMessage(ChatColor.GRAY + "La somme entré n'est pas valide");
                }
            } else if (args.length > 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player playerToDeposit = Bukkit.getPlayer(args[0]);
                    if (IsDouble(args[1])) {
                        Double amount = Double.parseDouble(args[1]);
                        Wallet playerToGive = walletManager.findWalletByPlayerUUID(playerToDeposit.getUniqueId());
                        playerToGive.deposit(amount);
                        walletManager.save();
                    } else {
                        player.sendMessage(ChatColor.GRAY + "La somme entré n'est pas valide");
                    }
                }else {
                    player.sendMessage(ChatColor.GRAY+"Ce joueur n'existe pas ou n'est pas connecté");
                }
            } else {
                player.sendMessage(ChatColor.GRAY + "Utilisation de la commande /giveMoney <Joueur> <montant>");
            }
        }
        return true;
    }
}
