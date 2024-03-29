package fr.swansky.advancedsurvivalplugin.home.commands;

import fr.swansky.advancedsurvivalplugin.home.Home;
import fr.swansky.advancedsurvivalplugin.home.HomeManager;
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

public class HomeCommand implements CommandExecutor, TabCompleter {
    private final HomeManager homeManager;

    public HomeCommand(HomeManager homeManager) {
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
                    player.teleport(homeByName.get().getLocation());
                    player.sendMessage(ChatColor.GRAY + "Vous avez été teleporté a votre home.");
                } else {
                    player.sendMessage(ChatColor.GRAY + "Cette home n'existe pas");
                }
            } else {
                List<Home> playerHomes = homeManager.getPlayerHomesByPlayerUUID(player.getUniqueId());
                if (playerHomes.isEmpty()) {
                    player.sendMessage(ChatColor.GRAY + "Vous n'avez pas de home");
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(ChatColor.GRAY).append("Vos homes: ").append('\n');
                    for (Home playerHome : playerHomes) {
                        stringBuilder.append(playerHome.getHomeName()).append(", ");
                    }
                    stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                    player.sendMessage(stringBuilder.toString());
                }
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
        List<String> content = new ArrayList<>();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            for (Home home : homeManager.getPlayerHomesByPlayerUUID(player.getUniqueId())) {
                content.add(home.getHomeName());
            }
        }
        return content;
    }
}
