package fr.swansky.advancedsurvivalplugin.utilsGameplay.commands;

import fr.swansky.advancedsurvivalplugin.utilsGameplay.BackManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class backCommand implements CommandExecutor {
    private final BackManager backManager;

    public backCommand(BackManager backManager) {
        this.backManager = backManager;
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
            Optional<Location> oldLocation = backManager.getOldLocation(player.getUniqueId());
            if (oldLocation.isPresent()) {
                player.teleport(oldLocation.get());
            } else {
                player.sendMessage(ChatColor.GRAY + "Vous n'avez pas été teleporter recemment");
            }
        }
        return true;
    }
}
