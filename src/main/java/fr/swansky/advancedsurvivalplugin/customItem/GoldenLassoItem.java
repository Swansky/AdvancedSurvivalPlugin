package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

public class GoldenLassoItem extends CustomItem {

    private static final String customItemID = "GOLDEN_LASSO";
    private static final String customName = ChatColor.RED + "Golden lasso";
    private static final Material material = Material.LEAD;
    private static final String description = "";

    public GoldenLassoItem() {
        super(material, customItemID, customName, description);
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
