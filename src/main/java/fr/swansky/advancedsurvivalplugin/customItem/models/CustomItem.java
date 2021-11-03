package fr.swansky.advancedsurvivalplugin.customItem.models;

import fr.swansky.advancedsurvivalplugin.AdvancedSurvivalPlugin;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public abstract class CustomItem extends ItemStack {
    private final String customItemID;
    private final String customName;
    private final String description;

    /**
     * Creates a new item stack derived from the specified stack
     *
     * @param stack       the stack to copy
     * @param description
     * @throws IllegalArgumentException if the specified stack is null or
     *                                  returns an item meta not created by the item factory
     */
    public CustomItem(@NotNull ItemStack stack, String customItemID, String customName, String description) throws IllegalArgumentException {
        super(stack);
        this.customItemID = customItemID;
        this.customName = customName;
        this.description = description;
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
     * @param description
     */
    public CustomItem(@NotNull Material type, String customItemID, String customName, String description) {
        super(type);
        this.customItemID = customItemID;
        this.customName = customName;
        this.description = description;
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
     * @param description
     */
    public CustomItem(@NotNull Material type, int amount, String customItemID, String customName, String description) {
        super(type, amount);
        this.customItemID = customItemID;
        this.customName = customName;
        this.description = description;
        initMetaData();
        initContainerID();
    }

    private void initContainerID() {

        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.getPersistentDataContainer().set(AdvancedSurvivalPlugin.NAMESPACE_KEY, PersistentDataType.STRING, this.customItemID);
        setItemMeta(itemMeta);
    }

    public abstract void initMetaData();

    public abstract void rightClick(PlayerEvent event);

    public abstract void leftClick(PlayerEvent event);

    public abstract void middleClick(PlayerEvent event);


    public String getCustomItemID() {
        return customItemID;
    }

    public String getCustomName() {
        return customName;
    }

    public String getDescription() {
        return description;
    }
}
