package me.ZedBear.InfiniteBuckets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class ItemEvents implements Listener {

    @EventHandler
    public void onBucketDrain(PlayerBucketEmptyEvent bucket) {
        int x = bucket.getBlock().getX();
        int y = bucket.getBlock().getY();
        int z = bucket.getBlock().getZ();
        if(bucket.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Infinite Water Bucket")) {
            bucket.getPlayer().getWorld().getBlockAt(x, y, z).setType(Material.WATER);
            bucket.setCancelled(true);
        }
        if(bucket.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Infinite Lava Bucket")) {
            bucket.getPlayer().getWorld().getBlockAt(x, y, z).setType(Material.LAVA);
            bucket.setCancelled(true);
        }
    }
}
