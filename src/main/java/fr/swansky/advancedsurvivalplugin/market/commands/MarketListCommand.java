package fr.swansky.advancedsurvivalplugin.market.commands;

import fr.swansky.advancedsurvivalplugin.market.MarketController;
import fr.swansky.advancedsurvivalplugin.market.models.Market;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MarketListCommand implements CommandExecutor {
    private final MarketController marketManager;

    public MarketListCommand(MarketController marketManager) {
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
            Map<String, Market> marketMap = marketManager.getMarketMap();
            if(!marketMap.isEmpty())
            {
                StringBuilder message = new StringBuilder(String.valueOf(ChatColor.GRAY));
                for (Market value : marketMap.values()) {
                    message.append(value.getInventoryName()).append(" - ").append(ChatColor.GREEN)
                            .append(value.getMarketID()).append(ChatColor.GRAY).append("\n");
                }
                player.sendMessage(message.toString());
            }
           else
            {
                player.sendMessage(ChatColor.GRAY+"Il n'y a pas de market.");
            }

        }
        return true;
    }
}
