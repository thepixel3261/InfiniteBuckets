package me.ZedBear.InfiniteBuckets.item;

import me.ZedBear.InfiniteBuckets.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Cauldron;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemEvents implements Listener {

    private final Main plugin;

    public ItemEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBucketDrain(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();

        Block block = event.getBlock();

        ItemStack bucket = event.getItemStack();

        // no meta
        if (!bucket.hasItemMeta()) return;

        NamespacedKey infinite = new NamespacedKey(plugin, "infinite");
        PersistentDataContainer container = bucket.getItemMeta().getPersistentDataContainer();

        // not an infinite bucket
        if (!container.has(infinite, PersistentDataType.INTEGER)) return;


        BlockState state = block.getState();
        BlockData blockData = block.getBlockData();
        // infinite water
        if (container.get(infinite, PersistentDataType.INTEGER) == 0) {

            // Waterloggable
            if (block.getBlockData() instanceof Waterlogged) {
                Waterlogged waterloggedData = (Waterlogged) block.getBlockData();
                waterloggedData.setWaterlogged(true);
                block.setBlockData(waterloggedData, true);

                event.setCancelled(true);
                return;
            }

            // Cauldron - Water

            //Not Lava cauldron
            if (block.getType() == Material.LAVA_CAULDRON) {
                event.setCancelled(true);
                return;
            }

            if (block.getType() == Material.CAULDRON || block.getType() == Material.WATER_CAULDRON) {
                block.setType(Material.WATER_CAULDRON);
                if (blockData instanceof Levelled) {
                    Levelled cauldronData = (Levelled) blockData;
                    cauldronData.setLevel(cauldronData.getMaximumLevel());
                    block.setBlockData(cauldronData);
                }
                event.setCancelled(true);
                return;
            }

            block.setType(Material.WATER);
            event.setCancelled(true);
            return;
        }

        // infinite lava
        if (container.get(infinite, PersistentDataType.INTEGER) == 1) {

            // Cauldron - Lava

            //Not Water cauldron
            if (block.getType() == Material.WATER_CAULDRON) {
                event.setCancelled(true);
                return;
            }

            if (block.getType() == Material.CAULDRON || block.getType() == Material.LAVA_CAULDRON || block.getType() == Material.WATER_CAULDRON) {
                block.setType(Material.LAVA_CAULDRON);
                if (blockData instanceof Levelled) {
                    Levelled cauldronData = (Levelled) blockData;
                    cauldronData.setLevel(cauldronData.getMaximumLevel());
                    block.setBlockData(cauldronData);
                }
                event.setCancelled(true);
                return;
            }

            block.setType(Material.LAVA);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent event) {
        ItemStack bucket = event.getItem();
        Block dispenserBlock = event.getBlock();

        // no meta
        if (!bucket.hasItemMeta()) return;

        NamespacedKey infinite = new NamespacedKey(plugin, "infinite");
        PersistentDataContainer container = bucket.getItemMeta().getPersistentDataContainer();

        // not an infinite bucket
        if (!container.has(infinite, PersistentDataType.INTEGER)) return;

        // Get the block where the liquid will be placed
        Block targetBlock = dispenserBlock.getRelative(((org.bukkit.block.data.Directional) dispenserBlock.getBlockData()).getFacing());

        // Check if target block can be filled
        if (targetBlock.getType() != Material.AIR &&
                targetBlock.getType() != Material.CAULDRON &&
                !(targetBlock.getBlockData() instanceof Waterlogged)) {
            return; // Can't place liquid here
        }

        // water
        if (container.get(infinite, PersistentDataType.INTEGER) == 0) {
            // Handle waterloggable blocks
            if (targetBlock.getBlockData() instanceof Waterlogged) {
                Waterlogged waterloggedData = (Waterlogged) targetBlock.getBlockData();
                waterloggedData.setWaterlogged(true);
                targetBlock.setBlockData(waterloggedData, true);
            }
            // cauldrons
            else if (targetBlock.getType() == Material.CAULDRON) {
                targetBlock.setType(Material.WATER_CAULDRON);
                BlockData blockData = targetBlock.getBlockData();
                if (blockData instanceof Levelled) {
                    Levelled cauldronData = (Levelled) blockData;
                    cauldronData.setLevel(cauldronData.getMaximumLevel());
                    targetBlock.setBlockData(cauldronData);
                }
            }
            // regular blocks
            else if (targetBlock.getType() == Material.AIR) {
                targetBlock.setType(Material.WATER);
            }
        }

        // lava
        else if (container.get(infinite, PersistentDataType.INTEGER) == 1) {
            // cauldrons
            if (targetBlock.getType() == Material.CAULDRON) {
                targetBlock.setType(Material.LAVA_CAULDRON);
                BlockData blockData = targetBlock.getBlockData();
                if (blockData instanceof Levelled) {
                    Levelled cauldronData = (Levelled) blockData;
                    cauldronData.setLevel(cauldronData.getMaximumLevel());
                    targetBlock.setBlockData(cauldronData);
                }
            }
            // regular blocks
            else if (targetBlock.getType() == Material.AIR) {
                targetBlock.setType(Material.LAVA);
            }
        }

        event.setCancelled(true);
    }

}
