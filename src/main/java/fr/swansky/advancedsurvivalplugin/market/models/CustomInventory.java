package fr.swansky.advancedsurvivalplugin.market.models;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CustomInventory implements InventoryHolder {

    private final Inventory inventory;
    private final int row, column = 9;
    private final Map<Integer, Clickable> clickableMap = new HashMap<>();
    private final String inventoryTitle;
    public CustomInventory(String titleInventory, int row) {
        this.inventory = Bukkit.createInventory(this, row * column, Component.text(titleInventory));
        this.row = row;

        this.inventoryTitle = titleInventory;
    }

    public CustomInventory(int row) {
        this.inventory = Bukkit.createInventory(this, row * column);
        this.row = row;
        this.inventoryTitle = "custom Inventory";
    }

    public static CustomInventory createInventory(int row) {
        return new CustomInventory(row);
    }

    public CustomInventory addFullBackground(Material item) {
        ItemStack itemStack = generateBackgroundItem(item);

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemStack);
        }
        return this;
    }

    public CustomInventory addClickableItem(Clickable clickable) {

        int slot = (((clickable.getRowPosition() - 1) * (column)) - 1) + clickable.getColumnPosition();
        clickableMap.put(slot, clickable);
        this.inventory.setItem(slot, clickable.getIcon());
        return this;
    }

    public CustomInventory addBorder(Material material, int thickness) {
        ItemStack itemStack = generateBackgroundItem(material);
        int totalSlot = (column * row) - 1;
        // TOP
        for (int i = 0; i < column * thickness; i++) {
            inventory.setItem(i, itemStack);
        }

        //LEFT RIGHT
        int j = 0;
        for (int i = 1; i < row; i++) {
            j = (column * i) - 1;
            for (int thick = 1; thick <= thickness; thick++) {
                inventory.setItem(j + thick, itemStack);
                inventory.setItem((j + column - thick) + 1, itemStack);
            }
            j = (column * i) - 1;
        }

        //BOTTOM
        for (int i = totalSlot; i > totalSlot - (column * thickness); i--) {
            inventory.setItem(i, itemStack);
        }
        return this;
    }


    private ItemStack generateBackgroundItem(Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.empty());
        meta.setLocalizedName("NOT_MOVABLE_ITEM");
        meta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    /**
     * Get the object's inventory.
     *
     * @return The inventory.
     */
    @Override
    public @NotNull
    Inventory getInventory() {
        return inventory;
    }

    public Map<Integer, Clickable> getClickableMap() {
        return clickableMap;
    }

    public String getInventoryName() {
        return this.inventoryTitle;
    }

    public int getRow() {
        return row;
    }
}
