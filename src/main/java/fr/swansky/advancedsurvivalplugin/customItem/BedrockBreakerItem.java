package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

public class BedrockBreakerItem extends CustomItem {
    private static final String customItemID = "BEDROCK_BREAKER";
    private static final String customName = ChatColor.RED + "Bedrock breaker";
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
     * @param stack the stack to copy
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
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType() == Material.BEDROCK) {
                event.getClickedBlock().breakNaturally();
                event.getPlayer().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_WITHER_SHOOT, 1L, 1L);


                Damageable itemMeta = (Damageable) event.getItem().getItemMeta();

                if (itemMeta.getDamage() + 10 > event.getItem().getType().getMaxDurability()) {
                    event.getPlayer().playSound(
                            event.getPlayer().getLocation(),
                            Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                    event.getPlayer().getInventory().remove(event.getItem());
                } else {

                    itemMeta.setDamage(itemMeta.getDamage() + 10);
                }

                event.getItem().setItemMeta(itemMeta);
            }
        }
    }

    @Override
    public void middleClick(PlayerInteractEvent event) {

    }
}
