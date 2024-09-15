package ru.healthanmary.trainingplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class TrainingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("effectmenu").setExecutor(new CommandExecutor());
        getServer().getPluginManager().registerEvents(new ForbidGetItem(), this);
        getServer().getPluginManager().registerEvents(new GetPotionEffect(), this);
    }

    @Override
    public void onDisable() {
    }
}
