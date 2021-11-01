package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BedrockBreakerItem extends CustomItem {
    private static final String customItemID = "";
    private static final String customName = "";
    private static final Material material = Material.GOLDEN_PICKAXE;

    public BedrockBreakerItem() {
        super(material, customItemID, customName);
    }


    public BedrockBreakerItem(int amount) {
        super(material, amount, customItemID, customName);
    }

    /**
     * Creates a new item stack derived from the specified stack
     *
     * @param stack        the stack to copy
     * @throws IllegalArgumentException if the specified stack is null or
     *                                  returns an item meta not created by the item factory
     */
    public BedrockBreakerItem(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack, customItemID, customName);
    }

    @Override
    public void initMetaData() {

    }

    @Override
    public void rightClick(PlayerInteractEvent event) {

    }

    @Override
    public void leftClick(PlayerInteractEvent event) {

    }

    @Override
    public void middleClick(PlayerInteractEvent event) {

    }
}
