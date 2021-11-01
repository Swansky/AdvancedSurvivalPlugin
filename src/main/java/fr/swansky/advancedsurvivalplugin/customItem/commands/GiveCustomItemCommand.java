package fr.swansky.advancedsurvivalplugin.customItem.commands;

import fr.swansky.advancedsurvivalplugin.customItem.BedrockBreakerItem;
import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiveCustomItemCommand implements CommandExecutor, TabCompleter {
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
        //TODO add code for give custom item command
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                String arg = args[0];
                switch (arg) {
                    case "bedrockBreaker":
                        ItemStack itemStack = new BedrockBreakerItem();

                        player.getInventory().addItem(itemStack);
                        player.sendMessage("L'objet a été donné");
                        break;
                    default:
                        player.sendMessage("Ce type n'existe pas");
                }
            } else {
                player.sendMessage("Il manque le type de l'objet");
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
        List<String> list = new ArrayList<>();
        list.add("bedrockBreaker");
        return list;
    }
}
