package fr.swansky.advancedsurvivalplugin.market.models;

import fr.swansky.advancedsurvivalplugin.AdvancedSurvivalPlugin;
import fr.swansky.advancedsurvivalplugin.economy.Wallet;
import fr.swansky.advancedsurvivalplugin.economy.WalletManager;
import fr.swansky.advancedsurvivalplugin.economy.exceptions.WalletWithdrawException;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MarketItem implements Clickable {
    private final String identificationName;
    private final String displayName;
    private final ItemStack icon;
    private final ItemStack itemForMarket;
    private final Double sellPrice;
    private final Double purchasePrice;
    private final int rowPosition;
    private final int columnPosition;
    private final WalletManager walletManager;
    private boolean sellable = false;
    private boolean purchasable = false;

    public MarketItem(String identificationName, String displayName, ItemStack itemForMarket, Double sellPrice, Double purchasePrice, int rowPosition, int columnPosition) {
        this.identificationName = identificationName;
        this.displayName = displayName;
        this.itemForMarket = itemForMarket;
        this.icon = itemForMarket.clone();
        this.sellPrice = sellPrice;
        this.purchasePrice = purchasePrice;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.walletManager = AdvancedSurvivalPlugin.INSTANCE.getWalletManager();
    }

    public MarketItem(String identificationName, String displayName, ItemStack icon, ItemStack itemForMarket, Double sellPrice, Double purchasePrice, int rowPosition, int columnPosition) {
        this.identificationName = identificationName;
        this.displayName = displayName;
        this.icon = icon;
        this.itemForMarket = itemForMarket;
        this.sellPrice = sellPrice;
        this.purchasePrice = purchasePrice;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.walletManager = AdvancedSurvivalPlugin.INSTANCE.getWalletManager();
    }

    public MarketItem(String identificationName, ItemStack itemForMarket, Double sellPrice, Double purchasePrice, int rowPosition, int columnPosition) {
        this.identificationName = identificationName;
        this.itemForMarket = itemForMarket;
        this.displayName = itemForMarket.displayName().examinableName();
        this.icon = itemForMarket.clone();
        this.sellPrice = sellPrice;
        this.purchasePrice = purchasePrice;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.walletManager = AdvancedSurvivalPlugin.INSTANCE.getWalletManager();
    }


    @Override
    public void click(Player player, ClickType clickType) {
        Wallet wallet = walletManager.findWalletByPlayerUUID(player.getUniqueId());
        if (clickType.isLeftClick()) {
            if (player.getInventory().contains(itemForMarket.getType(),1)) {
                player.getInventory().removeItem(itemForMarket);
                wallet.deposit(sellPrice);
                player.sendMessage(ChatColor.GRAY + "Vous venez de ventre un item");
            } else {
                player.sendMessage(ChatColor.GRAY + "Vous n'avez pas cet item");
            }
        } else if (clickType.isRightClick()) {
            try {
                wallet.withdraw(purchasePrice);
                player.getInventory().addItem(itemForMarket);
                player.sendMessage(ChatColor.GRAY + "Vous venez d'acheter l'item !");
            } catch (WalletWithdrawException e) {
                player.sendMessage(ChatColor.GRAY + "Il semble que vous n'avez pas assez d'argent pour cette item :O");
            }
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public void initView() {
        ItemMeta itemMeta = this.icon.getItemMeta();
        itemMeta.displayName(Component.text(this.displayName));
        List<Component> lores = new ArrayList<>();

        if (sellable) {
            lores.add(Component.text(ChatColor.GRAY + "Vendre pour: " + ChatColor.RED + sellPrice));
            lores.add(Component.text(ChatColor.GRAY + " count: " + ChatColor.RED + 1 + "x"));
        }
        if (purchasable) {
            lores.add(Component.text(ChatColor.GRAY + "Acheter pour: " + ChatColor.RED + sellPrice));
            lores.add(Component.text(ChatColor.GRAY + " count: " + ChatColor.RED + 1 + "x"));
        }
        //Second check to add at end only
        if (sellable) {
            lores.add(Component.text(ChatColor.GREEN + "CLICK GAUCHE POUR VENDRE"));
        }
        if (purchasable) {
            lores.add(Component.text(ChatColor.YELLOW + "CLICK DROIT POUR ACHETER"));
        }
        itemMeta.lore(lores);
        this.icon.setItemMeta(itemMeta);
    }

    public ItemStack getItemForMarket() {
        return itemForMarket;
    }

    public String getIdentificationName() {
        return identificationName;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }


    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public boolean isSellable() {
        return sellable;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }
}
