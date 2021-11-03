package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GoldenLassoItem extends CustomItem {

    private static final String customItemID = "GOLDEN_LASSO";
    private static final String customName = ChatColor.RED + "Golden lasso";
    private static final Material material = Material.LEAD;
    private static final String description = "";
    private final Map<UUID, Entity> entityMap = new HashMap<>();

    public GoldenLassoItem() {
        super(material, customItemID, customName, description);
    }

    @Override
    public void initMetaData() {

    }

    @Override
    public void rightClick(PlayerEvent playerEvent) {

        if (playerEvent instanceof PlayerInteractEntityEvent) {
            PlayerInteractEntityEvent event = (PlayerInteractEntityEvent) playerEvent;
            Entity entity = event.getRightClicked();
            if (!(entity instanceof Player)) {
                entityMap.put(playerEvent.getPlayer().getUniqueId(), entity);
                event.getPlayer().sendMessage(ChatColor.GRAY + "Vous venez de selectionner un mob");

            }
            event.setCancelled(true);
        } else if (playerEvent instanceof PlayerInteractEvent) {
            PlayerInteractEvent event = (PlayerInteractEvent) playerEvent;
            if (entityMap.containsKey(event.getPlayer().getUniqueId())) {
                Entity entitySave = entityMap.get(playerEvent.getPlayer().getUniqueId());
                if (entitySave != null) {
                    if (!entitySave.isDead() && entitySave.isValid()) {
                        if (event.getInteractionPoint() != null) {
                            entitySave.teleport(event.getInteractionPoint());
                            entityMap.remove(event.getPlayer().getUniqueId());
                            event.getPlayer().sendMessage(ChatColor.GRAY + "Vous venez de teleporter un mob");

                        }
                    }
                }
            } else {
                event.getPlayer().sendMessage(ChatColor.GRAY + "Vous n'avez pas selectionné de mob a teleporté !");
            }
            event.setCancelled(true);
        }

    }

    @Override
    public void leftClick(PlayerEvent playerEvent) {

    }

    @Override
    public void middleClick(PlayerEvent event) {

    }
}
