package fr.swansky.advancedsurvivalplugin.home.commands;

import fr.swansky.advancedsurvivalplugin.home.Home;
import fr.swansky.advancedsurvivalplugin.home.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DeleteHomeCommand implements CommandExecutor {
    private final HomeManager homeManager;

    public DeleteHomeCommand(HomeManager homeManager) {
        this.homeManager = homeManager;
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
                Optional<Home> homeByName = homeManager.getHomeByName(player.getUniqueId(), args[0]);
                if (homeByName.isPresent()) {
                        homeManager.delete(homeByName.get());
                        homeManager.save();
                        player.sendMessage(ChatColor.GRAY+"Ce home a bien été supprimé");
                } else {
                    player.sendMessage(ChatColor.GRAY+"Ce nom de home existe pas");
                }
            } else {
                player.sendMessage(ChatColor.GRAY + "Vous n'avez pas precisé de nom de home a supprimer");
            }
        }
        return true;
    }
}
