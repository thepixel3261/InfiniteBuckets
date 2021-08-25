package me.ZedBear.InfiniteBuckets;

import me.ZedBear.InfiniteBuckets.commands.Commands;
import me.ZedBear.InfiniteBuckets.item.ItemEvents;
import me.ZedBear.InfiniteBuckets.item.ItemManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Economy econ = null;
    public static Main instance;

    private ItemManager itemManager;

    public void onEnable() {
        instance = this;

        itemManager = new ItemManager(this);

        this.getCommand("infwater").setExecutor(new Commands(this));
        this.getCommand("inflava").setExecutor(new Commands(this));
        this.getCommand("infinitebuckets").setExecutor(new Commands(this));

        getServer().getPluginManager().registerEvents(new ItemEvents(this), this);

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Save default config
        this.saveDefaultConfig();

    }

    public void onDisable() {

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public ItemManager getItemManager() {return itemManager;}

    public Economy getEconomy() {
        return econ;
    }

}
