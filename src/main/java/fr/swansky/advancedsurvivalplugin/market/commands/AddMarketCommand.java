package fr.swansky.advancedsurvivalplugin.market.commands;

import fr.swansky.advancedsurvivalplugin.Rank;
import fr.swansky.advancedsurvivalplugin.market.MarketManager;
import fr.swansky.advancedsurvivalplugin.market.models.Market;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddMarketCommand implements CommandExecutor {
    private final MarketManager marketManager;

    public AddMarketCommand(MarketManager marketManager) {
        this.marketManager = marketManager;
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
            if (args.length > 1) {
                String id = args[0];
                StringBuilder marketTitle = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    marketTitle.append(args[i]);
                }
                Market market = new Market(id,marketTitle.toString(),3);
            }
            //TODO add code for add market command
        }
        return true;
    }
}
