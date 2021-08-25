package me.ZedBear.InfiniteBuckets;

import net.milkbowl.vault.VaultEco;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Locale;

public class Commands implements CommandExecutor {

    public static Economy econ = null;

    public static Economy getEconomy() {
        return econ;
    }

    public static String lavacost() {
        return Main.plugin.getConfig().getString("costs.lava");
    }

    public static String watercost() {
        return Main.plugin.getConfig().getString("costs.water");
    }

    int waterc = Integer.parseInt(watercost());
    int lavac = Integer.parseInt(lavacost());

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only Players can use that Command!");
            return true;
        } else {
            Player player = (Player) sender;
            Economy economy = Main.getEconomy();
            if (player.hasPermission("infbuckets.obtain")) {
                if (cmd.getName().equalsIgnoreCase("infwater")) {
                    if (args.length == 0) {
                        //Player only typed '/infwater'
                        sender.sendMessage("");
                        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You are about to purchase an infinite water bucket");
                        sender.sendMessage(ChatColor.GREEN + "This costs 100k. Type /infwater confirm to buy it.");
                        sender.sendMessage("");
                    } else {
                        if (args.length == 1) {
                            if (args[0].equals("confirm")) {
                                if (economy.getBalance(player) >= waterc) {
                                    economy.withdrawPlayer(player, waterc);
                                    sender.sendMessage(ChatColor.GREEN + "You have bought an Infinite water bucket!");
                                    player.getInventory().addItem(ItemManager.InfiniteWaterBucket);
                                } else {
                                    sender.sendMessage(ChatColor.GREEN + "You do not have enough money!");
                                }


                                return true;
                            } else {
                                sender.sendMessage("");
                                sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You are about to purchase an infinite water bucket");
                                sender.sendMessage(ChatColor.GREEN + "This costs 100k. Type /infwater confirm to buy it.");
                                sender.sendMessage("");
                            }
                        }
                    }
                }
                if (cmd.getName().equalsIgnoreCase("inflava")) {
                    if (args.length == 0) {
                        //Player only typed '/infwater'
                        sender.sendMessage("");
                        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You are about to purchase an infinite lava bucket");
                        sender.sendMessage(ChatColor.GREEN + "This costs 100k. Type /inflava confirm to buy it.");
                        sender.sendMessage("");
                    } else {
                        if (args.length == 1) {
                            if (args[0].equals("confirm")) {
                                if (economy.getBalance(player) >= lavac) {
                                    economy.withdrawPlayer(player, lavac);
                                    sender.sendMessage(ChatColor.GREEN + "You have bought an Infinite Lava bucket!");
                                    player.getInventory().addItem(ItemManager.InfiniteLavaBucket);
                                } else {
                                    sender.sendMessage(ChatColor.GREEN + "You do not have enough money!");
                                }


                                return true;
                            } else {
                                sender.sendMessage("");
                                sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You are about to purchase an infinite lava bucket");
                                sender.sendMessage(ChatColor.GREEN + "This costs 100k. Type /inflava confirm to buy it.");
                                sender.sendMessage("");
                            }
                        }
                    }
                }
                if (cmd.getName().equalsIgnoreCase("infinitebuckets")) {
                    if (player.hasPermission("infbuckets.admin")) {
                        if (args.length == 0) {
                            //Player only typed '/infinitebuckets' so lets heal them back!
                            sender.sendMessage(ChatColor.RED + "Invalid usage: Use /ib reload!");
                        } else {
                            //Player typed something more
                            if (args.length >= 1) {
                                if (args[0].equals("reload")) {

                                    sender.sendMessage(ChatColor.GREEN + "Configuration Reloaded.");
                                } else {
                                    sender.sendMessage(ChatColor.GREEN + "Configuration Reloaded.");
                                }

                            } else {
                                sender.sendMessage(ChatColor.RED + "Incorrect usage. /ib reload!");
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You are not allowed to use that command!");
            }
        }
        return true;
    }
}
