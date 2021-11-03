package fr.swansky.advancedsurvivalplugin.market.commands;

import fr.swansky.advancedsurvivalplugin.market.CustomMarketVillagerManager;
import fr.swansky.advancedsurvivalplugin.market.models.CustomMarketVillager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class DeleteVillagerMarketCommand implements CommandExecutor {
    private final CustomMarketVillagerManager customMarketVillagerManager;

    public DeleteVillagerMarketCommand(CustomMarketVillagerManager customMarketVillagerManager) {
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
            if (args.length > 0) {
                UUID uuid;
                try {
                    uuid = UUID.fromString(args[0]);
                    Optional<CustomMarketVillager> customVillager = customMarketVillagerManager.findCustomVillager(uuid);
                    if (customVillager.isPresent()) {

                        customMarketVillagerManager.delete(customVillager.get());
                        customMarketVillagerManager.save();
                    } else {
                        player.sendMessage(ChatColor.RED + "Cette entité n'est pas un market villager");
                    }
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "UUID invalide.");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.RED + "Vous n'avez pas precisez de UUID");
            }
        }
        return true;
    }
}
