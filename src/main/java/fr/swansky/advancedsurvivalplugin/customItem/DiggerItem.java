package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class DiggerItem extends CustomItem {
    private static final String customItemID = "BEDROCK_BREAKER";
    private static final String customName = ChatColor.RED + "Bedrock breaker";
    private static final Material material = Material.GOLDEN_PICKAXE;
    private static final String description = "";

    public DiggerItem() {
        super(new ItemStack(material), customItemID, customName, description);
    }

    @Override
    public void initMetaData() {

    }

    @Override
    public void rightClick(Event event) {

    }

    @Override
    public void leftClick(Event event) {

    }
}
