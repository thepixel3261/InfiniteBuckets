package me.ZedBear.InfiniteBuckets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack InfiniteWaterBucket;
    public static ItemStack InfiniteLavaBucket;

    public static void init() {
        createInfiniteLavaBucket();
        createInfiniteWaterBucket();
    }

    private static void createInfiniteWaterBucket() {
        ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Infinite Water Bucket");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Legend says this water bucket");
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "was enchanted by a nice young fairy");
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "to solve the drought that a tiny village");
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "was suffering!");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.AQUA + "This is an infinite water bucket");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        InfiniteWaterBucket = item;
    }

    private static void createInfiniteLavaBucket() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Infinite Lava Bucket");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Legend says this water bucket");
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "was enchanted by a nice young fairy");
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "to solve the drought that a tiny village");
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "was suffering!");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.RED + "This is an infinite water bucket");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        InfiniteLavaBucket = item;
    }
}
