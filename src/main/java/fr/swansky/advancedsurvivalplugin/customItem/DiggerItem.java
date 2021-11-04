package fr.swansky.advancedsurvivalplugin.customItem;

import fr.swansky.advancedsurvivalplugin.customItem.models.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    public void rightClick(Event globalEvent) {

    }

    @Override
    public void leftClick(Event globalEvent) {
        if (globalEvent instanceof BlockBreakEvent) {
            BlockBreakEvent event = (BlockBreakEvent) globalEvent;
            Block block = event.getBlock();
            if (event.getPlayer().isSneaking()) return;
            if (event.getPlayer().getLocation().getPitch() < 30 && event.getPlayer().getLocation().getPitch() > -30) {
                List<Block> blocksToBreak = getVerticalBlock(block, event.getPlayer().getFacing());
                ItemStack itemInUse = event.getPlayer().getItemInUse();
                for (Block block1 : blocksToBreak) {
                    block1.breakNaturally(itemInUse, true);
                }
            } else {
                List<Block> blocksToBreak = getHorizontalBlock(block);
                ItemStack itemInUse = event.getPlayer().getItemInUse();
                for (Block block1 : blocksToBreak) {
                    block1.breakNaturally(itemInUse, true);
                }
            }
        }
    }

    private List<Block> getHorizontalBlock(Block block) {
        List<Block> blocksToBreak = new ArrayList<>();
        int x = block.getX() - 1, y = block.getY(), z = block.getZ() - 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Block blockAt = block.getWorld().getBlockAt(x, y, z);
                //Bukkit.broadcast(Component.text(String.format("x: %s y: %s z: %s", x, y, z)));
                if (blockAt.getType() == block.getType()) {
                    blocksToBreak.add(blockAt);
                }
                z = z + 1;
            }
            x = x + 1;
            z = z - 3;
        }
        return blocksToBreak;
    }

    private List<Block> getVerticalBlock(Block block, @NotNull BlockFace playerLocation) {
        List<Block> blocksToBreak = new ArrayList<>();
        blocksToBreak.add(block);

        switch (playerLocation) {
            case WEST:
            case EAST:
                int x = block.getX(), y = block.getY() + 1, z = block.getZ() - 1;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Block blockAt = block.getWorld().getBlockAt(x, y, z);
                        //Bukkit.broadcast(Component.text(String.format("x: %s y: %s z: %s", x, y, z)));
                        if (blockAt.getType() == block.getType()) {
                            blocksToBreak.add(blockAt);
                        }
                        z = z + 1;
                    }
                    y = y - 1;
                    z = z - 3;
                }
                break;
            case SOUTH:
            case NORTH:
                int xSN = block.getX() - 1, ySN = block.getY() + 1, zSN = block.getZ();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Block blockAt = block.getWorld().getBlockAt(xSN, ySN, zSN);
                        //Bukkit.broadcast(Component.text(String.format("x: %s y: %s z: %s", x, y, z)));
                        if (blockAt.getType() == block.getType()) {

                            blocksToBreak.add(blockAt);
                        }
                        xSN = xSN + 1;
                    }
                    ySN = ySN - 1;
                    xSN = xSN - 3;
                }
                break;
        }
        return blocksToBreak;
    }
}
