package me.ZedBear.InfiniteBuckets.item;

import me.ZedBear.InfiniteBuckets.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
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

        ItemStack bucket = player.getInventory().getItemInMainHand();

        // no meta
        if (!bucket.hasItemMeta()) return;

        NamespacedKey infinite = new NamespacedKey(plugin, "infinite");
        PersistentDataContainer container = bucket.getItemMeta().getPersistentDataContainer();

        // not an infinite bucket
        if (!container.has(infinite, PersistentDataType.INTEGER)) return;

        // infinite water
        if (container.get(infinite, PersistentDataType.INTEGER) == 0) {
            if (block.getBlockData() instanceof Waterlogged) {
                Waterlogged waterloggedData = (Waterlogged) block.getBlockData();
                waterloggedData.setWaterlogged(true);
                block.setBlockData(waterloggedData, true);

                event.setCancelled(true);
                return;
            }

            block.setType(Material.WATER);
            event.setCancelled(true);
            return;
        }

        // infinite lava
        if (container.get(infinite, PersistentDataType.INTEGER) == 1) {
            block.setType(Material.LAVA);
            event.setCancelled(true);
        }
    }
}
