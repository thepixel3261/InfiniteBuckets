package me.ZedBear.InfiniteBuckets;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.permission.Permission;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    public FileConfiguration config = this.getConfig();
    public static Main plugin;

    @Override
    public void onEnable() {
        ItemManager.init();
        this.getCommand("infwater").setExecutor(new Commands());
        this.getCommand("inflava").setExecutor(new Commands());
        this.getCommand("infinitebuckets").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new ItemEvents(), this);
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Config Startup Logic
        this.saveDefaultConfig();
        config.addDefault("costs.lava", 50000);
        config.addDefault("costs.water", 50000);
        plugin = this;
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
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
}
