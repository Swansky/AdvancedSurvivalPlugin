package fr.swansky.advancedsurvivalplugin.home.commands;

import fr.swansky.advancedsurvivalplugin.home.Home;
import fr.swansky.advancedsurvivalplugin.home.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class SetHomeCommand implements CommandExecutor {
    private final HomeManager homeManager;

    public SetHomeCommand(HomeManager homeManager) {
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
                if (!homeManager.homeExist(player.getUniqueId(), args[0])) {
                    Home home = new Home(args[0].toLowerCase(Locale.ROOT), player.getLocation(), player.getUniqueId());
                    try {
                        homeManager.add(home);
                        homeManager.save();
                        player.sendMessage(ChatColor.GRAY + "Le home a bien été ajouté.");
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Une erreur critique a eu lieu ");
                    }
                } else {
                    player.sendMessage(ChatColor.GRAY + "Ce nom de home existe déjà");
                }
            } else {
                player.sendMessage(ChatColor.GRAY + " Vous n'avez pas precisé de nom de home");
            }
        }
        return true;
    }
}
