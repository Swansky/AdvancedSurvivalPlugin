package fr.swansky.advancedsurvivalplugin.market.commands;

import fr.swansky.advancedsurvivalplugin.Rank;
import fr.swansky.advancedsurvivalplugin.market.CustomMarketVillagerManager;
import fr.swansky.advancedsurvivalplugin.market.MarketManager;
import fr.swansky.advancedsurvivalplugin.market.models.CustomMarketVillager;
import fr.swansky.advancedsurvivalplugin.market.models.Market;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddVillagerMarketCommand implements CommandExecutor, TabCompleter {
    private final MarketManager marketManager;
    private final CustomMarketVillagerManager customMarketVillagerManager;

    public AddVillagerMarketCommand(MarketManager marketManager, CustomMarketVillagerManager customMarketVillagerManager) {
        this.marketManager = marketManager;
        this.customMarketVillagerManager = customMarketVillagerManager;
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
                Optional<Market> marketByIdentificationName = marketManager.getMarketByIdentificationName(args[0]);
                if (marketByIdentificationName.isPresent()) {
                    Market market = marketByIdentificationName.get();
                    StringBuilder villagerName = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        villagerName.append(args[i]).append(" ");
                    }
                    villagerName.delete(villagerName.length() - 1, villagerName.length());
                    CustomMarketVillager customMarketVillager = new CustomMarketVillager(player.getLocation(), villagerName.toString());
                    customMarketVillager.setMarket(market);
                    try {
                        customMarketVillagerManager.add(customMarketVillager);
                        customMarketVillagerManager.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    player.sendMessage(ChatColor.GRAY + "Le market preciser n'est pas valide");
                }

            } else {
                player.sendMessage(ChatColor.GRAY + "Mauvaise utilisation, commande: /addVillagerMarket <MARKET_ID> <villager name>");
            }

        }
        return true;
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>(marketManager.getMarketMap().keySet());
    }
}
