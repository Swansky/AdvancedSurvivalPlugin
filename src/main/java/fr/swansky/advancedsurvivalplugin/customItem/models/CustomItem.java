package fr.swansky.advancedsurvivalplugin.customItem.models;

import fr.swansky.advancedsurvivalplugin.AdvancedSurvivalPlugin;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public abstract class CustomItem extends ItemStack {
    private final String customItemID;
    private final String customName;

    /**
     * Creates a new item stack derived from the specified stack
     *
     * @param stack the stack to copy
     * @throws IllegalArgumentException if the specified stack is null or
     *                                  returns an item meta not created by the item factory
     */
    public CustomItem(@NotNull ItemStack stack, String customItemID, String customName) throws IllegalArgumentException {
        super(stack);
        this.customItemID = customItemID;
        this.customName = customName;
        initMetaData();
        initContainerID();
    }

    /**
     * Defaults stack size to 1, with no extra data.
     * <p>
     * <b>IMPORTANT: An <i>Item</i>Stack is only designed to contain
     * <i>items</i>. Do not use this class to encapsulate Materials for which
     * {@link Material#isItem()} returns false.</b>
     *
     * @param type         item material
     * @param customItemID
     * @param customName
     */
    public CustomItem(@NotNull Material type, String customItemID, String customName) {
        super(type);
        this.customItemID = customItemID;
        this.customName = customName;
        initMetaData();
        initContainerID();
    }

    /**
     * An item stack with no extra data.
     * <p>
     * <b>IMPORTANT: An <i>Item</i>Stack is only designed to contain
     * <i>items</i>. Do not use this class to encapsulate Materials for which
     * {@link Material#isItem()} returns false.</b>
     *
     * @param type         item material
     * @param amount       stack size
     * @param customItemID
     * @param customName
     */
    public CustomItem(@NotNull Material type, int amount, String customItemID, String customName) {
        super(type, amount);
        this.customItemID = customItemID;
        this.customName = customName;
        initMetaData();
        initContainerID();
    }

    private void initContainerID() {

        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.getPersistentDataContainer().set(AdvancedSurvivalPlugin.NAMESPACE_KEY, PersistentDataType.STRING, this.customItemID);
        setItemMeta(itemMeta);
    }

    public abstract void initMetaData();

    public abstract void rightClick(PlayerInteractEvent event);

    public abstract void leftClick(PlayerInteractEvent event);

    public abstract void middleClick(PlayerInteractEvent event);


    public String getCustomItemID() {
        return customItemID;
    }

    public String getCustomName() {
        return customName;
    }


}
