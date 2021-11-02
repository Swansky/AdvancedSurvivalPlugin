package fr.swansky.advancedsurvivalplugin.market.commands;

import fr.swansky.advancedsurvivalplugin.Rank;
import fr.swansky.advancedsurvivalplugin.market.MarketController;
import fr.swansky.advancedsurvivalplugin.market.models.Market;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddMarketCommand implements CommandExecutor {
    private final MarketController marketController;

    public AddMarketCommand(MarketController marketController) {
        this.marketController = marketController;
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
            if (args.length > 2) {
                String id = args[0];
                String row = args[1];
                StringBuilder marketTitle = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    marketTitle.append(args[i]);
                }
                Market market = new Market(id,marketTitle.toString(),Integer.parseInt(row));
                try {
                    marketController.add(market);
                    marketController.save();
                    player.sendMessage(ChatColor.GRAY+" Le market "+ChatColor.GREEN+marketTitle.toString()+
                            ChatColor.GRAY+" avec l'id " +
                            ChatColor.GREEN+id+
                            ChatColor.GRAY+" a été crée.");
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED+"Impossible d'ajouter ce market.");
                }

            }
            //TODO add code for add market command
        }
        return true;
    }
}
