package fr.swansky.advancedsurvivalplugin.market.commands;

import fr.swansky.advancedsurvivalplugin.market.MarketController;
import fr.swansky.advancedsurvivalplugin.market.models.Market;
import fr.swansky.advancedsurvivalplugin.market.models.MarketItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static fr.swansky.advancedsurvivalplugin.utils.ParseUtils.*;

public class AddMarketItemCommand implements CommandExecutor {
    private final MarketController marketController;

    public AddMarketItemCommand(MarketController marketController) {
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
            if (args.length > 8) {
                String id = args[0];
                if (Material.getMaterial(args[1]) != null) {
                    Material material = Material.getMaterial(args[1]);
                    if (IsBoolean(args[2])) {
                        boolean sellable = Boolean.parseBoolean(args[2]);
                        if (IsDouble(args[3])) {
                            Double sellPrice = Double.parseDouble(args[3]);
                            if (IsBoolean(args[4])) {
                                boolean purchasable = Boolean.parseBoolean(args[4]);
                                if (IsDouble(args[5])) {
                                    Double purchasePrice = Double.parseDouble(args[5]);
                                    if (marketController.existByID(args[6])) {
                                        Market marketByID = marketController.getMarketByID(args[6]);
                                        ItemStack itemInUse = player.getInventory().getItemInMainHand();
                                        if (itemInUse.getType() != Material.AIR) {
                                            if (IsInteger(args[7]) && IsInteger(args[8])) {
                                                int row = Integer.parseInt(args[7]);
                                                int column = Integer.parseInt(args[8]);
                                                StringBuilder title = new StringBuilder();
                                                for (int i = 9; i < args.length; i++) {
                                                    title.append(args[i]);
                                                }
                                                itemInUse = itemInUse.clone();
                                                itemInUse.setAmount(1);
                                                MarketItem marketItem = new MarketItem(id, title.toString(), new ItemStack(material), itemInUse, sellPrice, purchasePrice, row, column);
                                                marketItem.setSellable(sellable);
                                                marketItem.setPurchasable(purchasable);
                                                marketByID.addClickableItem(marketItem);
                                                marketController.save();
                                            } else {
                                                player.sendMessage(ChatColor.GRAY + "Le row ou la column n'est pas valide");
                                            }

                                        } else {
                                            player.sendMessage("Vous n'avez pas l'item a la main");
                                        }

                                    } else {
                                        player.sendMessage(ChatColor.GRAY + "L'id de market n'est pas la bonne.");
                                    }

                                } else {
                                    player.sendMessage(ChatColor.GRAY + "Le prix d'achat n'est pas valide.");
                                }
                            } else {
                                player.sendMessage(ChatColor.GRAY + "la valeur de purchasable n'est pas bonne cela doit etre un boolean");
                            }
                        } else {
                            player.sendMessage(ChatColor.GRAY + "La valeur de vente n'est pas valide.");
                        }
                    } else {
                        player.sendMessage(ChatColor.GRAY + "la valeur de sellable n'est pas bonne cela doit etre un boolean");
                    }
                } else {
                    player.sendMessage(ChatColor.GRAY + "Mauvais material");
                }

            } else {
                player.sendMessage(ChatColor.GRAY + "Utilisation de la commande: <ID> <MATERIAL icon> <sellable> <sell price> <purchasable>  <purchase price> <marketID> <row> <column>  <Display name>");

            }
        }
        return true;
    }
}
